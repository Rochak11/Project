package com.example.schoolapp.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//ServiceBuilder

object ServiceBuilder {
    private const val BASE_URL =
        "http://192.168.1.112:3000/api/v1/"

    var token: String? = null

    private fun  getRetrofitClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun <T> buildService(serviceType: Class<T>): T {
        return getRetrofitClient().create(serviceType)
    }

    // Load image path in Service Builder class
    fun loadImagePath(): String {
        val arr = BASE_URL.split("/").toTypedArray()
        return arr[0] + "/" + arr[1] + arr[2] + "/uploads/"
    }
}