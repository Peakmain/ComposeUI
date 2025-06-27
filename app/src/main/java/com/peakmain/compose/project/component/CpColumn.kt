package com.peakmain.compose.project.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.peakmain.compose.project.constants.PkColor
import com.peakmain.compose.ui.title.PkNavBar

/**
 * author ：Peakmain
 * createTime：2025/6/27
 * mail:2726449200@qq.com
 * describe：
 */
@Composable
fun CpColumn(title:String, content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = Modifier
            .background(PkColor.color_fill1)
            .fillMaxSize()
    ) {
        PkNavBar(title)
        content()
    }
}