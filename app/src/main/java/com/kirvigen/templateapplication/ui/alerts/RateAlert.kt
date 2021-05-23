package com.kirvigen.templateapplication.ui.alerts

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.Button
import com.kirvigen.templateapplication.R
import kotlinx.android.synthetic.main.alert_rate_application.view.*

class RateAlert(context: Context): Dialog(context) {

    val mView: View = LayoutInflater.from(context).inflate(R.layout.alert_rate_application, null, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView(mView)
    }

    fun setRateListener(callback:(Float)->Unit):RateAlert{
        mView.button.setOnClickListener {
            callback(mView?.rating_bar?.rating?:0f)
            this.dismiss()
        }
        return this
    }
}