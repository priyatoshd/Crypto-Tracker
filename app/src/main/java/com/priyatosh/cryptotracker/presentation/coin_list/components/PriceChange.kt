package com.priyatosh.cryptotracker.presentation.coin_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.priyatosh.cryptotracker.presentation.models.DisplayableNumber
import com.priyatosh.cryptotracker.ui.theme.CryptoTrackerTheme
import com.priyatosh.cryptotracker.ui.theme.greenBackground

@Composable
fun PriceChangeLabel(
    change: DisplayableNumber,
    modifier: Modifier = Modifier
) {
    val (contentBackground, contentColor, icon) = if (change.value < 0.0)
        Triple(
            MaterialTheme.colorScheme.errorContainer,
            MaterialTheme.colorScheme.onErrorContainer,
            Icons.Default.KeyboardArrowDown
        )
    else
        Triple(
            greenBackground,
            Color.Green,
            Icons.Default.KeyboardArrowUp
        )

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(100f))
            .background(contentBackground)
            .padding(horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(20.dp),
            tint = contentColor
        )
        Text(
            text = "${change.formattedString} %",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = contentColor,
                fontWeight = FontWeight.Medium
            )
        )
    }
}

@PreviewLightDark
@Composable
private fun PriceChangeLabelPreview() {
    CryptoTrackerTheme {
        PriceChangeLabel(
            change = DisplayableNumber(
                value = 2.43,
                formattedString = "2.43"
            )
        )
    }
    
}