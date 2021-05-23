package com.kirvigen.templateapplication.ui.fragments.listGames

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import com.kirvigen.templateapplication.R
import com.kirvigen.templateapplication.databinding.FragmentListGamesBinding
import com.kirvigen.templateapplication.ui.alerts.RateAlert
import com.kirvigen.templateapplication.ui.base.BaseFragment
import kotlinx.android.synthetic.main.alert_rate_application.view.*
import kotlinx.android.synthetic.main.fragment_list_games.*
import org.koin.android.viewmodel.ext.android.viewModel


class ListGamesFragments:BaseFragment(R.layout.fragment_list_games) {

    companion object{
        fun newInstance():Fragment{
            return ListGamesFragments()
        }
    }

    private var loading = true
    private var page = 1
    private var adapter = AdapterTopGames(mutableListOf())
    private val binding by viewBinding(FragmentListGamesBinding::bind)
    private val listGameViewModel: ListGameViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecycler()
        initError()
        binding.floatingRate.setOnClickListener { v->
            RateAlert(v.context).setRateListener {
                if(it > 0f)
                Toast.makeText(v.context, String.format(getString(R.string.thanks_rate),it), Toast.LENGTH_LONG).show()
            }.show()
        }
        listGameViewModel.getGames(25,0)
    }

    private fun initError() {
        listGameViewModel.handleError.observe(viewLifecycleOwner, {
            binding.swipeRefreshLayout.isRefreshing = false
            binding.progress.visibility = View.GONE
            adapter.data.clear()
            when(it){
                ErrorState.INTERNETERROR->{
                    val snackbar = Snackbar.make(binding.floatingRate,it.id,Snackbar.LENGTH_LONG)
                    snackbar.setAction(getString(R.string.refresh)){
                        loading = true
                        listGameViewModel.getGames(25,25*page)
                    }
                    snackbar.show()
                }
            }
        })
    }

    private fun initRecycler(){
        binding.recycler.adapter = adapter
        listGameViewModel.topGames.observe(viewLifecycleOwner, {
            binding.swipeRefreshLayout.isRefreshing = false
            loading = false
            progress.visibility = View.GONE
            adapter.setData(it)
        })
        binding.recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    val mLayoutManager = recyclerView.layoutManager?:return
                    val visibleItemCount = mLayoutManager.childCount
                    val totalItemCount = mLayoutManager.itemCount
                    val pastVisibleItems =(mLayoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                    if (!loading) {
                        if (visibleItemCount + pastVisibleItems >= totalItemCount-1) {
                            page += 1
                            loading = true
                            listGameViewModel.getGames(25,25*page)
                        }
                    }
                }
            }
        })
        binding.swipeRefreshLayout.setOnRefreshListener {
            adapter.data.clear()
            loading = true
            page = 1
            listGameViewModel.getGames(25,0)
        }
        binding.swipeRefreshLayout.setColorSchemeColors(resources.getColor(R.color.purple_500))
    }
}