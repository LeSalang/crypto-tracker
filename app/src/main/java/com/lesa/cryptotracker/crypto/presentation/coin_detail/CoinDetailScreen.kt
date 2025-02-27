@file:OptIn(ExperimentalLayoutApi::class)

package com.lesa.cryptotracker.crypto.presentation.coin_detail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewDynamicColors
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lesa.cryptotracker.R
import com.lesa.cryptotracker.core.presentation.util.chooseGreenRedColor
import com.lesa.cryptotracker.crypto.presentation.coin_detail.components.InfoCard
import com.lesa.cryptotracker.crypto.presentation.coin_list.CoinListState
import com.lesa.cryptotracker.crypto.presentation.models.previewCoin
import com.lesa.cryptotracker.crypto.presentation.models.toDisplayableNumber
import com.lesa.cryptotracker.ui.theme.CryptoTrackerTheme

@Composable
fun CoinDetailScreen(
	state: CoinListState,
	modifier: Modifier = Modifier,
) {
	if (state.isLoading) {
		Box(
			modifier = modifier
				.fillMaxSize(),
			contentAlignment = Alignment.Center,
		) {
			CircularProgressIndicator()
		}
	} else if (state.selectedCoin != null) {
		val coin = state.selectedCoin
		Column(
			modifier = modifier
				.fillMaxSize()
				.verticalScroll(rememberScrollState())
				.padding(16.dp),
			horizontalAlignment = Alignment.CenterHorizontally,
		) {
			Icon(
				imageVector = ImageVector.vectorResource(id = coin.iconRes),
				contentDescription = coin.name,
				tint = MaterialTheme.colorScheme.primary,
				modifier = Modifier.size(100.dp),
			)
			Text(
				text = coin.name,
				fontSize = 40.sp,
				fontWeight = FontWeight.Bold,
				textAlign = TextAlign.Center,
				color = MaterialTheme.colorScheme.onSurfaceVariant,
			)
			Text(
				text = coin.symbol,
				fontSize = 20.sp,
				fontWeight = FontWeight.Light,
				textAlign = TextAlign.Center,
				color = MaterialTheme.colorScheme.onSurfaceVariant,
			)
			FlowRow(
				modifier = Modifier.fillMaxWidth(),
				horizontalArrangement = Arrangement.Center
			) {
				InfoCard(
                    icon = ImageVector.vectorResource(R.drawable.stock),
                    formattedTextValue = "$ ${coin.marketCapUsd.formattedValue}",
                    title = stringResource(id = R.string.market_cap),
                )
				InfoCard(
					icon = ImageVector.vectorResource(R.drawable.dollar),
					formattedTextValue = "$ ${coin.priceUsd.formattedValue}",
					title = stringResource(id = R.string.price),
				)
				val absoluteChangeFormatted = (coin.priceUsd.value * coin.changePercent24Hr.value / 100)
					.toDisplayableNumber()
				val isPositive = coin.changePercent24Hr.value > 0
				val contentColor = chooseGreenRedColor(isPositive)
				InfoCard(
					icon = ImageVector.vectorResource(
						if (isPositive) R.drawable.trending else R.drawable.trending_down
					),
					formattedTextValue = absoluteChangeFormatted.formattedValue,
					title = stringResource(id = R.string.change_last_24h),
					contentColor = contentColor.second,
				)
			}
			AnimatedVisibility(
				visible = coin.coinPriceHistory.isNotEmpty(),
			) {
				var selectedDataPoint by remember {
					mutableStateOf<DataPoint?>(null)
				}
				var labelWidth by remember {
					mutableFloatStateOf(0f)
				}
				var totalChartWidth by remember {
					mutableFloatStateOf(0f)
				}
				val amountOfVisibleDataPoints = if (labelWidth > 0) {
					((totalChartWidth - 2.5f * labelWidth) / labelWidth).toInt()
				} else {
					0
				}
				val startIndex = (coin.coinPriceHistory.lastIndex - amountOfVisibleDataPoints)
					.coerceAtLeast(0)
				LineChart(
					dataPoints = coin.coinPriceHistory,
					style = ChartStyle(
						chartLineColor = MaterialTheme.colorScheme.primary,
						unselectedColor = MaterialTheme.colorScheme.secondary.copy(
							alpha = 0.3f
						),
						selectedColor = MaterialTheme.colorScheme.primary,
						helperLineThicknessPx = 5f,
						axisLineThicknessPx = 5f,
						labelFontSize = 14.sp,
						minYLabelSpacing = 25.dp,
						verticalPadding = 8.dp,
						horizontalPadding = 8.dp,
						xAxisLabelSpacing = 8.dp,
					),
					visibleDataPointsIndices = startIndex..coin.coinPriceHistory.lastIndex,
					unit = "$",
					modifier = Modifier
						.fillMaxWidth()
						.aspectRatio(16/9f)
						.onSizeChanged {
							totalChartWidth = it.width.toFloat()
						},
					selectedDataPoint = selectedDataPoint,
					onSelectedDataPoint = {
						selectedDataPoint = it
					},
					onXLabelWidthChange = {
						labelWidth = it
					}
				)
			}
		}
	}
}

@PreviewLightDark
@PreviewDynamicColors
@Composable
private fun CoinDetailScreenPreview() {
	CryptoTrackerTheme {
		CoinDetailScreen(
			state = CoinListState(
				selectedCoin = previewCoin
			),
			modifier = Modifier
				.background(MaterialTheme.colorScheme.background)
		)
	}
}
