package com.example.cryptocurrency.domain.use_case.getcoins

import com.example.cryptocurrency.common.Resource
import com.example.cryptocurrency.data.remote.dto.toCoin
import com.example.cryptocurrency.domain.model.Coin
import com.example.cryptocurrency.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * Use case responsible for fetching the list of coins from the repository.
 *
 * @property repository The repository providing access to coin-related data.
 */
class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) {

    /**
     * Invokes the use case, initiating the process of fetching the list of coins.
     * The function emits a Flow of Resource, where Resource represents different states
     * of the data retrieval process (Loading, Success, Error).
     *
     * @return Flow<Resource<List<Coin>>> A Flow representing the state of the data retrieval process.
     */
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            // Emitting the Loading state before fetching the data
            emit(Resource.Loading())

            // Fetching the list of coins from the repository and mapping the DTOs to domain model
            val coin = repository.getCoins().map { it.toCoin() }

            // Emitting the Success state with the fetched coin data
            emit(Resource.Success(coin))
        } catch (e: HttpException) {
            // Emitting the Error state with the error message from the HTTP exception
            emit(Resource.Error(e.message().toString()))
        } catch (e: IOException) {
            // Emitting the Error state with the error message from the IO exception
            emit(Resource.Error(e.message.toString()))
        }
    }
}

/**
 * The operator fun invoke() is a convention in Kotlin that allows an object to be called as
 * if it were a function. In this case, it allows instances of GetCoinsUseCase to be invoked directly,
 * as if it were a function. The invoke function is the entry point for executing the use case,
 * and it returns a Flow that represents the asynchronous flow of data.
 * The use of the invoke operator makes the code cleaner and more concise when using instances of the GetCoinsUseCase class
 * */