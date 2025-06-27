package com.peakmain.compose.project.page.tools.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.unit.dp
import com.peakmain.compose.project.component.CpCell
import com.peakmain.compose.project.component.CpColumn
import com.peakmain.compose.project.component.CpTitle
import com.peakmain.compose.ui.image.PkImageView
import com.peakmain.compose.ui.title.PkTitle
import com.peakmain.compose.ui.title.PkTitleType
import com.peakmain.compose.utils.ImagePainterUtils

/**
 * author ：Peakmain
 * createTime：2025/6/27
 * mail:2726449200@qq.com
 * describe：
 */
@Composable
fun ImagePainterUtilsPage() {
    val imageUrls =
        arrayListOf(
            "https://inews.gtimg.com/om_bt/O3HPBXvE8uZQS2PlMcFZpECPpRqLPVJ-ti-LYB2z5JdiYAA/641",
            "https://coil-kt.github.io/coil/images/coil_logo_black.svg",
            "https://ww3.sinaimg.cn/mw690/005Ohgevjw1eobhokb9ekg30cc06y1l0.gif"
        )
    CpColumn("ImagePainterUtils工具类") {
        Row {
            Column {
                CpTitle("正常展示png/jpg/webp图片")
                PkImageView(
                    ImagePainterUtils.getPainter(imageUrls[0]),
                    modifier = Modifier.height(150.dp)
                )
            }
            Column {
                CpTitle("展示svg图片")
                PkImageView(
                    painter = ImagePainterUtils.getPainter(imageUrls[1]),
                    modifier = Modifier.height(150.dp)
                )
            }

        }
        Row {
            Column {
                CpTitle("展示gif图片")
                PkImageView(
                    painter = ImagePainterUtils.getPainter(imageUrls[2]),
                )

            }
            Column(modifier = Modifier.padding(horizontal = 18.dp)) {
                CpTitle("占位图")
                PkImageView(
                    painter = ImagePainterUtils.getPainter(null),
                    modifier = Modifier.size(150.dp)
                )
            }
        }
    }
}