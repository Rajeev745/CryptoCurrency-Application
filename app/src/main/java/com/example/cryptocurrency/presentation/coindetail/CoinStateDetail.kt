package com.example.cryptocurrency.presentation.coindetail

import com.example.cryptocurrency.domain.model.CoinDetail

data class CoinStateDetail (
    var isLoading: Boolean = false,
    var isSuccess: CoinDetail? = null,
    var isError: String = ""
)