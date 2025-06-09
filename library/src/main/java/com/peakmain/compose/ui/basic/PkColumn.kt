package com.peakmain.compose.ui.basic

/**
 * author ：Peakmain
 * createTime：2025/6/4
 * mail:2726449200@qq.com
 * describe：
 */
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
inline fun PkColumn(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.White,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier
            .background(backgroundColor),
        verticalArrangement,
        horizontalAlignment,
        content
    )
}

@Preview
@Composable
fun PkColumnPreview() {
    PkColumn() {

    }
}

