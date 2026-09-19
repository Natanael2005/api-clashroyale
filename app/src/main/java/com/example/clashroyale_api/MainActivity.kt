package com.example.clashroyale_api

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.clashroyale_api.ui.theme.ClashRoyaleAPITheme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.clashroyale_api.navigation.AppNavigation
import com.example.clashroyale_api.ui.theme.ClashRoyaleAPITheme // Tu tema autogenerado

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ClashRoyaleAPITheme {
                // Surface es el contenedor principal que usa los colores de tu tema
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // ¡Aquí mandamos a llamar a nuestro director de orquesta!
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ClashRoyaleAPITheme {
        Greeting("Android")
    }
}