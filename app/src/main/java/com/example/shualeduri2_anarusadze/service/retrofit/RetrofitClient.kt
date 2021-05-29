package com.example.shualeduri2_anarusadze.service.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private lateinit var retrofit: Retrofit

    fun initClient() {
        retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun <S> getService(serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }

    val apiService: ApiService
        get() = getService(ApiService::class.java)

}