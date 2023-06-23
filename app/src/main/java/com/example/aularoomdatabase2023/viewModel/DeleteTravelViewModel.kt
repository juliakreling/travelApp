package com.example.aularoomdatabase2023.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aularoomdatabase2023.entity.Travel
import com.example.aularoomdatabase2023.repository.TravelRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class DeleteTravelViewModel(private val travelRepository: TravelRepository): ViewModel() {
    private val _toastMessage = MutableSharedFlow<String>()
    val toastMessage = _toastMessage.asSharedFlow()

    fun deleteTravel(userID: Int, travel: Travel){
        try {
            travelRepository.delete(travel)
        } catch (e: Exception){
            viewModelScope.launch {
                _toastMessage.emit(e.message?: "Unknown error")
            }
        }
    }
}