package com.example.cryptocurrency.presentation.coin_list

import com.example.cryptocurrency.domain.model.Coin

data class CoinStateList(
    var isLoading: Boolean = false,
    var isError: String = "",
    var isSuccess: List<Coin> = emptyList()
)