package com.example.marcoslopes_rm82813.services

import com.example.marcoslopes_rm82813.models.Joke
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit

interface JokeInterface {

    @GET("random")
    fun getJoke() : Call<Joke>

}

class JokeConnection {

    private var retrofit = Retrofit.Builder()
        .baseUrl("https://api.chucknorris.io/jokes/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var service = retrofit.create(JokeInterface::class.java)

}