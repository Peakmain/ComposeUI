package com.peakmain.compose.project.component

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.peakmain.compose.project.page.display.DisplayComponentActivity
import com.peakmain.compose.ui.cell.PkCell
import com.peakmain.compose.ui.title.PkTitleType

/**
 * author ：Peakmain
 * createTime：2025/6/27
 * mail:2726449200@qq.com
 * describe：
 */
@Composable
fun CpCell(title:String,intent:Intent,type:Int) {
    val context = LocalContext.current
    PkCell(text = title, modifier = Modifier.clickable {
        intent.putExtra("type",type)
        context.startActivity(intent)
    }, type = PkTitleType.TitleBold1())
}