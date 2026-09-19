package com.example.clashroyale_api.model

/**
 * Project: Clash Royale - API
 * From: com.example.clashroyale_api.model
 * Created by: felip
 * On: 18/09/2026
 * All rights reserved: 2026
 */

data class CardsResponse(
    val items: List<Card>
)

data class Card(
    val id: Int,
    val name: String,
    val maxLevel: Int,
    val maxEvolutionLevel: Int?,
    val elixirCost: Int,
    val rarity: String,
    val iconUrls: IconUrls
)

data class IconUrls(
    val medium: String,
    val heroMedium: String?,      // Agregado para aprovechar los recursos completos
    val evolutionMedium: String?  // Agregado para aprovechar los recursos completos
)