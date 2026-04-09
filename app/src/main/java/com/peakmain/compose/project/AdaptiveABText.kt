package com.peakmain.compose.project

/**
 * @author ：Peakmain
 * createTime：2025/12/2
 * mail:2726449200@qq.com
 * describe：
 */

/**
 * 自适应换行的A/B字段组合
 * @param textA 字段A的文本（安心保障字段）
 * @param textB 字段B的文本（立即查看字段）
 */

/**
 * 自适应文本布局：文本A+文本B，空间足够则同行，不足则文本B换行
 * 核心：通过 SubcomposeLayout 测量两个文本的实际宽度，动态决定布局方式
 */
import android.R.attr.scaleX
import android.R.attr.scaleY
import android.R.attr.tag
import android.R.attr.text
import android.R.attr.top
import android.R.attr.translationX
import android.R.attr.translationY
import android.annotation.SuppressLint
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.AnimationState
import androidx.compose.animation.core.SnapSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateTo
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Badge
import androidx.compose.material.BadgedBox
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.DrawerDefaults.backgroundColor
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.LocalContentColor
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextFieldDefaults.contentPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.carousel.HorizontalCenteredHeroCarousel
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.material3.contentColorFor
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.peakmain.compose.ext.slice
import com.peakmain.compose.ui.PkGridLayout
import com.peakmain.compose.ui.image.PkImageView
import com.peakmain.compose.utils.ImagePainterUtils
import kotlinx.coroutines.launch
import kotlin.math.max

@Composable
fun PkAdaptiveSuffixText(
    textA: String,
    textB: String,
    modifier: Modifier = Modifier,
    styleA: SpanStyle = SpanStyle(
        fontSize = 12.sp,
        color = Color(0xFF666666),
        fontWeight = FontWeight.W500
    ),
    styleB: SpanStyle = SpanStyle(
        fontSize = 12.sp,
        color = Color(0xFF1F401B),
        fontWeight = FontWeight.W500,
        textDecoration = TextDecoration.Underline
    ),
    onClick: (() -> Unit)? = null
) {
    var showInline by remember { mutableStateOf(true) }
    val measurer = rememberTextMeasurer()
    BoxWithConstraints(modifier) {
        val maxWidthPx = constraints.maxWidth
        //A的宽度
        val layoutA = measurer.measure(
            text = AnnotatedString(textA),
            style = TextStyle(fontSize = styleA.fontSize, color = styleA.color),
            constraints = Constraints(maxWidth = maxWidthPx)
        )
        val lastLineWidth = layoutA.getLineRight(layoutA.lineCount - 1)

        val widthB = measurer.measure(
            text = AnnotatedString(textB),
            style = TextStyle(fontSize = styleB.fontSize, color = styleB.color),
        ).size.width

        val inline = lastLineWidth + widthB <= maxWidthPx
        LaunchedEffect(inline) {
            showInline = inline
        }
        var layoutResultState by remember { mutableStateOf<TextLayoutResult?>(null) }
        Column {
            if (showInline) {
                val text = buildAnnotatedString {
                    withStyle(styleA) {
                        append(textA)
                    }
                    pushStringAnnotation(tag = "B", annotation = "B")
                    withStyle(styleB) {
                        append(textB)
                    }
                    pop()
                }
                Text(
                    text = text,
                    onTextLayout = { layoutResult ->
                        // 监听点击范围
                        layoutResultState = layoutResult
                    },
                    modifier = Modifier.pointerInput(Unit) {
                        detectTapGestures { offset: Offset ->
                            layoutResultState?.let { layoutResult ->
                                val position = layoutResult.getOffsetForPosition(offset)
                                val annotations = text.getStringAnnotations(
                                    tag = "B",
                                    start = position,
                                    end = position
                                )
                                if (annotations.isNotEmpty()) {
                                    onClick?.invoke()
                                }
                            }
                        }
                    }
                )

            } else {
                // A 单独显示
                Text(
                    text = textA,
                    style = TextStyle(
                        fontSize = styleA.fontSize,
                        fontWeight = styleA.fontWeight,
                        color = styleA.color
                    )
                )
                // B 单独显示
                Text(
                    text = textB,
                    style = TextStyle(
                        fontSize = styleB.fontSize,
                        fontWeight = styleB.fontWeight,
                        color = styleB.color,
                        textDecoration = TextDecoration.Underline
                    ),
                    modifier = Modifier.clickable { onClick?.invoke() }
                )
            }
        }
    }

}


