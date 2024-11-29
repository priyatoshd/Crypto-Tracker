package com.priyatosh.cryptotracker.crypto.presentation.coin_list

import androidx.compose.runtime.Immutable
import com.priyatosh.cryptotracker.crypto.domain.model.Coin
import com.priyatosh.cryptotracker.crypto.presentation.models.CoinUi

@Immutable  //Signifies that this class itself never changes, if it changes the whole instance is replaced
data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<CoinUi> = emptyList(),
    val selectedCoin: Coin? = null,
)
