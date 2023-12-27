package com.example.foodbrowser.presentation.uicommon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


@Composable
fun DefaultErrorState(modifier: Modifier = Modifier, text: String) {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Red),
        style = MaterialTheme.typography.headlineSmall,
        text = text,
        color = Color.Black,
    )
}