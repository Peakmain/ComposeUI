package com.peakmain.compose.project.compose.basic

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.peakmain.compose.basic.BasicFont
import com.peakmain.compose.project.R
import com.peakmain.compose.project.constants.PkColor
import com.peakmain.compose.theme.PkTheme
import com.peakmain.compose.ui.button.PkButton
import com.peakmain.compose.ui.button.PkButtonDefault
import com.peakmain.compose.ui.cell.PkCell
import com.peakmain.compose.ui.divier.PkDivider
import com.peakmain.compose.ui.title.PkNavBar
import com.peakmain.compose.ui.title.PkTitle
import com.peakmain.compose.ui.title.PkTitleType

/**
 * author ：Peakmain
 * createTime：2025/6/26
 * mail:2726449200@qq.com
 * describe：基础组件
 */
class BasicComponentActivity : ComponentActivity() {
    val type by lazy {
        intent.getIntExtra("type", 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            when (type) {
                1 -> {
                    //标题组件
                    TitlePage()
                }

                2 -> {
                    //导航栏组件
                    NavBarPage()
                }

                3 -> {
                    //单元格组件
                    CellPage()
                }
                4->{
                    //按钮组件
                    ButtonPage()
                }
            }
        }
    }




}
@Composable
 fun ButtonPage() {
    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        PkNavBar("按钮组件")
        PkButton(
            onClick = {

            }, elevation = null,
            colors = PkButtonDefault.transparentColor(),
            shape = PkTheme.shapes.small,
            border = BorderStroke(0.5.dp, Color(0xFFD4D4D5))
        ) {
            Text(
                "继续挑战", fontWeight = FontWeight.W500,
                fontSize = BasicFont.font_12
            )
        }
        PkButton(
            onClick = {

            }, elevation = null,
            colors = PkButtonDefault.buttonColors(),
            shape = PkTheme.shapes.small,
        ) {
            Text(
                "领取任务", fontWeight = FontWeight.W500,
                fontSize = BasicFont.font_12
            )
        }
    }
}
@Composable
fun CellPage() {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.background(PkColor.color_fill2)
    ) {
        PkNavBar("单元格组件")
        PkCell(
            "常用功能",
            PkTitleType.BigTitle3(),
            rightText = "展开",
            modifier = Modifier
                .padding(horizontal = 18.dp),
            color = Color(0xFF14401B)
        )
        // 基础用法（右侧带箭头图标）
        PkCell(
            text = "个人资料",
            type = PkTitleType.TitleBold1(),
            modifier = Modifier.padding(16.dp),
            rightContent = {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "进入",
                    tint = Color.Gray
                )
            }
        )

        // 复杂右侧内容（文字 + 开关）
        PkCell(
            text = "夜间模式",
            color = Color.Black,
            rightContent = {
                Text("已开启", color = Color.Gray)
                Spacer(Modifier.width(8.dp))
                Switch(
                    checked = true,
                    onCheckedChange = { /* 状态更新逻辑 */ }
                )
            }
        )
    }
}

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

/**
 * 标题组件
 */
@Composable
fun TitlePage() {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PkNavBar("标题组件")
        PkTitle("大标题1", PkTitleType.BigTitle1())
        PkTitle("大标题2", PkTitleType.BigTitle2())
        PkTitle("大标题3", PkTitleType.BigTitle3())
        PkDivider(modifier = Modifier.padding(vertical = 10.dp), isHorizontal = true)
        PkTitle("标题1加粗", PkTitleType.TitleBold1())
        PkTitle("标题1常规", PkTitleType.TitleNormal1())
        PkTitle("标题2加粗", PkTitleType.TitleBold2())
        PkTitle("标题2常规", PkTitleType.TitleNormal2())
        PkDivider(modifier = Modifier.padding(vertical = 10.dp), isHorizontal = true)
        PkTitle("小标题加粗", PkTitleType.SmallTitleBold())
        PkTitle("小标题常规", PkTitleType.SmallTitleNormal())
        PkDivider(modifier = Modifier.padding(vertical = 10.dp), isHorizontal = true)
        PkTitle("内文1加粗", PkTitleType.TextBold1())
        PkTitle("内文1正常", PkTitleType.TextNormal1())
        PkTitle("内文2加粗", PkTitleType.TextBold2())
        PkTitle("内文2正常", PkTitleType.TextNormal2())
    }
}