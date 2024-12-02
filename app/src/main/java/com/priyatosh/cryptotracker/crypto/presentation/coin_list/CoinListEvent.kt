package com.priyatosh.cryptotracker.crypto.presentation.coin_list

import com.priyatosh.cryptotracker.core.domain.util.NetworkError


sealed interface CoinListEvent {
    data class Error(val error: NetworkError): CoinListEvent
}