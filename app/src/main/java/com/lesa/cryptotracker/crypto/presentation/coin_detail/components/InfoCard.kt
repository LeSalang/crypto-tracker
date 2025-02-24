package com.lesa.cryptotracker.crypto.presentation.coin_detail.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewDynamicColors
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lesa.cryptotracker.crypto.presentation.models.previewCoin
import com.lesa.cryptotracker.ui.theme.CryptoTrackerTheme

@Composable
fun InfoCard(
    icon: ImageVector,
    formattedTextValue: String,
    title: String,
    contentColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,

    modifier: Modifier = Modifier,
) {
    val defaultTextStyle: TextStyle = LocalTextStyle.current.copy(
        textAlign = TextAlign.Center,
        fontSize = 18.sp,
        color = contentColor
    )
    Card(
        modifier = modifier
            .padding(8.dp)
            .shadow(
                elevation = 15.dp,
                shape = RectangleShape,
                spotColor = MaterialTheme.colorScheme.primary,
                ambientColor = MaterialTheme.colorScheme.primary,
            ),
        shape = RectangleShape,
        border = BorderStroke(
            1.dp,
            MaterialTheme.colorScheme.primary
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            contentColor = contentColor
        ),
    ) {
        AnimatedContent(
            targetState = icon,
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            label = "IconAnimation",
        ) { icon ->
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = contentColor,
                modifier = Modifier
                    .size(75.dp)
                    .padding(top = 16.dp)
            )
        }
        Spacer(Modifier.height(8.dp))
        AnimatedContent(
            targetState = formattedTextValue,
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            label = "IconAnimation",
        ) { formattedTextValue ->
            Text(
                text = formattedTextValue,
                style = defaultTextStyle,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            )
        }
        Spacer(Modifier.height(8.dp))
        Text(
            text = title,
            fontSize = 12.sp,
            fontWeight = FontWeight.Light,
            color = contentColor,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 16.dp)
                .padding(bottom = 8.dp)
        )
    }

}

@PreviewLightDark
@PreviewDynamicColors
@Composable
private fun InfoCardPreview() {
    CryptoTrackerTheme {
        InfoCard(
            icon = ImageVector.vectorResource(previewCoin.iconRes),
            formattedTextValue = previewCoin.marketCapUsd.formattedValue,
            title = previewCoin.name,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
        )
    }
}
