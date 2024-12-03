package com.priyatosh.cryptotracker.crypto.data.remote

import com.priyatosh.cryptotracker.core.data.networking.constructUrl
import com.priyatosh.cryptotracker.core.data.networking.safeCall
import com.priyatosh.cryptotracker.core.domain.util.NetworkError
import com.priyatosh.cryptotracker.core.domain.util.Result
import com.priyatosh.cryptotracker.core.domain.util.map
import com.priyatosh.cryptotracker.crypto.data.mapper.toCoin
import com.priyatosh.cryptotracker.crypto.data.mapper.toCoinPrice
import com.priyatosh.cryptotracker.crypto.data.networking.dto.CoinPriceHistoryResponse
import com.priyatosh.cryptotracker.crypto.data.networking.dto.CoinsResponseDto
import com.priyatosh.cryptotracker.crypto.domain.model.Coin
import com.priyatosh.cryptotracker.crypto.domain.model.CoinPrice
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import java.time.ZoneId
import java.time.ZonedDateTime

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

    override suspend fun getCoinPriceHistory(
        coinId: String,
        start: ZonedDateTime,
        end: ZonedDateTime
    ): Result<List<CoinPrice>, NetworkError> {
        val startMillis = start
            .withZoneSameInstant(ZoneId.of("UTC"))
            .toInstant()
            .toEpochMilli()

        val endMillis = end
            .withZoneSameInstant(ZoneId.of("UTC"))
            .toInstant()
            .toEpochMilli()

        return safeCall<CoinPriceHistoryResponse> {
            httpClient.get(
                urlString = constructUrl("/assets/$coinId/history")
            ) {
                parameter("interval", "h6")
                parameter("start", startMillis)
                parameter("end", endMillis)
            }
        }.map { response ->
            response.data.map { it.toCoinPrice() }
        }
    }
}