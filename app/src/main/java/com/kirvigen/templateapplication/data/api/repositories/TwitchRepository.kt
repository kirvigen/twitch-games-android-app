package com.kirvigen.templateapplication.data.api.repositories

import com.kirvigen.templateapplication.data.api.BaseRepository
import com.kirvigen.templateapplication.data.api.Result
import com.kirvigen.templateapplication.data.api.TwitchApi
import com.kirvigen.templateapplication.data.database.TopGamesDao
import com.kirvigen.templateapplication.data.database.TwitchGameDb
import com.kirvigen.templateapplication.data.models.Top
import com.kirvigen.templateapplication.data.models.TwitchGamesResponse
import com.kirvigen.templateapplication.data.models.database.TopGameDb

class TwitchRepository(
    private val twitchApi: TwitchApi,
    private val topGamesDao: TopGamesDao
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
            ) ?: return Result.Error("", topGamesDao.getTopGames())

        return Result.Success(toTopGameDb(response))
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