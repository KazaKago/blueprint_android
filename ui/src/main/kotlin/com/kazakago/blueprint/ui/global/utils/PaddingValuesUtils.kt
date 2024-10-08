package com.kazakago.blueprint.ui.global.utils

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalLayoutDirection

@Composable
operator fun PaddingValues.plus(other: PaddingValues): PaddingValues {
    val layoutDirection = LocalLayoutDirection.current
    return PaddingValues(
        top = calculateTopPadding() + other.calculateTopPadding(),
        start = calculateStartPadding(layoutDirection) + other.calculateStartPadding(layoutDirection),
        end = calculateEndPadding(layoutDirection) + other.calculateEndPadding(layoutDirection),
        bottom = calculateBottomPadding() + other.calculateBottomPadding(),
    )
}

@Composable
operator fun PaddingValues.minus(other: PaddingValues): PaddingValues {
    val layoutDirection = LocalLayoutDirection.current
    return PaddingValues(
        top = calculateTopPadding() - other.calculateTopPadding(),
        start = calculateStartPadding(layoutDirection) - other.calculateStartPadding(layoutDirection),
        end = calculateEndPadding(layoutDirection) - other.calculateEndPadding(layoutDirection),
        bottom = calculateBottomPadding() - other.calculateBottomPadding(),
    )
}
