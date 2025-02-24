package com.lesa.cryptotracker.crypto.presentation.coin_list

import com.lesa.cryptotracker.core.domain.util.NetworkError

sealed interface CoinListEvent {
    data class Error(val error: NetworkError) : CoinListEvent
}
