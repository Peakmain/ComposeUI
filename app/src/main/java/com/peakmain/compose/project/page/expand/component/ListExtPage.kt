package com.peakmain.compose.project.page.expand.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key.Companion.At
import androidx.compose.ui.unit.dp
import com.peakmain.compose.basic.BasicFont
import com.peakmain.compose.ext.orSize
import com.peakmain.compose.ext.sizeBigZero
import com.peakmain.compose.ext.slice
import com.peakmain.compose.project.component.CpColumn
import com.peakmain.compose.project.component.CpTitle
import com.peakmain.compose.project.constants.PkColor

/**
 * author ：Peakmain
 * createTime：2025/6/27
 * mail:2726449200@qq.com
 * describe：
 */
@Composable
fun ListExtPage() {
    val list1: List<String>? = null
    val list2 = arrayListOf("list1", "list2", "list3", "list4")
    CpColumn("List扩展类") {
        CpTitle("空数组list1的大小:${list1.orSize()}")
        CpTitle("空数组list1的大小是否等于0:${list1.sizeBigZero()}")
        CpTitle("空数组list1的大小是否大于0:${list1.sizeBigZero()}")
        CpTitle("定索引范围 [fromIndex, toIndex) 内的子列表视图")
        var result: String = "结果是："
        list2.slice(1, 3)?.forEach {
            result +="$it,"
        }
        Text(result, fontSize = BasicFont.font_10, color = PkColor.color_text1, modifier = Modifier.padding(horizontal = 18.dp))

    }
}