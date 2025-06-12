package com.peakmain.compose.ui.button

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

/**
 * author ：Peakmain
 * createTime：2025/4/10
 * mail:2726449200@qq.com
 * describe：
 */

@Immutable
class PkShapes(
    val none: CornerBasedShape = RoundedCornerShape(0.dp),
    val small: CornerBasedShape = RoundedCornerShape(2.dp),
    val medium: CornerBasedShape = RoundedCornerShape(4.dp),
    val large: CornerBasedShape = RoundedCornerShape(8.dp),
    val extraLarge: CornerBasedShape = RoundedCornerShape(12.dp),
    val capsule: CornerBasedShape = RoundedCornerShape(percent = 50), // 胶囊型
    val circle: CornerBasedShape = RoundedCornerShape(percent = 50) // 圆形，默认是等宽等高的控件，圆形的效果
) {

    fun copy(
        none: CornerBasedShape = this.none,
        small: CornerBasedShape = this.small,
        medium: CornerBasedShape = this.medium,
        large: CornerBasedShape = this.large,
        extraLarge: CornerBasedShape = this.extraLarge,
        capsule: CornerBasedShape = this.capsule,
        circle: CornerBasedShape = this.circle
    ): PkShapes = PkShapes(
        none = none,
        small = small,
        medium = medium,
        large = large,
        extraLarge = extraLarge,
        capsule = capsule,
        circle = circle
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PkShapes) return false

        if (none != other.none) return false
        if (small != other.small) return false
        if (medium != other.medium) return false
        if (large != other.large) return false
        if (extraLarge != other.extraLarge) return false
        if (capsule != other.capsule) return false
        if (circle != other.circle) return false

        return true
    }

    override fun hashCode(): Int {
        var result = none.hashCode()
        result = 31 * result + small.hashCode()
        result = 31 * result + medium.hashCode()
        result = 31 * result + large.hashCode()
        result = 31 * result + extraLarge.hashCode()
        result = 31 * result + capsule.hashCode()
        result = 31 * result + circle.hashCode()
        return result
    }

    override fun toString(): String {
        return "PkShapes(none=$none, small=$small, medium=$medium, large=$large, extraLarge=$extraLarge, capsule=$capsule, circle=$circle)"
    }
}

internal val LocalPkShapes = staticCompositionLocalOf { PkShapes() }
