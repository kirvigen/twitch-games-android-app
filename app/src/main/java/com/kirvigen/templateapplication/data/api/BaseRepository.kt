package com.kirvigen.templateapplication.data.api

import android.util.Log
import retrofit2.Response
import java.io.IOException
import java.lang.Exception

open class BaseRepository(private val TAG:String = "BaseRepository"){

    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>, errorMessage: String): T? {

        val result : Result<T> = safeApiResult(call,errorMessage)
        var data : T? = null

        when(result) {
            is Result.Success ->
                data = result.data
            is Result.Error -> {
                Log.d(TAG, "$errorMessage & Exception - ${result.exception}")
            }
        }


        return data

    }

    private suspend fun <T: Any> safeApiResult(call: suspend ()-> Response<T>, errorMessage: String) : Result<T>{
        val response =try{
            call.invoke()
         }catch (e:Exception){
             null
         }
        if(response?.isSuccessful == true) return Result.Success(response.body()!!)

        return Result.Error(errorMessage)
    }
}

sealed class Result<out T: Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error<out T : Any>(val exception: String,val data: T? = null) : Result<T>()
}