package com.example.clashroyale_api.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

val RoyaleShapes = Shapes(
    small = RoundedCornerShape(12.dp),
    medium = RoundedCornerShape(18.dp),
    large = RoundedCornerShape(28.dp)
)

private val RoyaleColorScheme = darkColorScheme(
    primary = RoyaleBlue,
    onPrimary = RoyaleNavy,
    secondary = RoyaleGold,
    onSecondary = RoyaleNavy,
    tertiary = RoyaleMint,
    background = RoyaleNavy,
    onBackground = RoyaleText,
    surface = RoyaleInk,
    onSurface = RoyaleText,
    surfaceVariant = RoyalePanel,
    onSurfaceVariant = RoyaleMuted,
    outline = RoyaleStroke
)

@Composable
fun ClashRoyaleAPITheme(
    darkTheme: Boolean = true,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = RoyaleColorScheme,
        typography = Typography,
        shapes = RoyaleShapes,
        content = content
    )
}
