package com.peakmain.compose.utils

import java.util.Calendar

/**
 * author ：Peakmain
 * createTime：2025/7/3
 * mail:2726449200@qq.com
 * describe：日历工具类
 */
object CalendarUtils {

    /**
     * 获取当前日期模型
     */
    fun getCurrentDateModel(): CalendarModel {
        val calendar = Calendar.getInstance()
        return CalendarModel(
            year = calendar.get(Calendar.YEAR),
            month = calendar.get(Calendar.MONTH) + 1, // 月份从0开始，需+1
            weekDay = calendar.get(Calendar.DAY_OF_WEEK), // 周日=1, 周一=2,...,周六=7
            day = calendar.get(Calendar.DAY_OF_MONTH)
        )
    }

    /**
     * 计算两个日期的天数差
     */
    fun getDateDiff(start: CalendarModel, end: CalendarModel): Int {
        val startCal = Calendar.getInstance().apply {
            set(start.year, start.month - 1, start.day, 0, 0, 0)
            set(Calendar.MILLISECOND, 0)
        }

        val endCal = Calendar.getInstance().apply {
            set(end.year, end.month - 1, end.day, 0, 0, 0)
            set(Calendar.MILLISECOND, 0)
        }

        val diffMs = endCal.timeInMillis - startCal.timeInMillis
        return (diffMs / (1000 * 3600 * 24)).toInt()
    }

    /**
     * 判断是否同一天
     */
    fun isSameDay(month: Month, day: Int, target: CalendarModel): Boolean {
        return month.year == target.year &&
                month.month == target.month &&
                day == target.day
    }

    /**
     * 判断是否为今日
     */
    fun isToday(model: CalendarModel): Boolean {
        val today = getCurrentDateModel()
        return today.year == model.year &&
                today.month == model.month &&
                today.day == model.day
    }

    /**
     * 获取明天的日期模型
     */
    fun getTomorrowDateModel(): CalendarModel {
        val calendar = Calendar.getInstance().apply {
            add(Calendar.DATE, 1)
        }
        return CalendarModel(
            year = calendar.get(Calendar.YEAR),
            month = calendar.get(Calendar.MONTH) + 1,
            weekDay = calendar.get(Calendar.DAY_OF_WEEK),
            day = calendar.get(Calendar.DAY_OF_MONTH)
        )
    }

    /**
     * 生成月份日期数组
     */
    fun getMonthDate(month: Int, year: Int): IntArray {
        val cal = Calendar.getInstance().apply {
            set(year, month - 1, 1)
        }

        // 获取当月天数
        val daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH)
        // 获取第一天是周几 (周日=1, 周六=7)
        val firstDayOfWeek = cal.get(Calendar.DAY_OF_WEEK)
        // 获取最后一天是周几
        cal.set(Calendar.DAY_OF_MONTH, daysInMonth)
        val lastDayOfWeek = cal.get(Calendar.DAY_OF_WEEK)

        // 创建日期数组（6行*7天=42格）
        val days = IntArray(42)
        var index = 0

        // 填充上月日期 (置0)
        repeat(firstDayOfWeek - 1) {
            days[index++] = 0
        }

        // 填充当月日期
        repeat(daysInMonth) {
            days[index++] = it + 1
        }

        // 填充下月日期 (置0)
        repeat(42 - (firstDayOfWeek - 1 + daysInMonth)) {
            days[index++] = 0
        }

        return days
    }
    fun isCalendarAfter(a: CalendarModel, b: CalendarModel): Boolean {
        return when {
            a.year != b.year -> a.year > b.year
            a.month != b.month -> a.month > b.month
            else -> a.day > b.day
        }
    }
    /**
     * 判断日期先后
     */
    fun isCalendarBefore(a: CalendarModel, b: CalendarModel): Boolean {
        return when {
            a.year != b.year -> a.year < b.year
            a.month != b.month -> a.month < b.month
            else -> a.day < b.day
        }
    }
    fun isSelectBetweenDay(
        day: Int,
        month: Int,
        year: Int,
        start: CalendarModel,
        end: CalendarModel
    ): Boolean {
        if (day == 0) return false // 占位日期不显示选中背景
        if (end.day == 0) return false

        // 创建当前日期对象
        val currentDate = CalendarModel(year, month, 0, day)

        // 确保日期在开始和结束之间
        if (!isCalendarAfter(currentDate, start)) return false
        if (!isCalendarBefore(currentDate, end)) return false

        // 特殊处理：开始和结束在同一个月
        if (start.year == end.year && start.month == end.month) {
            return year == start.year && month == start.month
        }

        // 当前日期在开始月份
        if (year == start.year && month == start.month) {
            return day > start.day
        }

        // 当前日期在结束月份
        if (year == end.year && month == end.month) {
            return day < end.day
        }

        // 当前日期在中间月份
        return when (year) {
            start.year -> month > start.month
            end.year -> month < end.month
            else -> year > start.year && year < end.year
        }
    }
    /**
     * 获取连续月份数据
     */
    fun getAllDate(
        currentYear: Int,
        currentMonth: Int,
        selectStart: CalendarModel,
        contentData: MutableList<Month>
    ): Int {
        val months = mutableListOf<Month>()
        var initialIndex = 0

        for (i in 0..6) {
            // 计算相对月份
            val monthValue = (currentMonth + i - 1).let {
                if (it < 0) it + 12 else it
            } % 12 + 1

            val yearOffset = (currentMonth - 1 + i) / 12
            val year = currentYear + yearOffset

            val days = getMonthDate(monthValue, year)
            val month = Month(
                headTitle = "${year}年 ${monthValue}月",
                month = monthValue,
                days = days.toTypedArray(),
                year = year
            )

            // 标记初始选中位置
            if (selectStart.month == monthValue) {
                initialIndex = i
            }
            months.add(month)
        }

        contentData.addAll(months)
        return initialIndex
    }
}

// 数据类定义
data class CalendarModel(
    val year: Int,
    val month: Int,
    val weekDay: Int, // 周日=1, 周一=2,...,周六=7
    val day: Int
)

data class Month(
    val headTitle: String,
    val month: Int,
    val days: Array<Int>,
    val year: Int
) {
    // 重写equals和hashCode确保正确比较
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Month
        return headTitle == other.headTitle &&
                month == other.month &&
                days.contentEquals(other.days) &&
                year == other.year
    }

    override fun hashCode(): Int {
        var result = headTitle.hashCode()
        result = 31 * result + month
        result = 31 * result + days.contentHashCode()
        result = 31 * result + year
        return result
    }
}