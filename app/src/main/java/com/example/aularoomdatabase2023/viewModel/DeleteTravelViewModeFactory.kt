package com.example.aularoomdatabase2023.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aularoomdatabase2023.database.AppDatabase
import com.example.aularoomdatabase2023.repository.TravelRepository

class DeleteTravelViewModeFactory(val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val dao = AppDatabase.getDatabase(application).travelDao()
        val travelRepository = TravelRepository(dao)
        return DeleteTravelViewModel(travelRepository) as T
    }
}