package com.peakmain.compose.project.page.basic.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.peakmain.compose.basic.BasicFont
import com.peakmain.compose.theme.PkTheme
import com.peakmain.compose.ui.button.PkButton
import com.peakmain.compose.ui.button.PkButtonDefault
import com.peakmain.compose.ui.title.PkNavBar

/**
 * author ：Peakmain
 * createTime：2025/6/26
 * mail:2726449200@qq.com
 * describe：
 */
/**
 * 按钮组件
 */
@Composable
fun ButtonPage() {
    Column {
        PkNavBar("按钮组件")
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(top = 20.dp, start = 20.dp)
        ) {
            PkButton(
                onClick = {

                }, elevation = null,
                colors = PkButtonDefault.transparentColor(),
                shape = PkTheme.shapes.medium,
                border = BorderStroke(0.5.dp, Color(0xFFD4D4D5))
            ) {
                Text(
                    "继续挑战", fontWeight = FontWeight.W500,
                    fontSize = BasicFont.font_12
                )
            }
            PkButton(
                onClick = {

                },
                elevation = null,
                colors = PkButtonDefault.buttonColors(),
                shape = PkTheme.shapes.medium,
            ) {
                Text(
                    "领取任务", fontWeight = FontWeight.W500,
                    fontSize = BasicFont.font_12
                )
            }
        }
    }
}