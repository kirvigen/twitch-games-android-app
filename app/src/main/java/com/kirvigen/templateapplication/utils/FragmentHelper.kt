package com.kirvigen.templateapplication.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.kirvigen.templateapplication.R

class FragmentHelper(private val fragmentManager: FragmentManager) {

    var containerId:Int = R.id.frame_main

    fun addFragment(fragment: Fragment?, tag:String = fragment?.tag?:"") {
        if (fragment == null) return
        fragmentManager.beginTransaction()
            .replace(containerId, fragment, tag)
            .addToBackStack(tag)
            .commit()
    }

}