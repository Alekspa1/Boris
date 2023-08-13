package com.example.boris

import com.example.boris.APIClass.MoviesClass
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiInterface {

    @GET("v1/movie/random")
    @Headers("X-API-KEY: 2WF3XHY-ME7MCSS-NYM10FY-E6NN1WE")

    fun getUser() : Call<MoviesClass> // В скобках ключ Api

    companion object {
        var BASE_URL = "https://api.kinopoisk.dev/"
        fun create() : ApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)

        }
    }


}