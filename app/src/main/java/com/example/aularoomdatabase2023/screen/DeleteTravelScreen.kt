package com.example.aularoomdatabase2023.screen

import android.app.Application
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aularoomdatabase2023.viewModel.DeleteTravelViewModeFactory
import com.example.aularoomdatabase2023.viewModel.DeleteTravelViewModel

@Composable
fun DeleteTravelScreen (userId: Int){
    val application = LocalContext.current.applicationContext as Application
    val viewModel: DeleteTravelViewModel = viewModel(
        factory = DeleteTravelViewModeFactory(application)
    )

    Text(
        text = "PRECISO PASSAR O TRAVELID AQUI!!!. viagem excluida.... id viagem: ${userId.toString()}",
        textAlign = TextAlign.Center
    )

//    viewModel.deleteTravel(userId, travel)
}