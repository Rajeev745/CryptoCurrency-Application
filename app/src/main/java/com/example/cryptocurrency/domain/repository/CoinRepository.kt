package com.example.cryptocurrency.domain.repository

import com.example.cryptocurrency.data.remote.dto.CoinDetailDto
import com.example.cryptocurrency.data.remote.dto.CoinDto

/**
 * Interface defining methods to interact with the repository for retrieving information about coins.
 */
interface CoinRepository {

    /**
     * Asynchronously retrieves a list of CoinDto objects representing basic information about multiple coins.
     *
     * @return A [List] of [CoinDto] representing basic information about coins.
     */
    suspend fun getCoins(): List<CoinDto>

    /**
     * Asynchronously retrieves detailed information about a specific coin identified by its unique coinId.
     *
     * @param coinId The unique identifier of the coin for which detailed information is requested.
     * @return A [CoinDetailDto] containing detailed information about the specified coin.
     */
    suspend fun getCoinDetail(coinId: String): CoinDetailDto
}
