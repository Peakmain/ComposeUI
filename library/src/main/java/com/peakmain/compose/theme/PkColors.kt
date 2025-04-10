package com.peakmain.compose.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

/**
 * author ：Peakmain
 * createTime：2025/4/10
 * mail:2726449200@qq.com
 * describe：
 */
val color_FFFEFA=Color(0xFFFFFEFA)

fun pkColors(
    primary: Color = Color(0xFF1F401B),
    primaryVariant: Color = Color(0xFF677C64),
    secondary: Color = Color(0xFFB59E6D),//辅色
    secondaryVariant: Color = Color(0xFFDDCC9E),
    background: Color = Color(0xFFF8F9F3),
    surface: Color = color_FFFEFA,
    error: Color = Color(0xFFA5534D),
    onPrimary: Color = color_FFFEFA,
    onSecondary: Color = color_FFFEFA,
    onBackground: Color =color_FFFEFA,
    onSurface: Color = Color(0xFF333333),
    onError: Color =  Color(0xFFA5534D),
): Colors = Colors(
    primary,
    primaryVariant,
    secondary,
    secondaryVariant,
    background,
    surface,
    error,
    onPrimary,
    onSecondary,
    onBackground,
    onSurface,
    onError,
    true
)
internal val LocalPkColors = staticCompositionLocalOf { pkColors() }