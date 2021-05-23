package com.kirvigen.templateapplication.data.api.repositories

import com.kirvigen.templateapplication.data.api.BaseRepository
import com.kirvigen.templateapplication.data.api.Result
import com.kirvigen.templateapplication.data.api.TwitchApi
import com.kirvigen.templateapplication.data.database.TopGamesDao
import com.kirvigen.templateapplication.data.models.TwitchGamesResponse
import com.kirvigen.templateapplication.data.models.database.TopGameDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TwitchRepository(
     val twitchApi: TwitchApi,
     val topGamesDao: TopGamesDao
) : BaseRepository("TwitchRepository") {

    suspend fun getTopGames(limit: Int, offset: Int): Result<List<TopGameDb>> {
        val response =
            safeApiCall(
                call = {
                    twitchApi.getTopGames(
                        "sd4grh0omdj9a31exnpikhrmsu3v46", limit, offset
                    ).await()
                },
                errorMessage = "Error load TopGames"
            ) ?: return Result.Error("",topGamesDao.getTopGames())
        val topGamesDb = toTopGameDb(response)
        CoroutineScope(Dispatchers.IO).launch(Dispatchers.IO) {
            topGamesDao.insert(topGamesDb)
        }
        return Result.Success(topGamesDb)
    }

    private fun toTopGameDb(twitchGamesResponse: TwitchGamesResponse): List<TopGameDb> {
        val list = mutableListOf<TopGameDb>()
        for (top in twitchGamesResponse.top) {
            list.add(
                TopGameDb(
                    top.game._id,
                    top.game.box.medium,
                    top.channels,
                    top.viewers,
                    top.game.name
                )
            )
        }
        return list
    }
}