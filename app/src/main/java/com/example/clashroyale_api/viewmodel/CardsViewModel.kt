package com.example.clashroyale_api.viewmodel

/**
 * Project: Clash Royale - API
 * From: com.example.clashroyale_api.viewmodel
 * Created by: felip
 * On: 18/09/2026
 * All rights reserved: 2026
 */

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clashroyale_api.model.Card
import com.example.clashroyale_api.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CardsViewModel : ViewModel() {

    // Estado reactivo para almacenar la lista de cartas
    private val _cards = MutableStateFlow<List<Card>>(emptyList())
    val cards: StateFlow<List<Card>> = _cards.asStateFlow()

    // Estado para saber si la información está cargando
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    // Pon tu Token generado en el portal de Clash Royale aquí.
    // IMPORTANTE: Debe llevar la palabra "Bearer " seguida de un espacio antes de tu clave.
    private val apiKey = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6IjI4YTMxOGY3LTAwMDAtYTFlYi03ZmExLTJjNzQzM2M2Y2NhNSJ9.eyJpc3MiOiJzdXBlcmNlbGwiLCJhdWQiOiJzdXBlcmNlbGw6Z2FtZWFwaSIsImp0aSI6IjViMjFkYzMyLWE1NTMtNGYyNy04MzhiLWMyMzFmMzljNjgxZiIsImlhdCI6MTc4OTc3NzEyNSwic3ViIjoiZGV2ZWxvcGVyLzkyMDM5NWU0LWNjMmQtNGM3NC05MTYwLTE5NTg1MWU3ZDEwZSIsInNjb3BlcyI6WyJyb3lhbGUiXSwibGltaXRzIjpbeyJ0aWVyIjoiZGV2ZWxvcGVyL3NpbHZlciIsInR5cGUiOiJ0aHJvdHRsaW5nIn0seyJjaWRycyI6WyIyMDQuMTk0LjExNi42OSJdLCJ0eXBlIjoiY2xpZW50In1dfQ.NMvhzMZJz5orOiftGjSB2A5GUZXNvwHDEUsxtWZUmayRbdZcUHv8dQQqi7SX9oIeUmpZV-2eV_1_q_5nvGoOHg"

    init {
        // Al instanciar el ViewModel, llamamos automáticamente a la API
        fetchCards()
    }

    private fun fetchCards() {
        // viewModelScope.launch ejecuta la petición en un hilo secundario para no congelar la pantalla
        viewModelScope.launch {
            _isLoading.value = true
            try {
                // Hacemos la petición a través de nuestro cliente Retrofit
                val response = RetrofitClient.apiService.getCards(apiKey)
                _cards.value = response.items
            } catch (e: Exception) {
                // Aquí se capturan los errores (ej. falta de internet)
                Log.e("API_ERROR", "Fallo la conexion: ${e.message}", e)            } finally {
                _isLoading.value = false
            }
        }
    }
}