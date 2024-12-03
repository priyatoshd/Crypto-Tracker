package com.priyatosh.cryptotracker

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.priyatosh.cryptotracker.core.presentation.util.ObserveAsEvents
import com.priyatosh.cryptotracker.core.presentation.util.toString
import com.priyatosh.cryptotracker.crypto.presentation.coin_detail.CoinDetailScreen
import com.priyatosh.cryptotracker.crypto.presentation.coin_list.CoinListEvent
import com.priyatosh.cryptotracker.crypto.presentation.coin_list.CoinListScreen
import com.priyatosh.cryptotracker.crypto.presentation.coin_list.CoinListViewModel
import com.priyatosh.cryptotracker.ui.theme.CryptoTrackerTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CryptoTrackerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val viewmodel = koinViewModel<CoinListViewModel>()
                    val state by viewmodel.state.collectAsStateWithLifecycle()

                    val context = LocalContext.current
                    ObserveAsEvents(events = viewmodel.events) { event ->
                        when (event) {
                            is CoinListEvent.Error -> Toast.makeText(
                                context,
                                event.error.toString(context),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    when {
                        state.selectedCoin != null -> {
                            CoinDetailScreen(
                                state = state,
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                        else -> {
                            CoinListScreen(
                                state = state,
                                onAction = viewmodel::onAction,
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                    }
                }
            }
        }
    }
}