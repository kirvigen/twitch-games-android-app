package com.kirvigen.templateapplication.ui.fragments.listGames

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirvigen.templateapplication.data.api.Result
import com.kirvigen.templateapplication.data.api.repositories.TwitchRepository
import com.kirvigen.templateapplication.data.models.database.TopGameDb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListGameViewModel(private val twitchRepository: TwitchRepository):ViewModel() {

    val topGames = MutableLiveData<List<TopGameDb>>(emptyList())
    val handleError = MutableLiveData<ErrorState>()

    fun getGames(limit:Int,offset:Int){
        viewModelScope.launch(Dispatchers.IO) {
            val games = twitchRepository.getTopGames(limit,offset)
            when(games){
                is Result.Error ->{
                    pushGames(games.data?: emptyList())
                    if(games.data == null || games.data.isEmpty()){
                        handleError.postValue(ErrorState.EMPTYLIST)
                    }else {
                        handleError.postValue(ErrorState.INTERNETERROR)
                    }
                }
                is Result.Success -> pushGames(games.data)
            }
        }
    }

    private fun pushGames(games:List<TopGameDb>){
        val saveGames = topGames.value?.toMutableList()?: mutableListOf()
        saveGames.addAll(games)
        topGames.postValue(saveGames)
    }

}