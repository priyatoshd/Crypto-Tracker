package com.priyatosh.cryptotracker.crypto.presentation.coin_list

import com.priyatosh.cryptotracker.crypto.presentation.models.CoinUi

sealed interface CoinListAction {
    data class onCoinCLick(val coinUi: CoinUi): CoinListAction
}