// 预览：短文本A（空间足够，不换行）
@Composable
fun PreviewShortTextA_NoWrap() {
    ZoomableImage(painterResource(R.drawable.ic_meinv))
}

@Composable
private fun customComposable() {
    Column {
        var shown by remember { mutableStateOf(true) }

        // targetState: 变化的状态值
        // animationSpec: 控制淡入淡出的动画曲线，默认为 tween
        Crossfade(targetState = shown, animationSpec = tween(2000), label = "crossfade") { screen ->
            // 根据传入的状态（it/screen）来决定显示哪个布局
            if (screen) {
                Box(
                    Modifier
                        .size(48.dp)
                        .background(Color.Green)
                )
            } else {
                Box(
                    Modifier
                        .size(24.dp)
                        .background(Color.Red)
                )
            }
        }

    }
}

@Composable

fun ZoomableImage(

    painter: Painter,

    modifier: Modifier = Modifier,

    contentDescription: String? = null,

    maxScale: Float = 3f,

    minScale: Float = 1f

) {

    // 缩放比例动画状态

    var scale by remember { mutableFloatStateOf(1f) }

    // 偏移量动画状态

    var offset by remember { mutableStateOf(Offset.Zero) }


    // 用于计算边界

    var size by remember { mutableStateOf(IntSize.Zero) }



    Box(

        modifier = modifier

            .clip(RectangleShape) // 确保图片放大后不画出边界

            .onSizeChanged { size = it }

            .pointerInput(Unit) {

                // 处理双击事件

                detectTapGestures(

                    onDoubleTap = { tapOffset ->

                        // 如果当前已经放大了，则恢复原状

                        if (scale > 1f) {

                            scale = 1f

                            offset = Offset.Zero

                        } else {

                            // 双击放大：通常放大到最大倍数

                            // 这里可以做一个简单的计算，让双击点成为中心

                            // 为了演示简单，我们直接放大，不做复杂的中心点偏移计算

                            scale = maxScale

                        }

                    }

                )

            }

            .pointerInput(Unit) {

                // 处理手势变换（缩放和平移）

                detectTransformGestures { centroid, pan, zoom, _ ->

                    val oldScale = scale

                    val newScale = (scale * zoom).coerceIn(minScale, maxScale)


                    // 1. 更新缩放

                    scale = newScale


                    // 2. 更新偏移量

                    // 为了让缩放过程更自然（以两指中心为基准），需要配合 centroid 计算 offset

                    // 这里的算法简化处理：仅处理平移 pan 和基于缩放的简单位移

                    // 真实的 PhotoView 算法会更复杂，需要考虑 centroid 的位置保持不动


                    // 简单的平移逻辑：

                    var newOffset = offset + pan


                    // 3. 边界限制 (Rubber Banding 简化版)

                    // 计算图片放大后的宽高

                    val imageWidth = size.width * scale

                    val imageHeight = size.height * scale


                    // 计算允许的最大偏移量 (X轴和Y轴)

                    // 如果图片宽度大于容器，允许左右滑动；否则居中（偏移限制为0）

                    val maxOffsetX = ((imageWidth - size.width) / 2).coerceAtLeast(0f)

                    val maxOffsetY = ((imageHeight - size.height) / 2).coerceAtLeast(0f)


                    // 限制 Offset 在 [-max, +max] 之间

                    newOffset = Offset(

                        x = newOffset.x.coerceIn(-maxOffsetX, maxOffsetX),

                        y = newOffset.y.coerceIn(-maxOffsetY, maxOffsetY)

                    )



                    offset = newOffset

                }

            }

    ) {

        Image(

            painter = painter,

            contentDescription = contentDescription,

            contentScale = ContentScale.Fit,

            modifier = Modifier

                .fillMaxSize()

                .align(Alignment.Center)

                .graphicsLayer {

                    scaleX = scale

                    scaleY = scale

                    translationX = offset.x

                    translationY = offset.y

                }

        )

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarouselDemo() {

    val state = rememberCarouselState() {
        6
    }

    HorizontalCenteredHeroCarousel(
        state = state,
        itemSpacing = 8.dp,
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp),

        ) { index ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = if (index % 2 == 0)
                        Color(0xFF90CAF9)
                    else
                        Color(0xFFA5D6A7),
                    shape = RoundedCornerShape(16.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Item $index",
                color = Color.Black
            )
        }
    }
}

