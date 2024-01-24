package com.example.cryptocurrency.presentation.coindetail.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

// Composable function for showing the tags on coin detail page
@Composable
fun CoinTag(tag: String) {
    Box(
        modifier = Modifier
            .padding(10.dp)
            .border(width = 1.dp, shape = RoundedCornerShape(100.dp), color = Color.Green)
    ) {
        Text(
            text = tag,
            color = Color.Green,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}