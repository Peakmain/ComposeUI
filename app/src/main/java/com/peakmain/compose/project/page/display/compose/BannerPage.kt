package com.peakmain.compose.project.page.display.compose

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.peakmain.compose.basic.BasicColor
import com.peakmain.compose.basic.BasicFont
import com.peakmain.compose.basic.BasicRadius
import com.peakmain.compose.basic.BasicSize
import com.peakmain.compose.basic.BasicSpace
import com.peakmain.compose.library.TopAppBarCenter
import com.peakmain.compose.project.R
import com.peakmain.compose.project.component.CpColumn
import com.peakmain.compose.project.constants.PkColor
import com.peakmain.compose.space.PkSpacer
import com.peakmain.compose.ui.banner.PkBanner
import com.peakmain.compose.ui.image.PkImageView
import com.peakmain.compose.ui.progress.RoundedLinearProgressIndicator
import com.peakmain.compose.ui.title.PkNavBar
import com.peakmain.compose.ui.title.PkTitle
import com.peakmain.compose.ui.title.PkTitleType
import com.peakmain.compose.utils.ImagePainterUtils

/**
 * author ：Peakmain
 * createTime：2025/6/27
 * mail:2726449200@qq.com
 * describe：轮播图
 */
@Composable
fun BannerPage() {
    val lists = ArrayList<String>().apply {
        add("https://img2.baidu.com/it/u=292395973,2170347184&fm=253&fmt=auto&app=120&f=JPEG?w=1280&h=800")
        add("https://img0.baidu.com/it/u=3492687357,1203050466&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500")
        add("https://img2.baidu.com/it/u=2843793126,682473204&fm=253&fmt=auto&app=120&f=JPEG?w=1280&h=800")
        add("https://img1.baidu.com/it/u=3907217777,761642486&fm=253&fmt=auto&app=120&f=JPEG?w=1280&h=800")
        add("https://img1.baidu.com/it/u=1082651511,4058105193&fm=253&fmt=auto&app=120&f=JPEG?w=1422&h=800")
    }
    val vLists = arrayListOf("广告", "我是垂直轮播", "生活好滋味，就要上四休三")
    CpColumn("轮播图组件") {
        PkTitle("默认水平轮播", type = PkTitleType.TextBold1(), modifier = Modifier.padding(18.dp))
        PkBanner(
            lists,
            isAutoPlay = true,
            initialPage = 3,
            onBannerClick = { index, item ->
                Log.e("TAG", "获取到点击后的数据：${item}")
            }) { index, item ->
            Image(
                painter = ImagePainterUtils.getPainter(item),
                contentDescription = null,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .fillMaxSize(),
                contentScale = ContentScale.Crop,
                alignment = Alignment.TopCenter
            )
        }
        PkTitle("垂直轮播", type = PkTitleType.TextBold1(), modifier = Modifier.padding(18.dp))
        PkBanner(
            vLists,
            isAutoPlay = true,
            isVertical = true
        ) { index, item ->
            Box() {
                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(BasicRadius.radius_8))
                        .background(PkColor.color_fill2)
                        .padding(bottom = BasicSpace.space_16)
                        .fillMaxHeight(),
                ) {
                    Box() {
                        Column {
                            Row(
                                modifier = Modifier
                                    .padding(
                                        top = BasicSpace.space_16,
                                        start = BasicSpace.space_12,
                                        end = BasicSpace.space_12
                                    ),
                            ) {
                                // 左侧 圆形背景 + 图标
                                Box(
                                    modifier = Modifier
                                        .size(BasicSize.size_40)
                                        .clip(CircleShape)
                                        .background(PkColor.color_fill1),
                                    contentAlignment = Alignment.Center
                                ) {
                                    PkImageView(
                                        painter = painterResource(
                                            id = R.drawable.ic_online_check_out_disable
                                        ),
                                        modifier = Modifier.size(BasicSize.size_24),
                                    )
                                }

                                Spacer(modifier = Modifier.width(BasicSpace.space_8))

                                // 中间 内容
                                Column(
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text(
                                        text = "已入住",
                                        color = PkColor.color_text1,
                                        fontSize = BasicFont.font_14,
                                    )
                                    Spacer(modifier = Modifier.height(BasicSize.size_2))
                                    Text(
                                        text = "2025年06月27日" + "  |  " + "金会员",
                                        color = PkColor.color_text2,
                                        fontSize = BasicFont.font_11,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }

                            }
                            // 底部进度条
                            RoundedLinearProgressIndicator(
                                progress = 0.5f,
                                modifier = Modifier
                                    .padding(top = BasicSize.size_12)
                                    .fillMaxWidth()
                                    .height(BasicSize.size_4)
                                    .padding(horizontal = BasicSpace.space_16),
                                color = PkColor.color_brand1,
                                cornerRadius = BasicRadius.radius_2
                            )
                            PkSpacer(height = BasicSize.size_16)
                        }
                        //洗衣取送
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .padding(end = BasicSpace.space_8)
                                .align(Alignment.TopEnd)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.background_laundry_delivery),
                                contentDescription = null,
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier
                                    .wrapContentWidth()
                                    .wrapContentHeight()
                            )
                            androidx.compose.material3.Text(
                                text = "垂直轮播",
                                color = PkColor.color_text5,
                                fontSize = BasicFont.font_11,
                                modifier = Modifier
                                    .padding(
                                        horizontal = BasicSpace.space_7,
                                        vertical = BasicSpace.space_4
                                    )
                            )


                        }

                    }
                }
            }
        }
    }
}