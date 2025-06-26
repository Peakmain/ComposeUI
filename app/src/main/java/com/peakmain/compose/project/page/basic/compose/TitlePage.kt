package com.peakmain.compose.project.page.basic.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.peakmain.compose.ui.divier.PkDivider
import com.peakmain.compose.ui.title.PkNavBar
import com.peakmain.compose.ui.title.PkTitle
import com.peakmain.compose.ui.title.PkTitleType

/**
 * author ：Peakmain
 * createTime：2025/6/26
 * mail:2726449200@qq.com
 * describe：
 */
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