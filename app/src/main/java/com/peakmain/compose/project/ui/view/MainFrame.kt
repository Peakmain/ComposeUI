package com.peakmain.compose.project.ui.view

/**
 * author ：Peakmain
 * createTime：2022/5/2
 * mail:2726449200@qq.com
 * describe：
 */
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.peakmain.compose.project.bean.main.NavigationItem
import com.peakmain.compose.project.ui.theme.Color_149EE7
import com.peakmain.compose.project.ui.theme.Color_999999
import com.peakmain.compose.project.ui.view.main.HomeFragment
import com.peakmain.compose.project.ui.view.main.ExpandFragment
import com.peakmain.compose.project.ui.view.main.ProjectFragment
import com.peakmain.compose.project.ui.view.main.TypeFragment

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainFrame() {
    val navigationItems = listOf(
        NavigationItem("基础组件", Icons.Default.Home),
        NavigationItem("展示组件", Icons.Default.AccountCircle),
        NavigationItem("工具类", Icons.Default.LocationOn),
        NavigationItem("扩展类", Icons.Default.Person)
    )
    var currentNavigationIndex by remember {
        mutableStateOf(0)
    }
    Scaffold(
        bottomBar = {
            BottomNavigation(
                backgroundColor = MaterialTheme.colors.surface,
                modifier = Modifier.navigationBarsPadding()
            ) {
                navigationItems.forEachIndexed { index, navigationItem ->
                    BottomNavigationItem(
                        selected = currentNavigationIndex == index,
                        onClick = { currentNavigationIndex = index },
                        icon = {
                            Icon(imageVector = navigationItem.icon, contentDescription = null)
                        },
                        label = {
                            Text(text = navigationItem.title)
                        },
                        selectedContentColor = Color_149EE7,
                        unselectedContentColor = Color_999999
                    )
                }
            }
        },
    ) {
        when (currentNavigationIndex) {
            0 -> HomeFragment()
            1 -> ProjectFragment()
            2 -> TypeFragment()
            else -> ExpandFragment()
        }
    }
}

@Preview
@Composable
fun MainFramePreview() {
    MainFrame()
}

