package com.example.clashroyale_api.network

/**
 * Project: Clash Royale - API
 * From: com.example.clashroyale_api.network
 * Created by: felip
 * On: 18/09/2026
 * All rights reserved: 2026
 */

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://api.clashroyale.com/"

    val apiService: ClashRoyaleApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ClashRoyaleApi::class.java)
    }
}