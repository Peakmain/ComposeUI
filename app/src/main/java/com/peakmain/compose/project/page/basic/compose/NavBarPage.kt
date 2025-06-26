package com.peakmain.compose.project.page.basic.compose

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.peakmain.compose.project.R
import com.peakmain.compose.ui.divier.PkDivider
import com.peakmain.compose.ui.title.PkNavBar

/**
 * author ：Peakmain
 * createTime：2025/6/26
 * mail:2726449200@qq.com
 * describe：
 */
/**
 * 导航栏组件
 */
@Composable
fun NavBarPage() {
    val context = LocalContext.current
    Column() {
        PkNavBar("NavBar组件")
        PkDivider(isHorizontal = true)
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            PkNavBar("默认NavBar导航栏")
            PkNavBar("我是长文本的NavBar导航栏，超长的，但是不显示右边按钮")
            PkNavBar(
                "我是长文本的NavBar导航栏，超长的，显示右边按钮",
                rightResource = R.drawable.ic_menu
            )
            PkNavBar("自定义返回事件", onBackClick = {
                Toast.makeText(context, "自定义返回事件", Toast.LENGTH_LONG).show()
            })
        }

    }
}