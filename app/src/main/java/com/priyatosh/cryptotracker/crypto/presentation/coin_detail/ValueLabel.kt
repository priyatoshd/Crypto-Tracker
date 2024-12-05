package com.priyatosh.cryptotracker.crypto.presentation.coin_detail

import java.text.NumberFormat
import java.util.Locale

data class ValueLabel(
    val value: Float,
    val unit: String
) {
    fun formatted(): String {
        val formatter = NumberFormat.getInstance(Locale.getDefault()).apply {
            val fractionDigit = when {
                value > 1000 -> 0
                value in 2f..999f -> 2
                else -> 3
            }
            maximumFractionDigits = fractionDigit
            minimumFractionDigits = 0
        }
        return "${formatter.format(value)}$unit"
    }
}
