package com.peakmain.compose.project.ui.view.main

/**
 * author ：Peakmain
 * createTime：2022/5/2
 * mail:2726449200@qq.com
 * describe：分类Fragment
 */
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.Icon
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.key.Key.Companion.Ro
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.peakmain.compose.basic.BasicFont
import com.peakmain.compose.library.TopAppBarCenter
import com.peakmain.compose.project.R
import com.peakmain.compose.theme.PkTheme
import com.peakmain.compose.ui.banner.PkBanner
import com.peakmain.compose.ui.button.PkButton
import com.peakmain.compose.ui.button.PkButtonDefault
import com.peakmain.compose.ui.button.PkShapes
import com.peakmain.compose.ui.cell.PkCell
import com.peakmain.compose.ui.title.PkTitleType
import com.peakmain.compose.utils.ImagePainterUtils

@Composable
fun TypeFragment() {
    Column(
        modifier = Modifier
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
    ) {
        Column {
            BannerDemo()
            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    "￥729",
                    color = Color(0x40252525),
                    style = TextStyle(textDecoration = TextDecoration.LineThrough),
                )
                Text(
                    buildAnnotatedString {
                        append("¥671")
                        withStyle(SpanStyle()) {
                            addStyle(
                                style = SpanStyle(
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Normal
                                ), 0, 1
                            )
                        }
                    },
                    Modifier
                        .align(Alignment.Bottom)
                        .alignByBaseline(),
                    color = Color(0xFFE56A54),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.W500
                )
            }
            Box() {
                Image(
                    painter = painterResource(R.drawable.ic_launcher_foreground),
                    contentDescription = null
                )
                PkBadge("13")
            }
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
        }
        Column(
            Modifier
                .background(Color.White)
                .height(510.dp)
        ) {
            PkCell(
                "常用功能",
                PkTitleType.BigTitle3(),
                rightText = "展开",
                modifier = Modifier.padding(horizontal = 18.dp),
                color = Color(0xFF14401B)
            )
        }
        Box(modifier = Modifier
            .height(300.dp)
            .fillMaxWidth(), contentAlignment = Alignment.Center) {
            Text("我是底部")
        }
    }
}
@Composable
fun BannerDemo(){
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
            PkBanner(lists,
                isAutoPlay = true,
                initialPage = 3,
                onBannerClick = {
                    Log.e("TAG", "获取到点击后的数据：${lists[it]}")
                }) {
                Image(
                    painter = ImagePainterUtils.getPainter(lists[it]),
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

@Preview
@Composable
fun testPrev() {
    /*  Box(modifier = Modifier.size(36.dp)) {
          Image(
              painter = painterResource(R.drawable.portrair),
              contentDescription = null,
              contentScale = ContentScale.Crop,
              modifier = Modifier
                  .padding(top = 8.dp)
                  .size(28.dp)
          )
          PkBadge("13")
      }*/
    Column(Modifier.background(Color.White)) {
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

