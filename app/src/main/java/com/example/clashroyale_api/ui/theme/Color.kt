package com.example.clashroyale_api.ui.theme

import androidx.compose.ui.graphics.Color

val RoyaleNavy = Color(0xFF0B1220)
val RoyaleInk = Color(0xFF111B2E)
val RoyalePanel = Color(0xFF17243A)
val RoyalePanelStrong = Color(0xFF1D2D47)
val RoyaleBlue = Color(0xFF5EA7FF)
val RoyaleBlueBright = Color(0xFF8AC5FF)
val RoyaleGold = Color(0xFFFFC857)
val RoyaleMint = Color(0xFF52D6B5)
val RoyaleText = Color(0xFFF4F7FC)
val RoyaleMuted = Color(0xFF9BAAC1)
val RoyaleStroke = Color(0xFF2A3B57)

val RarityCommon = Color(0xFF8EA2B8)
val RarityRare = Color(0xFFFFB45B)
val RarityEpic = Color(0xFFD18AF2)
val RarityLegendary = Color(0xFFFFD166)
val RarityChampion = Color(0xFF63D7FF)

val Purple80 = RoyaleBlueBright
val PurpleGrey80 = RoyaleMuted
val Pink80 = RoyaleGold
val Purple40 = RoyaleBlue
val PurpleGrey40 = RoyaleMuted
val Pink40 = RoyaleGold

fun rarityColor(rarity: String): Color = when (rarity.lowercase()) {
    "common" -> RarityCommon
    "rare" -> RarityRare
    "epic" -> RarityEpic
    "legendary" -> RarityLegendary
    "champion" -> RarityChampion
    else -> RoyaleBlue
}
