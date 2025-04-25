package com.peakmain.compose.ui.text

/**
 * author ：Peakmain
 * createTime：2025/4/25
 * mail:2726449200@qq.com
 * describe：高亮文本
 */
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview

/**
 * author ：Peakmain
 * createTime：2025/4/25
 * mail:2726449200@qq.com
 * describe：高亮关键词的文本组件
 */

/**
 * 高亮关键词的文本组件。
 *
 * @param text 需要处理的原始文本。
 * @param keywords 高亮关键字列表（为空时不进行高亮）。
 * @param highlightColor 高亮文本颜色，默认值为 [Color.Red]。
 * @param style 基础文本样式，默认值为 [LocalTextStyle.current]，高亮部分继承此样式并叠加颜色。
 */
@Composable
fun PkHighlightText(
    text: String,
    keywords: List<String>,
    highlightColor: Color = Color.Red,
    style: TextStyle = LocalTextStyle.current
) {
    val annotatedString = remember(text, keywords) {
        buildAnnotatedString {
            if (keywords.isEmpty()) {
                append(text) // 无关键字直接返回原文本
                return@buildAnnotatedString
            }

            // 1. 生成正则表达式模式（处理特殊字符）
            val pattern = keywords
                .filter { it.isNotEmpty() } // 过滤空关键字
                .distinct() // 去重
                .joinToString("|") { Regex.escape(it) } // 转义正则特殊字符
                .takeIf { it.isNotEmpty() } ?: run {
                append(text)
                return@buildAnnotatedString
            }

            // 2. 创建不区分大小写的正则表达式
            val regex = try {
                Regex(pattern, RegexOption.IGNORE_CASE)
            } catch (e: Exception) {
                // 处理非法正则表达式（如空模式）
                append(text)
                return@buildAnnotatedString
            }

            // 3. 查找所有匹配区间并合并重叠
            val matches = regex.findAll(text)
                .map { it.range.first to it.range.last + 1 } // 转换为前闭后开区间
                .toList()

            val mergedRanges = mergeIntervals(matches)

            // 4. 构建带高亮的字符串
            var currentIndex = 0
            for ((start, end) in mergedRanges) {
                if (start > currentIndex) {
                    // 添加非高亮部分
                    append(text.substring(currentIndex, start))
                }
                // 添加高亮部分（保留原文字大小写）
                withStyle(SpanStyle(color = highlightColor)) {
                    append(text.substring(start, end))
                }
                currentIndex = end
            }
            // 添加剩余文本
            if (currentIndex < text.length) {
                append(text.substring(currentIndex))
            }
        }
    }
    Text(text = annotatedString, style = style)
}

/**
 * 合并重叠区间算法
 * 输入示例：[(0,3), (2,5), (6,7)] → 输出：[(0,5), (6,7)]
 */
private fun mergeIntervals(intervals: List<Pair<Int, Int>>): List<Pair<Int, Int>> {
    if (intervals.isEmpty()) return emptyList()

    val sorted = intervals.sortedBy { it.first }
    val merged = mutableListOf<Pair<Int, Int>>()
    var current = sorted.first()

    for (next in sorted) {
        if (next.first <= current.second) {
            // 区间重叠/相邻，合并
            current = current.first to maxOf(current.second, next.second)
        } else {
            merged.add(current)
            current = next
        }
    }
    merged.add(current)

    return merged
}

@Preview
@Composable
fun PkHighlightTextPreview() {
    Column(modifier = Modifier.background(Color.White)) {
        // 示例1：基本用法（多个关键字）
        PkHighlightText(
            text = "Jetpack Compose 是 Android 的现代 UI 工具包",
            keywords = listOf("compose", "android"),
            highlightColor = Color.Blue,
            style = MaterialTheme.typography.body1
        )

        // 示例2：含特殊字符的关键字
        PkHighlightText(
            text = "价格：$199 (限时优惠)",
            keywords = listOf("$199", "（限时优惠）"),
            highlightColor = Color(0xFF4CAF50)
        )

        // 示例3：无关键字/空列表
        PkHighlightText(
            text = "Hello World",
            keywords = emptyList() // 显示普通文本
        )
    }
}

