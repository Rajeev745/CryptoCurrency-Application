package com.example.cryptocurrency.data.repository

import com.example.cryptocurrency.data.remote.CoinPaprikaApi
import com.example.cryptocurrency.data.remote.dto.CoinDetailDto
import com.example.cryptocurrency.data.remote.dto.CoinDto
import com.example.cryptocurrency.domain.repository.CoinRepository
import javax.inject.Inject

/**
 * Implementation of the [CoinRepository] interface that utilizes the CoinPaprikaApi to fetch information about coins.
 *
 * @property api An instance of [CoinPaprikaApi] used for making API calls to retrieve coin-related data.
 */
class CoinRepositoryImpl @Inject constructor(
    val api: CoinPaprikaApi
) : CoinRepository {

    /**
     * Asynchronously retrieves a list of CoinDto objects representing basic information about multiple coins.
     *
     * @return A [List] of [CoinDto] representing basic information about coins.
     */
    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    /**
     * Asynchronously retrieves detailed information about a specific coin identified by its unique coinId.
     *
     * @param coinId The unique identifier of the coin for which detailed information is requested.
     * @return A [CoinDetailDto] containing detailed information about the specified coin.
     */
    override suspend fun getCoinDetail(coinId: String): CoinDetailDto {
        return api.getCoinDetail(coinId = coinId)
    }
}