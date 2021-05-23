package com.kirvigen.templateapplication.data.api

import com.kirvigen.templateapplication.data.models.TwitchGamesResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query


interface TwitchApi {

    @GET("games/top")
    fun getTopGames(
        @Header("Client-ID") client_id:String,
        @Query("limit") limit:Int,
        @Query("offset") offset:Int,
        @Header("Accept") accept:String = "application/vnd.twitchtv.v5+json"
    ):Deferred<Response<TwitchGamesResponse>>

}