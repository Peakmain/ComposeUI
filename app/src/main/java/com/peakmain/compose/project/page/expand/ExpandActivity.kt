package com.peakmain.compose.project.page.expand

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.peakmain.compose.project.page.expand.component.ListExtPage

/**
 * author ：Peakmain
 * createTime：2025/6/27
 * mail:2726449200@qq.com
 * describe：
 */
class ExpandActivity : ComponentActivity() {
    val type by lazy {
        intent.getIntExtra("type", 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            when (type) {
                1 -> {
                  //List扩展类
                    ListExtPage()
                }
            }
        }
    }
}