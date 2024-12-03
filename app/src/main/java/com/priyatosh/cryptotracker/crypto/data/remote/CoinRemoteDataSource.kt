package com.priyatosh.cryptotracker.crypto.data.remote

import com.priyatosh.cryptotracker.core.domain.util.NetworkError
import com.priyatosh.cryptotracker.core.domain.util.Result
import com.priyatosh.cryptotracker.crypto.domain.model.Coin
import com.priyatosh.cryptotracker.crypto.domain.model.CoinPrice
import java.time.ZonedDateTime

interface CoinRemoteDataSource {
    suspend fun getCoins(): Result<List<Coin>, NetworkError>
    suspend fun getCoinPriceHistory(
        coinId: String,
        start: ZonedDateTime,
        end: ZonedDateTime
    ): Result<List<CoinPrice>, NetworkError>
}