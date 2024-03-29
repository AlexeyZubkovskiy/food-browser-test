package com.example.foodbrowser.presentation.uicommon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun DefaultMessage(modifier: Modifier = Modifier, text: String) {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White),
        text = text,
        color = Color.Black,
        style = MaterialTheme.typography.headlineSmall,
    )
}