package com.priyatosh.cryptotracker.di

import com.priyatosh.cryptotracker.core.data.networking.HttpClientFactory
import com.priyatosh.cryptotracker.crypto.data.CoinDataRepositoryImpl
import com.priyatosh.cryptotracker.crypto.data.remote.CoinRemoteDataSource
import com.priyatosh.cryptotracker.crypto.data.remote.CoinRemoteDataSourceImpl
import com.priyatosh.cryptotracker.crypto.domain.CoinDataRepository
import com.priyatosh.cryptotracker.crypto.presentation.coin_list.CoinListViewModel
import io.ktor.client.engine.cio.CIO
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    single { HttpClientFactory.create(CIO.create()) }
    singleOf(::CoinRemoteDataSourceImpl).bind<CoinRemoteDataSource>()
    singleOf(::CoinDataRepositoryImpl).bind<CoinDataRepository>()

    viewModelOf(::CoinListViewModel)
}