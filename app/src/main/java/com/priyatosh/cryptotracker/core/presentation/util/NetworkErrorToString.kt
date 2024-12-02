package com.priyatosh.cryptotracker.core.presentation.util

import android.content.Context
import com.priyatosh.cryptotracker.R
import com.priyatosh.cryptotracker.core.domain.util.NetworkError

fun NetworkError.toString(context: Context): String {
    val resId =  when(this) {
        NetworkError.REQUEST_TIMEOUT -> R.string.error_request_timeout
        NetworkError.TOO_MANY_REQUESTS -> R.string.error_too_many_request
        NetworkError.NO_INTERNET -> R.string.error_no_internet
        NetworkError.SERVER_ERROR -> R.string.error_server
        NetworkError.SERIALIZATION -> R.string.error_serialization
        NetworkError.UNKNOWN -> R.string.error_unknown
    }
    return context.getString(R.string.text_network_error) + ": " + context.getString(resId)
}