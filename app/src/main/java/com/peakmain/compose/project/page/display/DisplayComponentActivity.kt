package com.peakmain.compose.project.page.display

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.peakmain.compose.project.page.display.compose.DividerPage
import com.peakmain.compose.project.page.display.compose.FlowRowPage
import com.peakmain.compose.project.page.display.compose.GridPage

/**
 * author ：Peakmain
 * createTime：2025/6/26
 * mail:2726449200@qq.com
 * describe：展示组件
 */
class DisplayComponentActivity:ComponentActivity() {
    val type by lazy {
        intent.getIntExtra("type",0)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            when(type){
                1->{
                    //网格布局
                    GridPage()
                }
                2->{
                    //分割线
                    DividerPage()
                }
                3->{
                    //流式布局
                    FlowRowPage()
                }
            }
        }
    }
}

