package com.example.aularoomdatabase2023.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign

@Composable
fun AboutScreen() {
    Column() {
        Text(
            text = "Desenvolvido por: Júlia Gabriele Kreling",
            textAlign = TextAlign.Center
        )
    }
}