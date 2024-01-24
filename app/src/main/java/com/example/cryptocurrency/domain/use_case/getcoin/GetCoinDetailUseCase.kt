package com.example.cryptocurrency.domain.use_case.getcoin

import com.example.cryptocurrency.common.Resource
import com.example.cryptocurrency.data.remote.dto.toCoinDetail
import com.example.cryptocurrency.domain.model.CoinDetail
import com.example.cryptocurrency.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * Use case responsible for fetching detailed information about a specific cryptocurrency.
 *
 * @property repository The repository providing access to coin-related data.
 */
class GetCoinDetailUseCase @Inject constructor(
    private val repository: CoinRepository
) {

    /**
     * Invokes the use case, initiating the process of fetching detailed information about a specific cryptocurrency.
     * The function emits a Flow of Resource, where Resource represents different states
     * of the data retrieval process (Loading, Success, Error).
     *
     * @param id The unique identifier of the cryptocurrency for which detailed information is to be fetched.
     * @return Flow<Resource<CoinDetail>> A Flow representing the state of the data retrieval process.
     */
    operator fun invoke(id: String): Flow<Resource<CoinDetail>> = flow {
        try {
            // Emitting the Loading state before fetching the data
            emit(Resource.Loading())

            // Fetching the detailed information about the cryptocurrency from the repository
            // and converting the DTO to the domain model (CoinDetail)
            val coinDetail = repository.getCoinDetail(id).toCoinDetail()

            // Emitting the Success state with the fetched coin detail data
            emit(Resource.Success(coinDetail))
        } catch (e: HttpException) {
            // Emitting the Error state with the error message from the HTTP exception
            emit(Resource.Error(e.message()))
        } catch (e: IOException) {
            // Emitting the Error state with the error message from the IO exception
            emit(Resource.Error(e.message))
        }
    }
}