package com.peakmain.compose.ui.image

/**
 * author ：Peakmain
 * createTime：2025/4/17
 * mail:2726449200@qq.com
 * describe：
 */
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun PkImageView(
    painter: Painter,
    modifier: Modifier = Modifier,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Crop,
    alpha: Float = DefaultAlpha,
    tintColor: Color? = null
) {
    Image(
        painter = painter,
        contentDescription = null,
        modifier, alignment,
        contentScale = contentScale,
        alpha = alpha,
        colorFilter = if (tintColor != null) ColorFilter.tint(tintColor) else null,
    )
}
