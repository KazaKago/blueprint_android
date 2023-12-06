package com.kazakago.blueprint.ui.global.hooks

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import java.net.UnknownHostException
import kotlin.coroutines.cancellation.CancellationException

@Composable
fun useErrorMessage(): (Throwable) -> String? {
    return remember {
        { throwable ->
            when (throwable) {
                is CancellationException -> null
                is UnknownHostException -> "ネットワークエラー、通信環境を見直して再度お試しください"
                else -> "不明なエラーが発生しました"
            }
        }
    }
}
