package com.example.cryptocurrency.presentation.coin_list

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cryptocurrency.presentation.Screen
import com.example.cryptocurrency.presentation.coin_list.components.CoinListItem

// Composable function for the CoinListScreen
@Composable
fun CoinListScreen(
    navController: NavController,
    viewModel: CoinListViewModel = hiltViewModel()
) {
    // Retrieve the current state from the CoinListViewModel
    val state = viewModel.state.value

    // Start defining a Box composable for layout
    Box(modifier = Modifier.fillMaxSize()) {

        // Create a LazyColumn for displaying a scrollable list of items
        LazyColumn(modifier = Modifier.fillMaxSize()) {

            // Iterate over the list of coins and display each as a CoinListItem
            items(state.isSuccess) { coin ->
                CoinListItem(
                    coin = coin,
                    onItemClick = {
                        Log.d("OnClick", "Clicked")
                        // Navigate to the CoinDetailScreen when an item is clicked
                        navController.navigate(Screen.CoinDetailScreen.route + "/${coin.id}")
                    })
            }
        }

        // Display an error message if the state has a non-blank error
        if (state.isError.isNotBlank()) {
            Text(
                text = state.isError,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center),
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Red,
                textAlign = TextAlign.Center
            )
        }

        // Display a circular progress indicator if data is currently being loaded
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}