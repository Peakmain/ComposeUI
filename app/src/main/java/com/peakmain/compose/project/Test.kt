package com.peakmain.compose.project

/**
 * @author ：Peakmain
 * createTime：2026/1/9
 * mail:2726449200@qq.com
 * describe：
 */
// 密封类：替代枚举，子类都是单例（object）
sealed class OrderStatusSealed {
    object Pending : OrderStatusSealed()    // 待支付
    object Paid : OrderStatusSealed()       // 已支付
    object Shipped : OrderStatusSealed()    // 已发货
    object Delivered : OrderStatusSealed()  // 已送达
}

// 使用密封类（用法和枚举几乎一致）
fun handleOrder(status: OrderStatusSealed) {
    when (status) {
        OrderStatusSealed.Pending -> println("请尽快支付")
        OrderStatusSealed.Paid -> println("订单已支付，等待发货")
        OrderStatusSealed.Shipped -> println("订单已发货，等待收货")
        OrderStatusSealed.Delivered -> println("订单已完成")
    }
}