package com.lesa.cryptotracker.core.presentation.util

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.lesa.cryptotracker.ui.theme.greenDark
import com.lesa.cryptotracker.ui.theme.greenLight
import com.lesa.cryptotracker.ui.theme.redDark
import com.lesa.cryptotracker.ui.theme.redLight

@Composable
fun chooseGreenRedColor(isChangePositive: Boolean): Pair<Color, Color> {
    val backgroundColor = if (isChangePositive) {
        if (isSystemInDarkTheme()) greenDark else greenLight
    } else {
        if (isSystemInDarkTheme()) redDark else redLight
    }
    val contentColor = if (isChangePositive) {
        if (isSystemInDarkTheme()) greenLight else greenDark
    } else {
        if (isSystemInDarkTheme()) redLight else redDark
    }
    return Pair(backgroundColor, contentColor)
}
