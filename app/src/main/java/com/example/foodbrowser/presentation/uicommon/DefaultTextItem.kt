package com.example.foodbrowser.presentation.uicommon

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun DefaultTextItem(
    text: String,
    modifier: Modifier = Modifier,
    scrollable: Boolean = false,
    contentAlignment: Alignment = Alignment.CenterStart
) {
    val scrollState = rememberScrollState()
    Box(
        modifier = modifier
            .height(42.dp)
            .border(BorderStroke(0.3.dp, if (isSystemInDarkTheme()) Color.White else Color.Black))
            .padding(all = 4.dp),
        contentAlignment = contentAlignment,
    ) {
        Text(
            modifier = if (scrollable) Modifier.horizontalScroll(scrollState) else Modifier,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Visible,
            text = text,
        )
    }
}