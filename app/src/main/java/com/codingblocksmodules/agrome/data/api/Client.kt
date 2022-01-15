package com.codingblocksmodules.agrome.data.api

import com.codingblocksmodules.agrome.util.NoConnectionInterceptor
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//making retrofit client
object Client {
    private val gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create()

    private var okHttpClient = OkHttpClient.Builder()
        .addInterceptor(NoConnectionInterceptor())
        .retryOnConnectionFailure(true)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://krishi-vyahan.herokuapp.com/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val api: GithubService = retrofit.create(GithubService::class.java)
}