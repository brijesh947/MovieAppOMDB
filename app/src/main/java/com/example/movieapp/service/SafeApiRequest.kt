package com.example.movieapp.service

import android.util.Log
import com.example.movieapp.model.Movie
import retrofit2.Call
import retrofit2.Response
import java.io.IOException

abstract class SafeApiRequest {

    suspend fun<T: Any> apiRequest(call: suspend () -> Response<T>) : T? {
        val response = call.invoke()
        if(response.isSuccessful){
            return response.body()
        }else{
            //@todo handle api exception

            Log.e("Error ", "apiRequest: " + response.message())
            throw ApiException(response.code().toString())
            //return response.body()
        }
    }

}

class ApiException(message: String): IOException(message)