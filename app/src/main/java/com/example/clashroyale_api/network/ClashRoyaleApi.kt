package com.example.clashroyale_api.network

/**
 * Project: Clash Royale - API
 * From: com.example.clashroyale_api.network
 * Created by: felip
 * On: 18/09/2026
 * All rights reserved: 2026
 */

import com.example.clashroyale_api.model.CardsResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface ClashRoyaleApi {
    @GET("v1/cards")
    suspend fun getCards(
        @Header("Authorization") token: String
    ): CardsResponse
}