/*
// 预览：中等文本A（临界状态，刚好不换行）
@Preview(showBackground = true, widthDp = 360)
@Composable
fun PreviewMediumTextA_NoWrap() {
    AdaptiveTextWithAction(
        textA = "当前绑定银行卡：招商银行(****8888) 开户人：张三",
        textB = "修改银行卡",
        onTextBClick = { println("点击修改银行卡") }
    )
}

// 预览：长文本A（空间不足，换行）
@Preview(showBackground = true, widthDp = 360)
@Composable
fun PreviewLongTextA_Wrap() {
    AdaptiveTextWithAction(
        textA = "当前绑定银行卡：招商银行(****8888) 开户人：张三 开户支行：上海市浦东新区张江高科技园区支行",
        textB = "修改银行卡",
        onTextBClick = { println("点击修改银行卡") }
    )
}
*/
@Composable
fun CommonFunctionItem(
) {
    Box(modifier = Modifier.background(Color.White)) {
        Box(
            Modifier
                .background(Color.Red)// 1. 先把整个区域涂红
                .size(80.dp)
        ) {

            Text(
                "哈哈", Modifier
                    .padding(20.dp)
                    .background(Color.White)
                    .padding(10.dp)
                    .background(Color.Blue)
            )
        }
    }
}

@Composable
fun CommonFunctionItemTop() {

    Box(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(top = 7.dp)
        ) {
            PkImageView(
                painter = painterResource(R.drawable.ic_icon),
                modifier = Modifier
                    .size(28.dp)
            )
        }

        if (false) {
            Row(
                modifier = Modifier
                    .padding(start = 25.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(if (true) Color.Red else Color.Transparent)
                    .padding(horizontal = 3.dp)
                    .align(Alignment.TopCenter)

            ) {
                Text(
                    "99+",
                    color = Color(0xFFFFFEFA),
                    fontSize = 10.sp,
                )
            }

        } else {
            Box( // 父 Box：控制位置和外边距
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 4.dp, start = 15.dp) // 红点离右上角的间距
            ) {
                Box( // 子 Box：纯红点样式
                    modifier = Modifier
                        .size(8.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(if (true) Color.Red else Color.Transparent)
                )
            }
        }


    }
}
@Composable
fun BottomAppBarExample() {
    Scaffold(
        bottomBar = {
            BottomAppBar(
                actions = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(Icons.Filled.Check, contentDescription = "Localized description")
                    }
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            Icons.Filled.Edit,
                            contentDescription = "Localized description",
                        )
                    }
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            Icons.Filled.Mic,
                            contentDescription = "Localized description",
                        )
                    }
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            Icons.Filled.Image,
                            contentDescription = "Localized description",
                        )
                    }
                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = { /* do something */ },
                        containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                    ) {
                        Icon(Icons.Filled.Add, "Localized description")
                    }
                }
            )
        },
    ) { innerPadding ->
        Text(
            modifier = Modifier.padding(innerPadding),
            text = "Example of a scaffold with a bottom app bar."
        )
    }
}
@Preview
@Composable
fun CommonFunctionItemTopPreview() {
    CarouselExample_Hero()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarouselExample_Hero() {
    data class CarouselItem(val id: Int, @DrawableRes val imageResId: Int, val desc: String)

    val items = remember {
        listOf(
            CarouselItem(0, R.drawable.image1, "Hero Item 1"),
            CarouselItem(1, R.drawable.image2, "Hero Item 2"),
            CarouselItem(2, R.drawable.image3, "Hero Item 3"),
            CarouselItem(3, R.drawable.image4, "Hero Item 4"),
        )
    }

    HorizontalCenteredHeroCarousel(
        state = rememberCarouselState { items.size },
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp) // 给出明确的高度，让主打项有足够空间
            .padding(vertical = 16.dp),
        itemSpacing = 8.dp, // 项目间距
        contentPadding = PaddingValues(horizontal = 16.dp), // 左右内边距
        // Hero Carousel 不需要 preferredItemWidth，它会根据容器和 contentPadding 自动计算主打项宽度
    ) { index ->
        val item = items[index]
        Card( // 内部可以使用 Card 来提供 M3 的默认样式
            modifier = Modifier
                .fillMaxSize() // 填充 CarouselItem 的槽位
                .maskClip(MaterialTheme.shapes.extraLarge), // 启用 M3 轮播的动态形状裁剪
        ) {
            Image(
                painter = painterResource(id = item.imageResId),
                contentDescription = item.desc,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PartialBottomSheet() {
    // 用于控制工作表是否在 Composition 中
    var showBottomSheet by remember { mutableStateOf(false) }

    // 关键配置：将 skipPartiallyExpanded 设置为 false 以开启“半屏”状态
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
    )

    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(
            onClick = { showBottomSheet = true }
        ) {
            Text("显示半屏底部动作条")
        }

        if (showBottomSheet) {
            ModalBottomSheet(
                // 确保工作表有能力填充至全屏
                modifier = Modifier.fillMaxHeight(),
                sheetState = sheetState,
                onDismissRequest = { showBottomSheet = false }
            ) {
                // 工作表内部内容
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "↑ 向上滑动即可全屏展示",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        "↓ 向下滑动即可收起或关闭",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalBottomSheetExample() {
    // 1. 定义状态与协程作用域
    val sheetState =
        androidx.compose.material3.rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text("显示底部动作条") },
                icon = { Icon(Icons.Filled.Add, contentDescription = "Add") },
                onClick = {
                    showBottomSheet = true // 触发显示
                }
            )
        }
    ) { contentPadding ->
        // 页面主体内容
        Box(modifier = Modifier.padding(contentPadding).fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("主界面内容")
        }

        // 2. 根据状态决定是否渲染 ModalBottomSheet
        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false // 用户手动取消时更新状态
                },
                sheetState = sheetState,
                // 自定义外观（可选）
                containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
            ) {
                // 3. 工作表内部布局
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("这是底部工作表内容", modifier = Modifier.padding(16.dp))

                    Button(onClick = {
                        // 4. 以编程方式隐藏
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            // 确保动画完全结束后再销毁组件
                            if (!sheetState.isVisible) {
                                showBottomSheet = false
                            }
                        }
                    }) {
                        Text("点击收起")
                    }
                }
            }
        }
    }
}
@Composable
fun BadgeInteractiveExample() {
    // 状态管理：记录购物车项目数量
    var itemCount by remember { mutableIntStateOf(0) }

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BadgedBox(
            badge = {
                // 条件渲染：仅在数量大于 0 时显示徽标
                if (itemCount > 0) {
                    Badge(
                        containerColor = Color.Red, // 背景颜色
                        contentColor = Color.White   // 文字颜色
                    ) {
                        // 在 Badge 内部放置 Text 来显示具体数值
                        Text("$itemCount")
                    }
                }
            }
        ) {
            // 徽标依附的购物车图标
            Icon(
                imageVector = Icons.Filled.ShoppingCart,
                contentDescription = "购物车",
            )
        }

        // 模拟交互：点击增加商品数量
        Button(onClick = { itemCount++ }) {
            Text("添加商品")
        }
    }
}
@Composable
private fun AppBarMultiSelectionExample(
    modifier: Modifier = Modifier,
) {
    val listItems by remember { mutableStateOf(listOf(1, 2, 3, 4, 5, 6)) }
    var selectedItems by rememberSaveable { mutableStateOf(setOf<Int>()) }

    Scaffold(
        modifier = modifier,
        topBar = { AppBarSelectionActions(selectedItems) }
    ) { innerPadding ->
        LazyColumn(contentPadding = innerPadding) {
            itemsIndexed(listItems) { _, index ->
                val isItemSelected = selectedItems.contains(index)
                ListItemSelectable(
                    selected = isItemSelected,
                    Modifier
                        .combinedClickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = {
                                /* click action */
                            },
                            onLongClick = {
                                if (isItemSelected) selectedItems -= index else selectedItems += index
                            }
                        )
                )
            }
        }
    }
}
/**
 * 可选择的列表项
 * @param text 项显示文本
 * @param isSelected 当前选中状态
 * @param onLongPress 长按回调（切换选中状态）
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ListItemSelectable(
    selected: Boolean,
    modifier: Modifier = Modifier,
) {
    // 根据选中状态调整文本颜色（可选，增强选中视觉反馈）
    val textColor = if (selected) Color(0xFF1677FF) else LocalContentColor.current

    ListItem(
        // 直接使用传入的 modifier（包含你配置的 combinedClickable）
        modifier = modifier.fillMaxWidth(),
        // 列表项主文本（可根据你的需求扩展副标题、图标等内容）
        headlineContent = {
            Text(
                text = "Long press to select or deselect item",
                color = textColor
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarSelectionActions(
    selectedItems: Set<Int>, // 传入已选择项的索引集合
    modifier: Modifier = Modifier,
) {
    // 核心逻辑：判断当前是否存在选中项
    val hasSelection = selectedItems.isNotEmpty()

    // 动态标题逻辑
    val topBarText = if (hasSelection) {
        "已选中 ${selectedItems.size} 项"
    } else {
        "项目列表"
    }

    TopAppBar(
        title = {
            Text(topBarText)
        },
        colors = TopAppBarDefaults.topAppBarColors(
            // 使用 Material 3 容器颜色，确保视觉层次感
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        actions = {
            // 条件渲染：仅在有选中项时显示“分享”图标
            if (hasSelection) {
                IconButton(onClick = {
                    /* 处理分享选中的 selectedItems 逻辑 */
                }) {
                    Icon(
                        imageVector = Icons.Filled.Share,
                        contentDescription = "分享选中项"
                    )
                }
            }
        },
        modifier = modifier
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarNavigationExample(
    // 最佳实践：通过 lambda 暴露导航事件，不直接依赖 NavController
    navigateBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Navigation example")
                },
                navigationIcon = {
                    // 导航图标槽位：通常放置返回、菜单或关闭图标
                    IconButton(onClick = navigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "返回上一页"
                        )
                    }
                },
            )
        },
    ) { innerPadding ->
        Text(
            text = "Click the back button to pop from the back stack.",
            modifier = Modifier.padding(innerPadding),
        )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LargeTopAppBarExample() {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        "Large Top App Bar",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Localized description"
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
    ) { innerPadding ->
        ScrollContent(innerPadding)
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediumTopAppBarExample() {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            MediumTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        "Medium Top App Bar",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Localized description"
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
    ) { innerPadding ->
        ScrollContent(innerPadding)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SmallTopAppBarExample() {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Small Top App Bar")
                }
            )
        },
    ) { innerPadding ->
        ScrollContent(innerPadding)
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenterAlignedTopAppBarExample() {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),

        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        "Centered Top App Bar",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Localized description"
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        },
    ) { innerPadding ->
        ScrollContent(innerPadding)
    }
}
@Composable
fun ScrollContent(innerPadding: PaddingValues) {
    Column(
        modifier = Modifier
            .padding(innerPadding) // 重要：使用 Scaffold 提供的 Padding
            .fillMaxSize()
            .verticalScroll(rememberScrollState()), // 使内容可滚动
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        repeat(50) { i ->
            Text(
                text = "List Item number $i",
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}