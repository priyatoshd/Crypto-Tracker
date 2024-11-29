package com.priyatosh.cryptotracker.crypto.domain

import com.priyatosh.cryptotracker.core.domain.util.NetworkError
import com.priyatosh.cryptotracker.core.domain.util.Result
import com.priyatosh.cryptotracker.crypto.domain.model.Coin

interface CoinDataRepository {
    suspend fun getCoins(): Result<List<Coin>, NetworkError>
}