package com.peakmain.compose.utils

import android.content.Context
import android.graphics.Insets.add
import android.os.Build.VERSION.SDK_INT
import android.text.TextUtils
import androidx.annotation.DrawableRes
import androidx.annotation.Px
import androidx.compose.foundation.Image
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.drawscope.DrawScope.Companion.DefaultFilterQuality
import androidx.compose.ui.platform.LocalContext
import coil.ImageLoader
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.decode.ImageDecoderDecoder
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.peakmain.compose.library.R

/**
 * author ：Peakmain
 * createTime：2025/3/6
 * mail:2726449200@qq.com
 * describe：图片加载工具类
 */
object ImagePainterUtils {
    /**
     * 根据图片 URL 获取 AsyncImagePainter 对象。
     *
     * @param imageUrl 图片的 URL，如果为空则显示占位图。
     * @param errorDrawableResId 图片加载失败时显示的 Drawable 资源 ID，默认为 R.drawable.icon_loading。
     * @param placeDrawableResId 图片加载过程中显示的占位图 Drawable 资源 ID，默认为 R.drawable.icon_loading。
     * @param targetWidth   目标解码宽度（像素）。
     *                      - 当不为 `0` 时，会在解码阶段下采样图片，减少内存占用。
     *                      - 建议设置为与容器宽度相近的像素值，避免解码原图造成高内存开销。
     * @param targetHeight  目标解码高度（像素）。
     *                       - 与 [targetWidth] 配合使用，控制图片的最终解码尺寸。
     *                       - 如果容器为固定比例（如 `ContentScale.Crop`），建议解码尺寸略大于显示区域，防止缩放模糊。
     *
     * @param filterQuality     绘制过滤质量（影响缩放清晰度与性能）。
     *                          - 默认值：`DefaultFilterQuality`（即 [FilterQuality.Low]）
     *                          - 可选值：
     *                               - `FilterQuality.None`：最快速，但缩放后可能出现锯齿。
     *                               - `FilterQuality.Low`：平衡性能与画质。
     *                               - `FilterQuality.Medium`：更平滑的缩放效果，性能中等。
     *                               - `FilterQuality.High`：最高画质，适合缩放后仍需高清展示的场景（代价是性能略低）。
     * @return 返回一个 AsyncImagePainter 对象。
     */
    @Composable
    fun getPainter(
        imageUrl: String?,
        @DrawableRes errorDrawableResId: Int = R.drawable.icon_loading,
        @DrawableRes placeDrawableResId: Int = R.drawable.icon_loading,
        @Px targetWidth: Int = 0,
        @Px targetHeight: Int = 0,
        filterQuality: FilterQuality = DefaultFilterQuality,
        imageLoader: ImageLoader = getImageLoader(LocalContext.current),
    ): AsyncImagePainter {
        if (TextUtils.isEmpty(imageUrl)) {
            return rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(placeDrawableResId)
                    .build(),
                imageLoader = imageLoader
            )
        }
        val builder = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .error(errorDrawableResId)
            .placeholder(placeDrawableResId)
        if (targetWidth != 0 && targetHeight != 0) {
            builder.size(targetWidth, targetHeight)
        }
        return rememberAsyncImagePainter(
            model = builder
                .build(),
            imageLoader = imageLoader,
            filterQuality = filterQuality
        )

    }

    private fun getImageLoader(context: Context): ImageLoader {
        return ImageLoader.Builder(context).components {
            if (SDK_INT > 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(coil.decode.GifDecoder.Factory())
            }
            add(SvgDecoder.Factory())
        }.build()
    }
}