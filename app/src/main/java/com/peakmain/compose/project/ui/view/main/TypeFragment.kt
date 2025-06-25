package com.peakmain.compose.project.ui.view.main

/**
 * author ：Peakmain
 * createTime：2022/5/2
 * mail:2726449200@qq.com
 * describe：分类Fragment
 */
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import com.peakmain.compose.basic.BasicFont
import com.peakmain.compose.basic.BasicSize
import com.peakmain.compose.basic.BasicSpace
import com.peakmain.compose.ext.isNotEmpty
import com.peakmain.compose.ext.isNotEmptyComposable
import com.peakmain.compose.project.R
import com.peakmain.compose.theme.PkTheme
import com.peakmain.compose.ui.banner.PkBanner
import com.peakmain.compose.ui.button.PkButton
import com.peakmain.compose.ui.button.PkButtonColors
import com.peakmain.compose.ui.button.PkButtonDefault
import com.peakmain.compose.ui.flow.PkFlowRow
import com.peakmain.compose.ui.title.PkTitle
import com.peakmain.compose.ui.title.PkTitleType
import com.peakmain.compose.utils.ImagePainterUtils


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TypeFragment() {
    val listState = rememberLazyListState()
    var isCommonFunctionExpand by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current
    val source = remember { MutableInteractionSource() }

    val scrollDistance by remember {
        derivedStateOf {
            if (listState.firstVisibleItemIndex > 0 || listState.firstVisibleItemScrollOffset >= 44f) {
                44f
            } else {
                0f
            }
        }
    }
    LazyColumn(
        modifier = Modifier
            .background(Color.White)
            .statusBarsPadding()
            .fillMaxSize(),
        state = listState
    ) {
        stickyHeader {
            //客服、消息、设置
            Box() {
                if (scrollDistance == 44f) {
                    PkTitle(
                        "会员",
                        type = PkTitleType.BigTitle3(),
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
        item {
            Row(verticalAlignment = Alignment.CenterVertically) {

            }
        }
        item {
            PkButton(
                onClick = {
                },
                interactionSource = source,
                elevation = null,
                colors = PkButtonDefault.transparentColor(
                    source,
                    pressedContentColor = Color(0xFFFFFEFA)
                ),
            ) {
                Text("测试")
            }
        }
        item {
            Column {
                Box(modifier = Modifier.fillMaxSize()) {
                    Button(
                        onClick = { },
                        modifier = Modifier.align(Alignment.Center)
                    ) {
                        Text("显示 Top Toast")
                    }

                }

                Column(
                    modifier = Modifier
                        .height(800.dp)
                        .fillMaxWidth()
                        .background(Color.Red)
                ) {

                }
            }
        }
    }
}

@Composable
fun VerticalBannerDemo() {
    val items = listOf("标题1", "标题标题2标题2标题2标题2标题2标题22", "标题3")
    PkBanner(
        lists = items,
        isVertical = true,
        isAutoPlay = true,
        duration = 1000,
        userScrollEnabled = false
    ) { currentIndex, it ->
        Text(
            text = it ?: "",
            modifier = Modifier
                .height(60.dp)
                .background(Color.Gray),
            fontSize = 20.sp,
        )
    }
}

@Composable
fun BannerDemo() {
    val lists = ArrayList<String>().apply {
        add("https://img2.baidu.com/it/u=292395973,2170347184&fm=253&fmt=auto&app=120&f=JPEG?w=1280&h=800")
        add("https://img0.baidu.com/it/u=3492687357,1203050466&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500")
        add("https://img2.baidu.com/it/u=2843793126,682473204&fm=253&fmt=auto&app=120&f=JPEG?w=1280&h=800")
        add("https://img1.baidu.com/it/u=3907217777,761642486&fm=253&fmt=auto&app=120&f=JPEG?w=1280&h=800")
        add("https://img1.baidu.com/it/u=1082651511,4058105193&fm=253&fmt=auto&app=120&f=JPEG?w=1422&h=800")
    }
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        PkBanner(
            lists,
        ) { currentIndex, it ->
            Log.e("TAG", "当前索引:$currentIndex")
            Image(
                painter = ImagePainterUtils.getPainter(it),
                contentDescription = null,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .fillMaxSize(),
                contentScale = ContentScale.Crop,
                alignment = Alignment.TopCenter
            )
        }

    }
}

// 定义图标类
object IconfontIcons {
    const val HOME = "\uE7FC"
    const val SEARCH = "\uE7FB"
    const val USER = "\ue602"
    const val SETTINGS = "\ue603"
    const val NOTIFICATION = "\ue604"
}

@Preview
@Composable
fun testPrev() {
    val tags = listOf(
        "Android",
        "Kotlin",
    )
    var currentLine by remember {
        mutableStateOf(0)
    }
    Column {
        Text("当前行数:$currentLine", color = Color.White, fontSize = 24.sp)
        PkFlowRow(
            horizontalSpacing = 8.dp,
            verticalSpacing = 12.dp,
            onLineCountChanged = {
                currentLine = it
            },
            maxLine = 1
        ) {
            tags.forEach { tag ->
                Text(
                    text = tag,
                    modifier = Modifier
                        .background(Color.LightGray, RoundedCornerShape(16.dp))
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                )
            }
        }
    }
}

@Composable
fun PkBadge(
    content: String? = null,
    backgroundColor: Color = Color(0xFFA5534D),
    paddingValues: PaddingValues = PaddingValues(horizontal = 3.dp, vertical = 1.dp),
    radius: Dp = 2.dp
) {
    if (content == null) return
    Box(contentAlignment = Alignment.CenterEnd) {
        Text(
            text = content,
            color = Color.White,
            modifier = Modifier
                .clip(RoundedCornerShape(radius))
                .background(backgroundColor)
                .padding(paddingValues = paddingValues),
            fontSize = 8.sp
        )
    }

}

@Composable
fun IconWithBadgeDemo() {
    var badgeWidth by remember {
        mutableStateOf(0.dp)
    }
    var badgeHeight by remember {
        mutableStateOf(0.dp)
    }
    val density = LocalDensity.current
    Box(
        modifier = Modifier
            .width(28.dp + badgeWidth / 2)
            .height(28.dp + badgeHeight / 2),
    ) {
        // 图标A
        Icon(
            imageVector = Icons.Default.MailOutline,
            contentDescription = "icon A",
            tint = Color.White,
            modifier = Modifier
                .size(28.dp)
                .align(Alignment.Center)
        )

        // 角标
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .offset(x = 0.dp, y = 0.dp) // 保持右上角中心点重合
                .clip(RoundedCornerShape(2.dp))
                .background(
                    color = Color(0xFFA5534D),
                )
                .padding(horizontal = 4.dp, vertical = 1.dp)
                .onSizeChanged {
                    with(density) {
                        badgeWidth = it.width.toDp()
                        badgeHeight = it.height.toDp()
                    }
                }
        ) {
            Text(
                text = "9",
                color = Color.White,
                fontSize = 8.sp,
            )
        }
    }
}

