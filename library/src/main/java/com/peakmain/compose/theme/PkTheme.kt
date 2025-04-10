package com.peakmain.compose.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import com.peakmain.compose.ui.button.LocalPkShapes
import com.peakmain.compose.ui.button.PkShapes

/**
 * author ：Peakmain
 * createTime：2025/4/10
 * mail:2726449200@qq.com
 * describe：
 */
object PkTheme {
    val colors: Colors
        @Composable
        @ReadOnlyComposable
        get() = LocalPkColors.current

    val shapes: PkShapes
        @Composable
        @ReadOnlyComposable
        get() = LocalPkShapes.current
}