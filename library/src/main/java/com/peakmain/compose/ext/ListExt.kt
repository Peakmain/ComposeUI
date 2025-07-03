package com.peakmain.compose.ext


/**
 * author ：Peakmain
 * createTime：2025/3/11
 * mail:2726449200@qq.com
 * describe：List扩展类
 */
/**
 * 获取List的大小
 * ①、如果List为null，则返回0。
 * ②、如果List不为null，则返回size
 */
fun <T> List<T>?.orSize(): Int {
    return this?.size ?: return 0
}

/**
 * 判断List大小是否大于0
 * ①、如果List为null，则返回false。
 * ②、如果List不为null，则判断大小是否大于0
 */
fun <T> List<T>?.sizeBigZero(): Boolean {
    return (this?.size ?: 0) > 0
}

/**
 * List的大小是否为0
 * ①、如果List为null，则返回true。
 * ②、如果List不为null，则判断大小是否等于0
 */
fun <T> List<T>?.sizeEqualZero(): Boolean {
    return (this?.size ?: 0) == 0
}

/**
 * 返回此列表中指定[fromIndex]（包含）和[toIndex]（不包含）之间部分的视图
 */
fun <T> List<T>?.slice(fromIndex: Int, toIndex: Int): List<T>? {
    return if (this.orSize() > (toIndex - fromIndex)) this?.subList(fromIndex, toIndex) else this
}

/**
 * 是否是最后一条数据
 */
fun <T> List<T>?.isLastItem(position: Int): Boolean {
    if (this.isNullOrEmpty()) {
        return false
    }
    val size = this.size
    return position == size - 1
}