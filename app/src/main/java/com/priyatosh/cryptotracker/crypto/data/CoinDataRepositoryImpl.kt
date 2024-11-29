package com.priyatosh.cryptotracker.crypto.data

import com.priyatosh.cryptotracker.core.domain.util.NetworkError
import com.priyatosh.cryptotracker.core.domain.util.Result
import com.priyatosh.cryptotracker.crypto.data.remote.CoinRemoteDataSource
import com.priyatosh.cryptotracker.crypto.domain.CoinDataRepository
import com.priyatosh.cryptotracker.crypto.domain.model.Coin

class CoinDataRepositoryImpl(
    private val remoteDataSource: CoinRemoteDataSource
): CoinDataRepository {
    override suspend fun getCoins(): Result<List<Coin>, NetworkError> =
        remoteDataSource.getCoins()
}