package com.example.cryptocurrency.data.remote

import com.example.cryptocurrency.data.remote.dto.CoinDetailDto
import com.example.cryptocurrency.data.remote.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Retrofit interface for defining API endpoints related to coin information using the CoinPaprika service.
 */
interface CoinPaprikaApi {

    /**
     * Asynchronously retrieves a list of CoinDto objects representing basic information about multiple coins.
     *
     * @return A [List] of [CoinDto] representing basic information about coins.
     */
    @GET("v1/coins")
    suspend fun getCoins(): List<CoinDto>

    /**
     * Asynchronously retrieves detailed information about a specific coin identified by its unique coinId.
     *
     * @param coinId The unique identifier of the coin for which detailed information is requested.
     * @return A [CoinDetailDto] containing detailed information about the specified coin.
     */
    @GET("/v1/coins/{coinId}")
    suspend fun getCoinDetail(@Path("coinId") coinId: String): CoinDetailDto
}