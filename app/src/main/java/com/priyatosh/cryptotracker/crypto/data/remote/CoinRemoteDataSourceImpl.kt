package com.priyatosh.cryptotracker.crypto.data.remote

import com.priyatosh.cryptotracker.core.data.networking.constructUrl
import com.priyatosh.cryptotracker.core.data.networking.safeCall
import com.priyatosh.cryptotracker.core.domain.util.NetworkError
import com.priyatosh.cryptotracker.core.domain.util.Result
import com.priyatosh.cryptotracker.core.domain.util.map
import com.priyatosh.cryptotracker.crypto.data.mapper.toCoin
import com.priyatosh.cryptotracker.crypto.data.networking.dto.CoinsResponseDto
import com.priyatosh.cryptotracker.crypto.domain.model.Coin
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class CoinRemoteDataSourceImpl(
    private val httpClient: HttpClient
): CoinRemoteDataSource {
    override suspend fun getCoins(): Result<List<Coin>, NetworkError> {
        return safeCall<CoinsResponseDto> {
            httpClient.get(
                urlString = constructUrl("/assets")
            )
        }.map { coinsResponse ->
            coinsResponse.data.map { it.toCoin() }
        }
    }
}