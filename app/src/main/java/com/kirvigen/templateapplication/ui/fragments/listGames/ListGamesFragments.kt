package com.kirvigen.templateapplication.ui.fragments.listGames

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kirvigen.templateapplication.R
import com.kirvigen.templateapplication.databinding.FragmentListGamesBinding
import com.kirvigen.templateapplication.ui.base.BaseFragment

class ListGamesFragments:BaseFragment(R.layout.fragment_list_games) {

    private val binding by viewBinding(FragmentListGamesBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}