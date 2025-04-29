package com.peakmain.compose.ext

import android.text.TextUtils

/**
 * author ：Peakmain
 * createTime：2025/4/29
 * mail:2726449200@qq.com
 * describe：字符串工具类
 */
fun String?.isEmpty(): Boolean {
    return TextUtils.isEmpty(this)
}
fun String?.isEmpty(block: () -> Unit): String? {
    if(isEmpty()){
        block.invoke()
    }
    return this
}
fun String?.isNotEmpty(): Boolean {
    return !isEmpty()
}

fun String?.isNotEmpty(block: ((String)) -> Unit): String? {
    if (isNotEmpty()) {
        block.invoke(this!!)
    }
    return this
}
