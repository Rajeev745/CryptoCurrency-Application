package com.example.cryptocurrency.presentation.coindetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cryptocurrency.presentation.coindetail.components.CoinTag
import com.example.cryptocurrency.presentation.coindetail.components.TeamListItem

/**
 * Composable function for showing the coin detail screen
 *
 * @param viewModel is the model for handling the business logic for getting the coin detail
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CoinDetailScreen(
    viewModel: CoinDetailViewModel = hiltViewModel()
) {

    // Retrieve the current state from the CoinDetailViewModel
    val state = viewModel.state.value

    // Start defining the layout with a Box composable
    Box(modifier = Modifier.fillMaxSize()) {

        // Check if the coin detail retrieval was successful
        state.isSuccess?.let { coinDetail ->
            // Create a LazyColumn for the scrollable content with specified padding
            LazyColumn(
                modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(20.dp)
            ) {
                // Display coin details in a Row layout
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "${coinDetail.rank}. ${coinDetail.name} (${coinDetail.symbol})",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.weight(8f)
                        )
                        Text(
                            text = if (coinDetail.isActive) "active" else "inactive",
                            color = if (coinDetail.isActive) Color.Green else Color.Green,
                            modifier = Modifier
                                .weight(2f)
                                .align(CenterVertically),
                            fontStyle = FontStyle.Italic
                        )
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    // Display coin description with specified text style
                    Text(text = coinDetail.description, style = MaterialTheme.typography.bodyMedium)
                    Spacer(modifier = Modifier.height(15.dp))
                    // Display "Tags" section with specified text style
                    Text(
                        text = "Tags", style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    // Display coin tags in a FlowRow layout
                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        coinDetail.tags.forEach { tag ->
                            CoinTag(tag = tag)
                        }
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    // Display "Team Members" section with specified text style
                    Text(text = "Team Members", style = MaterialTheme.typography.bodyLarge)
                    Spacer(modifier = Modifier.height(15.dp))
                }

                // Display team members in a LazyColumn with TeamListItem and Divider
                items(coinDetail.team) { coinTeam ->
                    TeamListItem(
                        teamMember = coinTeam,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    )
                    Divider()
                }
            }
        }

        // Display an error message if the state has a non-blank error
        if (state.isError.isNotBlank()) {
            Text(
                text = state.isError,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Center),
                textAlign = TextAlign.Center,
                color = Color.Red
            )
        }

        // Display a circular progress indicator if data is currently being loaded
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Center))
        }
    }
}