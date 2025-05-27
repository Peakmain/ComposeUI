package com.peakmain.compose.utils

import android.content.Context
import android.graphics.Insets.add
import android.os.Build.VERSION.SDK_INT
import android.text.TextUtils
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
     * @return 返回一个 AsyncImagePainter 对象。
     */
    @Composable
    fun getPainter(
        imageUrl: String?,
        @DrawableRes errorDrawableResId: Int = R.drawable.icon_loading,
        @DrawableRes placeDrawableResId: Int = R.drawable.icon_loading,
        imageLoader: ImageLoader = getImageLoader(LocalContext.current)
    ): AsyncImagePainter {

        return if (!TextUtils.isEmpty(imageUrl))
            rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .error(errorDrawableResId)
                    .placeholder(placeDrawableResId)
                    .build(),
                imageLoader =imageLoader
            ) else
            rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(placeDrawableResId)
                    .build(),
                imageLoader =imageLoader
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