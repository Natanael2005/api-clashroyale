package com.example.clashroyale_api.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clashroyale_api.model.ClashEvent
import com.example.clashroyale_api.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class EventsViewModel : ViewModel() {

    private val _events = MutableStateFlow<List<ClashEvent>>(emptyList())
    val events: StateFlow<List<ClashEvent>> = _events

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    // NUEVA VARIABLE: Guardará el texto del error para mostrarlo en pantalla
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    // REVISA ESTO: Asegúrate de pegar el token largo que generaste en la página de Clash Royale
    private val apiToken = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6IjI4YTMxOGY3LTAwMDAtYTFlYi03ZmExLTJjNzQzM2M2Y2NhNSJ9.eyJpc3MiOiJzdXBlcmNlbGwiLCJhdWQiOiJzdXBlcmNlbGw6Z2FtZWFwaSIsImp0aSI6Ijg3ZDhkMzM1LTc3ZTMtNDNkYi1iOGIyLTc2YTUwNmZkZTBmZSIsImlhdCI6MTc4OTkyOTEwNywic3ViIjoiZGV2ZWxvcGVyLzkyMDM5NWU0LWNjMmQtNGM3NC05MTYwLTE5NTg1MWU3ZDEwZSIsInNjb3BlcyI6WyJyb3lhbGUiXSwibGltaXRzIjpbeyJ0aWVyIjoiZGV2ZWxvcGVyL3NpbHZlciIsInR5cGUiOiJ0aHJvdHRsaW5nIn0seyJjaWRycyI6WyIxMjkuMjIyLjIwMS4xNTIiXSwidHlwZSI6ImNsaWVudCJ9XX0.dUPXZWWPQJkfL3jCR4Ary6jdwP5lXv_h25-H9gtZingVaPxB3JsBVJb41IVBT3NzGaOsQNJomHig9F9x5JuFCA"

    init {
        fetchEvents()
    }

    private fun fetchEvents() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null // Limpiamos errores previos
            try {
                val response = RetrofitClient.apiService.getEvents(apiToken)
                _events.value = response
            } catch (e: HttpException) {
                // Error 403 (Token inválido o IP incorrecta) o 404
                _errorMessage.value = "Error del servidor: Código ${e.code()}"
                Log.e("EventsViewModel", "Error HTTP: ${e.response()?.errorBody()?.string()}")
            } catch (e: Exception) {
                // Error de conversión de JSON o sin internet
                _errorMessage.value = "Error de datos o red: ${e.localizedMessage}"
                Log.e("EventsViewModel", "Excepción: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }
}