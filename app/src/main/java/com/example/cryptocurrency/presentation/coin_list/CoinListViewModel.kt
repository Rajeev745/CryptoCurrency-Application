package com.example.cryptocurrency.presentation.coin_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrency.common.Resource
import com.example.cryptocurrency.domain.use_case.getcoins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * ViewModel responsible for handling the logic related to the list of cryptocurrencies.
 * It communicates with the GetCoinsUseCase to fetch the list of coins and updates its state
 * accordingly to reflect loading, success, or error states.
 *
 * @property userCase The use case responsible for fetching the list of coins.
 */
@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val userCase: GetCoinsUseCase
) : ViewModel() {

    // Mutable state variable to hold the current state of the coin list
    private val _state = mutableStateOf(CoinStateList())

    // Immutable state variable exposing the state for external observation
    val state: State<CoinStateList> = _state

    // Initialization block that triggers the retrieval of the list of coins
    init {
        getCoinsList()
    }

    /**
     * Initiates the retrieval of the list of coins and updates the ViewModel's state accordingly.
     */
    private fun getCoinsList() {
        // Invoking the useCase to fetch the list of coins and observing the result using Kotlin Flow
        userCase().onEach { result ->
            // Handling different states of the result (Success, Error, Loading)
            when (result) {
                is Resource.Success -> {
                    // Updating the ViewModel's state with the successful result data
                    _state.value = CoinStateList(isSuccess = result.data ?: emptyList())
                }

                is Resource.Error -> {
                    // Updating the ViewModel's state with an error message, or a default message if none is provided
                    _state.value =
                        CoinStateList(isError = result.message ?: "Something Unexpected happen")
                }

                is Resource.Loading -> {
                    // Updating the ViewModel's state to indicate that the data is currently loading
                    _state.value = CoinStateList(isLoading = true)
                }
            }
        }
            .launchIn(viewModelScope) // Launching the Flow in the viewModelScope to handle the lifecycle of the ViewModel
    }
}