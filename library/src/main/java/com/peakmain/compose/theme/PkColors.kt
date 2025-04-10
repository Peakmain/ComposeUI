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
val error_A5534D= Color(0xFFA5534D)

fun pkColors(
    primary: Color = Color(0xFF1F401B),
    primaryVariant: Color = Color(0xB21F401B),
    secondary: Color = Color(0xFFB59E6D),//辅色
    secondaryVariant: Color = Color(0xFFDDCC9E),
    background: Color = Color(0xFFF8F9F3),
    surface: Color = color_FFFEFA,
    error: Color =error_A5534D,
    onPrimary: Color = color_FFFEFA,
    onSecondary: Color = Color(0xFF666666),//次要按钮上的文字颜色
    onBackground: Color =Color(0xFF333333),//背景颜色上的字体颜色
    onSurface: Color = Color(0xFF333333),//卡片上文字颜色
    onError: Color =  color_FFFEFA,//错误上的文字颜色
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