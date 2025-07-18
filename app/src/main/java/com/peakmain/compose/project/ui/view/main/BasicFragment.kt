package com.peakmain.compose.project.ui.view.main

/**
 * author ：Peakmain
 * createTime：2022/5/2
 * mail:2726449200@qq.com
 * describe：
 */
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.peakmain.compose.project.page.basic.BasicComponentActivity
import com.peakmain.compose.project.ui.theme.Color_149EE7
import com.peakmain.compose.project.ui.theme.Color_2DCDF5
import com.peakmain.compose.ui.cell.PkCell
import com.peakmain.compose.ui.title.PkTitleType

@Composable
fun HomeFragment() {
    val context = LocalContext.current
    val intent=Intent(context,BasicComponentActivity::class.java)
    TopAppBarCenter(
        title = {
            Text(text = "首页", color = Color.White)
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
            PkCell(text = "标题组件", modifier = Modifier.clickable {
                intent.putExtra("type",1)
                context.startActivity(intent)
            }, type = PkTitleType.TitleBold1())
            PkCell(text = "NavBar导航栏", modifier = Modifier.clickable {
                intent.putExtra("type",2)
                context.startActivity(intent)
            }, type = PkTitleType.TitleBold1())

            PkCell(text = "单元格组件", modifier = Modifier.clickable {
                intent.putExtra("type",3)
                context.startActivity(intent)
            }, type = PkTitleType.TitleBold1())
            PkCell(text = "按钮组件", modifier = Modifier.clickable {
                intent.putExtra("type",4)
                context.startActivity(intent)
            }, type = PkTitleType.TitleBold1())
            PkCell(text = "图片组件", modifier = Modifier.clickable {
                intent.putExtra("type",5)
                context.startActivity(intent)
            }, type = PkTitleType.TitleBold1())
        }
    }
}

