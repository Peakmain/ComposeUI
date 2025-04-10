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
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonElevation
import androidx.compose.material.Card
import androidx.compose.material.ContentAlpha
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalDrawer
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintSetScope
import com.peakmain.compose.library.TopAppBarCenter
import com.peakmain.compose.theme.PkTheme
import com.peakmain.compose.ui.banner.PkBanner
import com.peakmain.compose.ui.button.PkButton
import com.peakmain.compose.ui.button.PkButtonDefault
import com.peakmain.compose.utils.ImagePainterUtils

@Composable
fun TypeFragment() {
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
        TopAppBarCenter(title = {
            Text("分类", color = Color.White)
        }) {
            Column {
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
                //获取按钮的状态
                val interactionState = remember {
                    MutableInteractionSource()
                }
                /**
                 * 70%
                 */


                Text(
                    "Button", color = Color.White, modifier = Modifier
                        .indication(
                            interactionSource = interactionState,
                            indication = null
                        )
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xB21F401B))
                        .padding(horizontal = 24.dp, vertical = 8.dp)
                )
                PkButton(onClick = {

                },) {
                    Text("登录")
                }
            }
        }

    }
}





@Preview
@Composable
fun testPrev() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.End
    ) {
        Spacer(
            Modifier
                .height(100.dp)
                .fillMaxWidth()
        )
        Text("测试", fontSize = 10.sp)
    }
}

