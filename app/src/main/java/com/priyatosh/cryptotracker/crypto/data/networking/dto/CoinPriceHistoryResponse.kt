package com.priyatosh.cryptotracker.crypto.data.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class CoinPriceHistoryResponse(
    val data: List<CoinPriceDto>
)