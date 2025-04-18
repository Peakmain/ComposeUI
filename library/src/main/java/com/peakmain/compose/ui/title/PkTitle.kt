package com.peakmain.compose.ui.title

/**
 * author ：Peakmain
 * createTime：2025/4/2
 * mail:2726449200@qq.com
 * describe：
 */
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

sealed class PkTitleType {
    abstract val fontSize: TextUnit
    abstract val fontWeight: FontWeight

    /**
     * 大标题1
     */
    data class BigTitle1(
        override val fontSize: TextUnit = 24.sp,
        override val fontWeight: FontWeight = FontWeight.W500
    ) : PkTitleType()

    /**
     * 大标题2
     */
    data class BigTitle2(
        override val fontSize: TextUnit = 22.sp,
        override val fontWeight: FontWeight = FontWeight.W500
    ) : PkTitleType()

    /**
     * 大标题3
     */
    data class BigTitle3(
        override val fontSize: TextUnit = 18.sp,
        override val fontWeight: FontWeight = FontWeight.W500
    ) : PkTitleType()

    /**
     * 标题1 加粗
     */
    data class TitleBold1(
        override val fontSize: TextUnit = 16.sp,
        override val fontWeight: FontWeight = FontWeight.W500
    ) : PkTitleType()

    /**
     * 标题1 常规
     */
    data class TitleNormal1(
        override val fontSize: TextUnit = 16.sp,
        override val fontWeight: FontWeight = FontWeight.W400
    ) : PkTitleType()

    /**
     * 标题2 加粗
     */
    data class TitleBold2(
        override val fontSize: TextUnit = 15.sp,
        override val fontWeight: FontWeight = FontWeight.W500
    ) : PkTitleType()

    /**
     * 标题2 常规
     */
    data class TitleNormal2(
        override val fontSize: TextUnit = 15.sp,
        override val fontWeight: FontWeight = FontWeight.W400
    ) : PkTitleType()

    /**
     * 小标题 加粗
     */
    data class SmallTitleBold(
        override val fontSize: TextUnit = 14.sp,
        override val fontWeight: FontWeight = FontWeight.W500
    ) : PkTitleType()

    /**
     * 小标题 常规
     */
    data class SmallTitleNormal(
        override val fontSize: TextUnit = 14.sp,
        override val fontWeight: FontWeight = FontWeight.W400
    ) : PkTitleType()

    /**
     * 内文1加粗
     */
    data class TextBold1(
        override val fontSize: TextUnit = 12.sp,
        override val fontWeight: FontWeight = FontWeight.W500
    ) : PkTitleType()
    /**
     * 内文1常规
     */
    data class TextNormal1(
        override val fontSize: TextUnit = 12.sp,
        override val fontWeight: FontWeight = FontWeight.W400
    ) : PkTitleType()
    /**
     * 内文2加粗
     */
    data class TextBold2(
        override val fontSize: TextUnit = 11.sp,
        override val fontWeight: FontWeight = FontWeight.W500
    ) : PkTitleType()

    /**
     * 内文2常规
     */
    data class TextNormal2(
        override val fontSize: TextUnit = 11.sp,
        override val fontWeight: FontWeight = FontWeight.W400
    ) : PkTitleType()
}
@Composable
fun PkTitle(
    text: String,
    type: PkTitleType = PkTitleType.BigTitle1(),
    modifier: Modifier = Modifier,
    color: Color = Color(0xFF333333),
    fontStyle: FontStyle? = null,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    maxLines: Int =1,
    style: TextStyle = LocalTextStyle.current
) {
    Text(
        text,
        modifier,
        color,
        type.fontSize,
        fontStyle,
        type.fontWeight,
        textAlign = textAlign,
        overflow = overflow,
        maxLines = maxLines,
        style = style
    )
}

@Preview
@Composable
fun PkTitleComposablePreview() {
  Column {
      PkTitle("大标题1",PkTitleType.BigTitle1())
      PkTitle("大标题2",PkTitleType.BigTitle2())
      PkTitle("大标题3",PkTitleType.BigTitle3())
      PkTitle("标题1加粗",PkTitleType.TitleBold1())
      PkTitle("标题1常规",PkTitleType.TitleNormal1())
      PkTitle("标题2加粗",PkTitleType.TitleBold2())
      PkTitle("标题2常规",PkTitleType.TitleNormal2())
      PkTitle("小标题加粗",PkTitleType.SmallTitleBold())
      PkTitle("小标题常规",PkTitleType.SmallTitleNormal())
      PkTitle("内文1加粗",PkTitleType.TextBold1())
      PkTitle("内文1正常",PkTitleType.TextNormal1())
      PkTitle("内文2加粗",PkTitleType.TextBold2())
      PkTitle("内文2正常",PkTitleType.TextNormal2())
  }
}
