package com.priyatosh.cryptotracker.crypto.presentation.coin_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.priyatosh.cryptotracker.core.domain.util.onError
import com.priyatosh.cryptotracker.core.domain.util.onSuccess
import com.priyatosh.cryptotracker.crypto.domain.CoinDataRepository
import com.priyatosh.cryptotracker.crypto.presentation.coin_detail.DataPoint
import com.priyatosh.cryptotracker.crypto.presentation.models.CoinUi
import com.priyatosh.cryptotracker.crypto.presentation.models.toCoinUi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class CoinListViewModel(
    private val coinDataRepository: CoinDataRepository
): ViewModel() {

    private val _state = MutableStateFlow(CoinListState())
    val state = _state
        .onStart { loadCoins() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            CoinListState()
        )

    private val _events = Channel<CoinListEvent>()
    val events = _events.receiveAsFlow()

    fun onAction(action: CoinListAction) {
        when(action) {
            is CoinListAction.OnCoinCLick -> {
                selectCoin(action.coinUi)
            }
            CoinListAction.OnRefresh -> {
                loadCoins(isRefresh = true)
            }
        }
    }

    private fun selectCoin(coinUi: CoinUi) {
        _state.update { it.copy(selectedCoin = coinUi) }

        viewModelScope.launch {
            coinDataRepository
                .getCoinPriceHistory(
                    coinId = coinUi.id,
                    start = ZonedDateTime.now().minusDays(5),
                    end = ZonedDateTime.now()
                )
                .onSuccess { history ->
                    val dataPoints = history
                        .sortedBy { it.dateTime }
                        .map {
                            DataPoint(
                                x = it.dateTime.hour.toFloat(),
                                y = it.priceUsd.toFloat(),
                                xLabel = DateTimeFormatter
                                    .ofPattern("ha\nM/d")
                                    .format(it.dateTime)
                            )
                        }
                    _state.update {
                        it.copy(
                            selectedCoin = it.selectedCoin?.copy(
                                coinPriceHistory = dataPoints
                            )
                        )
                    }
                }
                .onError { error ->
                    _state.update { it.copy(isLoading = false) }
                    _events.send(CoinListEvent.Error(error))
                }
        }
    }

    private fun loadCoins(isRefresh: Boolean = false) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = !isRefresh,
                    isRefreshing = isRefresh
                )
            }

            coinDataRepository.getCoins()
                .onSuccess { coins ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isRefreshing = false,
                            coins = coins.map { coins ->
                                coins.toCoinUi()
                            }
                        )
                    }
                }
                .onError { error ->
                    _events.send(CoinListEvent.Error(error))
                }
        }
    }
}