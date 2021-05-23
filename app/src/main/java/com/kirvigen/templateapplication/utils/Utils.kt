package com.kirvigen.templateapplication.utils

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.view.animation.DecelerateInterpolator
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager
import com.kirvigen.templateapplication.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.roundToInt


class Utils {
    companion object {
        val listOpening = mutableListOf<String>()
        val Int.px: Int
            get() = (this * Resources.getSystem().displayMetrics.density).toInt()


        fun setMarginView(v:View,dp:Int = 0){
            val params = v.layoutParams as ViewGroup.MarginLayoutParams
            val marginTop = getStatusBarHeight(v.context) + px(dp,v.context).toInt()
            params.setMargins(0,marginTop,0,0)
            v.layoutParams = params
        }
        fun isInternetConnection(context: Context): Boolean {
            val connected: Boolean
            val connectivityManager =
                    context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
            connected =
                    connectivityManager!!.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)!!.state == NetworkInfo.State.CONNECTED ||
                            connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)!!.state == NetworkInfo.State.CONNECTED
            return connected
        }

        fun animate_containerIn(container: ViewGroup,tag:String = "",scale:Boolean = true) {
            container.alpha = 0f
            if(scale) {
                container.scaleX = 0.95f
                container.scaleY = 0.95f
            }
            if(tag in listOpening) {
                container.animate().alpha(1f).scaleX(1f).scaleY(1f)
                    .setInterpolator(DecelerateInterpolator()).start()
            }else {
                if(tag != "") listOpening.add(tag)
                CoroutineScope(Dispatchers.Main).launch {
                    container.animate().alpha(1f).scaleX(1f).scaleY(1f)
                        .setInterpolator(DecelerateInterpolator()).start()
                }
            }

        }

        fun setTransitionBounds(container: ViewGroup) {
            TransitionManager.beginDelayedTransition(
                container, ChangeBounds()
                    .setInterpolator(DecelerateInterpolator()).setDuration(200)
            )
        }

        fun buildSimpleAlert(
            context: Context,
            label: String,
            positive: String,
            negative: String,
            eventSuccess: () -> Unit
        ) {
            val builder = AlertDialog.Builder(context)
            builder.setMessage(label)
                    .setCancelable(false)
                    .setPositiveButton(
                        positive
                    ) { dialog, id -> eventSuccess() }
                    .setNegativeButton(
                        negative
                    ) { dialog, id -> dialog.cancel() }
            val alert = builder.create()
            alert.show()
        }

        fun px(dp: Int, context: Context): Float {
            val displayMetrics: DisplayMetrics = context.resources.displayMetrics
            return (dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT)).roundToInt().toFloat()
        }

        fun sp_px(sp: Int, context: Context): Float {
            return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP,
                sp.toFloat(),
                context.resources.displayMetrics
            )
        }

        fun hideKeyboard(v: View) {
            val imm = v.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(v.windowToken, 0)
        }

        fun showKeyboard(v: View) {
            val inputMethodManager: InputMethodManager = v.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);
        }

        fun setTransperent(window: Window) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.apply {
                    clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                    addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        decorView.systemUiVisibility =
                                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                    } else {
                        decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    }
                    statusBarColor = Color.TRANSPARENT
                    navigationBarColor = ActivityCompat.getColor(window.context, R.color.black)
                }
            }
        }

        private fun getStatusBarHeight(context: Context): Int {
            var result = 0
            val resourceId = context.resources.getIdentifier(
                "status_bar_height",
                "dimen",
                "android"
            )
            if (resourceId > 0) {
                result = context.resources.getDimensionPixelSize(resourceId)
            }
            return result
        }
    }


}