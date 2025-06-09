package com.peakmain.compose.ui.banner

/**
 * author ：Peakmain
 * createTime：2025/4/8
 * mail:2726449200@qq.com
 * describe：
 */
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

/**
 * 通用轮播组件
 * @param lists 轮播数据源，为空时不渲染组件（必填）
 * @param pagerWidth 单个轮播项宽度，默认290.dp
 * @param pagerHeight 轮播容器高度，默认116.dp
 * @param pageSpacing 轮播项水平间距，默认12.dp
 * @param contentPadding 首尾项两侧边距（用于居中），默认18.dp
 * @param duration 自动轮播间隔时间（毫秒），默认3000
 * @param isAutoPlay 是否启用自动轮播，默认false
 * @param initialPage 初始显示的页面索引（从0开始），超出范围时自动修正为有效值0
 * @param onBannerClick 点击回调，参数为当前页索引（从0开始）
 * @param isVertical 是否为纵向轮播，默认值是false(也就是说默认是水平轮播)
 * @param horizontalAlignment 横向对齐方式，默认值是Alignment.Start
 * @param verticalAlignment 纵向对齐方式，默认值 Alignment.CenterVertically
 * @param userScrollEnabled\ 是否允许用户手动滑动 默认值是true
 * @param content 自定义轮播项内容的Composable，参数为当前页索引（必填）
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun <T> PkBanner(
    lists: List<T?>,
    pagerWidth: Dp = 290.dp,
    pagerHeight: Dp = 116.dp,
    pageSpacing: Dp = 12.dp,
    contentPadding: Dp = 18.dp,
    duration: Long = 3000,
    isAutoPlay: Boolean = false,
    initialPage: Int = 0,
    onBannerClick: ((Int, T?) -> Unit)? = null,
    isVertical: Boolean = false,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    userScrollEnabled: Boolean = true,
    content: @Composable (Int, T?) -> Unit
) {
    val size = lists.size
    if (size == 0) return
    val pagerState = rememberPagerState(if (initialPage in 0..<size) initialPage else 0) {
        size
    }
    val screenWidthDp = LocalConfiguration.current.screenWidthDp.dp

    /**
     * 非0或者size-1的内容边距
     */
    val contentHorizontalPadding = (screenWidthDp - pagerWidth) / 2
    //控制是否轮播
    var isAutoScrollEnabled by remember {
        mutableStateOf(isAutoPlay && size > 0)
    }
    val screenHeightPx =
        with(LocalDensity.current) { LocalConfiguration.current.screenHeightDp.dp.toPx() }
    var isBannerVisible by remember { mutableStateOf(true) } // 用于判断是否在屏幕上

    /**
     * 监听是否可见
     */
    val visibilityModifier = Modifier.onGloballyPositioned { layoutCoordinates ->
        val position = layoutCoordinates.localToWindow(Offset.Zero)
        isBannerVisible = position.y in 0f..screenHeightPx
    }

    if (size > 1 && isAutoPlay) {
        LaunchedEffect(isAutoScrollEnabled, isBannerVisible) {
            while (isAutoScrollEnabled && isBannerVisible) {
                delay(duration)
                if (isAutoScrollEnabled && isBannerVisible) {
                    val nextPage = (pagerState.currentPage + 1) % pagerState.pageCount
                    pagerState.animateScrollToPage(nextPage)
                }
            }
        }
        LaunchedEffect(pagerState.isScrollInProgress) {
            if (!pagerState.isScrollInProgress && !isAutoScrollEnabled) {
                // 滑动+惯性滚动结束后再恢复
                isAutoScrollEnabled = true
            }
        }
    }
    if (isVertical) {
        VerticalPager(
            pagerState,
            pageSize = if (size == 1) PageSize.Fill else PageSize.Fixed(pagerWidth),
            horizontalAlignment = horizontalAlignment,
            userScrollEnabled = userScrollEnabled,
            modifier = visibilityModifier
                .pointerInput(Unit) {
                    detectTapGestures(
                        onPress = {
                            if (userScrollEnabled && isAutoPlay) {
                                isAutoScrollEnabled = false
                                if (tryAwaitRelease()) {
                                    isAutoScrollEnabled = true
                                }
                            }
                        },
                        onTap = {
                            val index = pagerState.currentPage
                            onBannerClick?.invoke(
                                index,
                                lists.getOrNull(index)
                            )
                        }
                    )
                }
                .height(pagerHeight),// 限制父容器高度
            contentPadding = PaddingValues(horizontal = contentPadding),
            pageSpacing = pageSpacing
        ) {
            content(it, lists.getOrNull(it))
        }
    } else {
        HorizontalPager(
            pagerState,
            pageSize = if (size == 1) PageSize.Fill else PageSize.Fixed(pagerWidth),
            verticalAlignment = verticalAlignment,
            userScrollEnabled = userScrollEnabled,
            modifier = visibilityModifier
                .pointerInput(Unit) {
                    detectTapGestures(
                        onPress = {
                            if (userScrollEnabled && isAutoPlay) {
                                isAutoScrollEnabled = false
                                if (tryAwaitRelease()) {
                                    isAutoScrollEnabled = true
                                }
                            }
                        },
                        onTap = {
                            val index = pagerState.currentPage
                            onBannerClick?.invoke(
                                index,
                                lists.getOrNull(index)
                            )
                        }
                    )
                }
                .height(pagerHeight),// 限制父容器高度
            contentPadding = PaddingValues(horizontal = if (pagerState.currentPage == 0 || pagerState.currentPage == lists.size - 1) contentPadding else contentHorizontalPadding),
            pageSpacing = pageSpacing
        ) {
            content(it, lists[if (it < lists.size) it else 0])
        }
    }


}

@Preview
@Composable
fun PkBannerPreview() {
    //PkBanner()
}

