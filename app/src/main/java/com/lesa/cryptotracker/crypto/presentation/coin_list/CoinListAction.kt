package com.lesa.cryptotracker.crypto.presentation.coin_list

import com.lesa.cryptotracker.crypto.presentation.models.CoinUi

sealed interface CoinListAction {
    data class OnCoinClick(val coinUi: CoinUi) : CoinListAction
}
