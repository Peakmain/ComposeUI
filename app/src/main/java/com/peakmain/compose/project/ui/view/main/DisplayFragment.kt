package com.peakmain.compose.project.ui.view.main

/**
 * author ：Peakmain
 * createTime：2022/5/2
 * mail:2726449200@qq.com
 * describe：
 */
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.peakmain.compose.basic.BasicSpace
import com.peakmain.compose.library.TopAppBarCenter
import com.peakmain.compose.project.component.CpCell
import com.peakmain.compose.project.page.display.DisplayComponentActivity
import com.peakmain.compose.project.ui.theme.Color_149EE7
import com.peakmain.compose.project.ui.theme.Color_2DCDF5

@Composable
fun ProjectFragment() {
    val context = LocalContext.current
    val intent = Intent(context, DisplayComponentActivity::class.java)
    val lists = arrayListOf("网格布局", "分割线", "流式布局", "轮播图", "高亮文本", "瀑布流")
    TopAppBarCenter(
        title = {
            Text(text = "展示组件", color = Color.White)
        },
        isImmersive = true,
        modifier = Modifier.background(Brush.linearGradient(listOf(Color_149EE7, Color_2DCDF5)))
    ) {

        Column(
            modifier = Modifier
                .padding(BasicSpace.space_18)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(BasicSpace.space_18),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            lists.forEachIndexed { index, item ->
                CpCell(item, intent, index + 1)
            }
        }
    }
}



