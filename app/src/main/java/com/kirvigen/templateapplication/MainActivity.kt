package com.kirvigen.templateapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kirvigen.templateapplication.ui.fragments.listGames.ListGamesFragments
import com.kirvigen.templateapplication.utils.FragmentHelper

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FragmentHelper(supportFragmentManager).addFragment(ListGamesFragments.newInstance())

    }
}