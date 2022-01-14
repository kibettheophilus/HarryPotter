package com.anniekobia.harrypotter.utils

import android.util.Log
import java.io.IOException

suspend fun <T : Any> safeApiCall(
    call: suspend () -> NetworkResult<T>,
    errorMessage: String
): NetworkResult<T> =
    try {
        call.invoke()
    } catch (e: Exception) {
        e.message?.let { Log.e("FetchIssue: ", it) }
        NetworkResult.Error(IOException(errorMessage, e))
    }