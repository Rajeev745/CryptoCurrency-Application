package com.example.cryptocurrency.presentation.coin_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.cryptocurrency.domain.model.Coin

// Composable function for displaying a single item in the coin list
@Composable
fun CoinListItem(
    coin: Coin,
    onItemClick: (Coin) -> Unit
) {

    // Create a Row layout for the coin item with specified modifiers
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(coin) }  // Make the entire row clickable and trigger onItemClick when clicked
            .padding(20.dp),  // Add padding to the row
        horizontalArrangement = Arrangement.SpaceBetween  // Arrange items horizontally with space between them
    ) {

        // Display the coin name, symbol, and rank with specified text style and overflow behavior
        Text(
            text = "${coin.rank}. ${coin.name} (${coin.symbol})",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black,
            overflow = TextOverflow.Ellipsis,
        )

        // Display the activity status of the coin with color-coded text, italic style, and alignment
        Text(
            text = if (coin.isActive) "active" else "inactive",
            color = if (coin.isActive) Color.Green else Color.Red,
            style = MaterialTheme.typography.bodySmall,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.End,
            modifier = Modifier.align(CenterVertically)  // Align the text vertically in the center
        )
    }
}