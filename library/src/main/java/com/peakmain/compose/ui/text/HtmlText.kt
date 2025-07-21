package com.peakmain.compose.ui.text

import android.util.TypedValue
import android.widget.TextView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import com.peakmain.compose.basic.BasicColor
import com.peakmain.compose.basic.BasicFont

/**
 * @author ：Peakmain
 * createTime：2025/7/21
 * mail:2726449200@qq.com
 * describe：支持Html的Text
 */
@Composable
fun HtmlText(
    modifier: Modifier = Modifier,
    htmlText: String,
    color: Color = BasicColor.color_666666,
    fontSize: TextUnit = BasicFont.font_12,
) {
    AndroidView(
        modifier=modifier,
        factory = { context ->
            TextView(context).apply {
                text = HtmlCompat.fromHtml(htmlText, HtmlCompat.FROM_HTML_MODE_LEGACY)
                setTextColor(color.toArgb())
                setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize.value)
            }
        },
        update = { textView ->
            textView.text = HtmlCompat.fromHtml(htmlText, HtmlCompat.FROM_HTML_MODE_LEGACY)
        }
    )
}