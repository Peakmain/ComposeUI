package com.peakmain.compose.project.ui.view.main

/**
 * author ：Peakmain
 * createTime：2022/5/2
 * mail:2726449200@qq.com
 * describe：分类Fragment
 */
import android.content.Intent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.peakmain.compose.basic.BasicSpace
import com.peakmain.compose.library.TopAppBarCenter
import com.peakmain.compose.project.component.CpCell
import com.peakmain.compose.project.page.tools.ToolsActivity
import com.peakmain.compose.project.ui.theme.Color_149EE7
import com.peakmain.compose.project.ui.theme.Color_2DCDF5
import com.peakmain.compose.ui.calendar.PkCalendar
import com.peakmain.compose.utils.CalendarModel
import java.util.Calendar


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TypeFragment() {
    val context = LocalContext.current
    val intent = Intent(context, ToolsActivity::class.java)
    val lists = arrayListOf("ImagePainter工具类")
    var selectStart = remember { mutableStateOf(CalendarModel(0, 0, 0, 0)) }
    var selectEnd = remember { mutableStateOf(CalendarModel(0, 0, 0, 0)) }
    val calendar = Calendar.getInstance()
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
            PkCalendar(
                currentMonth = calendar.get(Calendar.MONTH) + 1,
                currentDay = calendar.get(Calendar.DAY_OF_MONTH),
                currentYear = calendar.get(Calendar.YEAR),
                selectStartModel = selectStart.value,
                selectEndModel = selectEnd.value,
                onSelectStartModelChanged = { selectStart.value = it },
                onSelectEndModelChanged = { selectEnd.value = it },
                onSelectCalendarResult = { start, end ->
                    // 处理选择结果
                }
            )
        }
    }
}

