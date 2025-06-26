package com.peakmain.compose.project.page.basic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.peakmain.compose.project.page.basic.compose.ButtonPage
import com.peakmain.compose.project.page.basic.compose.CellPage
import com.peakmain.compose.project.page.basic.compose.ImagePage
import com.peakmain.compose.project.page.basic.compose.NavBarPage
import com.peakmain.compose.project.page.basic.compose.TitlePage

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

                4 -> {
                    //按钮组件
                    ButtonPage()
                }

                5 -> {
                    //图片组件
                    ImagePage()
                }
            }
        }
    }


}









