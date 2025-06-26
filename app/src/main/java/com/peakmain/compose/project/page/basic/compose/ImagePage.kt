package com.peakmain.compose.project.page.basic.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.peakmain.compose.project.R
import com.peakmain.compose.ui.image.PkImageView
import com.peakmain.compose.ui.title.PkNavBar
import com.peakmain.compose.ui.title.PkTitle
import com.peakmain.compose.ui.title.PkTitleType
import com.peakmain.compose.utils.ImagePainterUtils

/**
 * author ：Peakmain
 * createTime：2025/6/26
 * mail:2726449200@qq.com
 * describe：
 */
@Composable
fun ImagePage() {
    val imageUrl="https://coil-kt.github.io/coil/images/coil_logo_black.svg"
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        PkNavBar("图片组件")
        Column {
            PkTitle("加载本地图片", type = PkTitleType.BigTitle3())
            PkImageView(painter = painterResource(R.drawable.portrair), modifier = Modifier.size(80.dp))
        }
        Column {
            PkTitle("加载网络图片", type = PkTitleType.BigTitle3())
            PkImageView(painter = ImagePainterUtils.getPainter(imageUrl), modifier = Modifier.size(80.dp))
        }
        Column {
            PkTitle("加载网络图片，并修改颜色为红色", type = PkTitleType.BigTitle3())
            PkImageView(painter = ImagePainterUtils.getPainter(imageUrl), tintColor = Color.Red,modifier = Modifier.size(80.dp))
        }
    }
}