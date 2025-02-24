package com.lesa.cryptotracker.crypto.presentation.coin_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewDynamicColors
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lesa.cryptotracker.core.presentation.util.chooseGreenRedColor
import com.lesa.cryptotracker.crypto.presentation.models.DisplayableNumber
import com.lesa.cryptotracker.ui.theme.CryptoTrackerTheme

@Composable
fun PriceChange(
    changePercent24Hr: DisplayableNumber,
    modifier: Modifier = Modifier,
) {
    val isChangePositive = changePercent24Hr.value >= 0
    val (backgroundColor, contentColor) = chooseGreenRedColor(isChangePositive)
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(100f))
            .background(backgroundColor)
            .padding(vertical = 4.dp, horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = if (isChangePositive) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
            contentDescription = null,
            modifier = Modifier.size(20.dp),
            tint = contentColor,
        )
        Text(
            text = "${changePercent24Hr.formattedValue} %",
            color = contentColor,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
        )
    }
}

@PreviewLightDark
@PreviewDynamicColors
@Composable
private fun PriceChangePreview() {
    CryptoTrackerTheme {
        PriceChange(
            changePercent24Hr = DisplayableNumber(
                value = 3.16,
                formattedValue = "3.16"
            )
        )
    }
}
