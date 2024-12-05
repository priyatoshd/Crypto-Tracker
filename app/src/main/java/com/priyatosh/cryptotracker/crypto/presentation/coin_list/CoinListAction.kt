package com.priyatosh.cryptotracker.crypto.presentation.coin_list

import com.priyatosh.cryptotracker.crypto.presentation.models.CoinUi

sealed interface CoinListAction {
    data class OnCoinCLick(val coinUi: CoinUi): CoinListAction
    data object OnRefresh: CoinListAction
}