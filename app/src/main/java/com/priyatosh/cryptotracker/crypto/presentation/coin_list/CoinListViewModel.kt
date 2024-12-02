package com.priyatosh.cryptotracker.crypto.presentation.coin_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.priyatosh.cryptotracker.core.domain.util.onError
import com.priyatosh.cryptotracker.core.domain.util.onSuccess
import com.priyatosh.cryptotracker.crypto.domain.CoinDataRepository
import com.priyatosh.cryptotracker.crypto.presentation.models.toCoinUi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

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
        when (action){
            is CoinListAction.onCoinCLick -> {

            }
        }
    }

    private fun loadCoins() {
        viewModelScope.launch {
            _state.update { it.copy(
                isLoading = true
            ) }

            coinDataRepository.getCoins()
                .onSuccess { coins ->
                    _state.update { it.copy(
                        isLoading = false,
                        coins = coins.map { it.toCoinUi() }
                    ) }
                }
                .onError { error ->
                    _state.update { it.copy(isLoading = false) }
                    _events.send(CoinListEvent.Error(error))
                }
        }
    }
}