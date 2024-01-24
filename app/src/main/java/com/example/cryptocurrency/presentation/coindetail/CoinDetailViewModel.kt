package com.example.cryptocurrency.presentation.coindetail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrency.common.Constants.PARAM_COIN_ID
import com.example.cryptocurrency.common.Resource
import com.example.cryptocurrency.domain.use_case.getcoin.GetCoinDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
// CoinDetailViewModel class definition that extends ViewModel and is injected with GetCoinDetailUseCase
class CoinDetailViewModel @Inject constructor(
    private val useCase: GetCoinDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    // Mutable state variable to hold the current state of CoinDetail
    private val _state = mutableStateOf(CoinStateDetail())

    // Immutable state variable exposing the state for external observation
    val state: State<CoinStateDetail> = _state

    // Initialization block that checks if a coinId is present in the SavedStateHandle and retrieves the coin details
    init {
        savedStateHandle.get<String>(PARAM_COIN_ID)?.let { coinId ->
            getCoinDetail(coinId)
        }
    }

    /**
     * Initiates the retrieval of coin details based on the provided coinId.
     * If a coinId is present in the SavedStateHandle, this method triggers the useCase
     * to fetch the coin details and updates the ViewModel's state accordingly.
     *
     * @param id The unique identifier for the cryptocurrency whose details are to be fetched.
     */
    private fun getCoinDetail(id: String) {
        // Invoking the useCase and observing the result using Kotlin Flow
        useCase(id).onEach { result ->
            // Handling different states of the result (Loading, Error, Success)
            when (result) {
                is Resource.Loading -> {
                    // Updating the state to indicate loading
                    _state.value = CoinStateDetail(isLoading = true)
                }

                is Resource.Error -> {
                    // Updating the state with an error message if applicable
                    _state.value =
                        CoinStateDetail(isError = result.message ?: "Something Went wrong")
                }

                is Resource.Success -> {
                    // Updating the state with the successful result data
                    _state.value = CoinStateDetail(isSuccess = result.data)
                }
            }
        }
            .launchIn(viewModelScope) // Launching the Flow in the viewModelScope to handle the lifecycle of the ViewModel
    }
}
