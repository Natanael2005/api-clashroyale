package com.example.clashroyale_api.model

/**
 * Project: Clash Royale - API
 * From: com.example.clashroyale_api.model
 * Created by: felip
 * On: 20/09/2026
 * All rights reserved: 2026
 */


data class ClashEvent(
    val eventTag: String,
    val title: String,
    val description: String? // Puede ser nulo según tu JSON
)