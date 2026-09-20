package com.example.clashroyale_api.network

/**
 * Project: Clash Royale - API
 * From: com.example.clashroyale_api.network
 * Created by: felip
 * On: 18/09/2026
 * All rights reserved: 2026
 */

import com.example.clashroyale_api.model.CardsResponse
import com.example.clashroyale_api.model.ClashEvent // Importamos el modelo que creamos
import retrofit2.http.GET
import retrofit2.http.Header

interface ClashRoyaleApi {
    @GET("v1/cards")
    suspend fun getCards(
        @Header("Authorization") token: String
    ): CardsResponse

    @GET("v1/events")
    suspend fun getEvents(
        @Header("Authorization") token: String
    ): List<ClashEvent>
}