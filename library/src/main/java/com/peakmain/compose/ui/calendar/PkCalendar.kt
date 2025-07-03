package com.peakmain.compose.ui.calendar

/**
 * @author ：Peakmain
 * createTime：2025/7/3
 * mail:2726449200@qq.com
 * describe：
 */
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.peakmain.compose.ui.PkGridLayout
import com.peakmain.compose.utils.CalendarModel
import com.peakmain.compose.utils.CalendarUtils
import com.peakmain.compose.utils.Month
import java.util.*

/**
 * author ：Peakmain
 * createTime：2025/7/3
 * mail:2726449200@qq.com
 * describe：
 */
@Composable
fun PkCalendar(
    currentMonth: Int,
    currentDay: Int,
    currentYear: Int,
    selectStartModel: CalendarModel,
    selectEndModel: CalendarModel,
    onSelectStartModelChanged: (CalendarModel) -> Unit,
    onSelectEndModelChanged: (CalendarModel) -> Unit,
    noUsedMessage: String = "您选择的日期不在可选范围内",
    noSelectLeaveMessage: String = "请选择离店日期",
    maxUseSelectDate: Int = 30,
    selectedBackgroundColor: Color = Color(0xFF6CBD9B),
    selectedBetweenBackgroundColor: Color = Color(0x1A6CBD9B),
    isBefore6AM: Boolean = true,
    onSelectCalendarResult: (CalendarModel, CalendarModel) -> Unit = { _, _ -> },
    onMaxUseSelectDateCallback: () -> Unit = {}
) {

    val calendarUtils = remember { CalendarUtils }
    val weeks = listOf("日", "一", "二", "三", "四", "五", "六")
    var contentData by remember { mutableStateOf(listOf<Month>()) }
    var initialIndex by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        val months = mutableListOf<Month>()
        initialIndex = calendarUtils.getAllDate(
            currentYear, currentMonth, selectStartModel, months
        )
        contentData = months
    }

    Column(modifier = Modifier.fillMaxSize()) {
        // 星期标题
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF1F4F6))
                .padding(vertical = 9.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            weeks.forEach { day ->
                Text(
                    text = day,
                    fontSize = 11.sp,
                    color = Color(0xFF67696A)
                )
            }
        }
        // 月份列表
        LazyColumn(
            state = rememberLazyListState(initialFirstVisibleItemIndex = initialIndex),
            modifier = Modifier.weight(1f)
        ) {
            itemsIndexed(contentData) { index, month ->
                MonthView(
                    month = month,
                    index = index,
                    contentData = contentData,
                    selectStartModel = selectStartModel,
                    selectEndModel = selectEndModel,
                    currentYear = currentYear,
                    currentMonth = currentMonth,
                    currentDay = currentDay,
                    calendarUtils = calendarUtils,
                    isBefore6AM = isBefore6AM,
                    selectedBackgroundColor = selectedBackgroundColor,
                    selectedBetweenBackgroundColor = selectedBetweenBackgroundColor,
                    noUsedMessage = noUsedMessage,
                    noSelectLeaveMessage = noSelectLeaveMessage,
                    maxUseSelectDate = maxUseSelectDate,
                    onDayClick = { day, monthItem ->
                        handleDayClick(
                            day,
                            monthItem,
                            calendarUtils,
                            selectStartModel,
                            selectEndModel,
                            onSelectStartModelChanged,
                            onSelectEndModelChanged,
                            noUsedMessage,
                            noSelectLeaveMessage,
                            maxUseSelectDate,
                            currentDay,
                            onMaxUseSelectDateCallback,
                            onSelectCalendarResult
                        )
                    }
                )
            }
        }
    }

}

@Composable
fun MonthView(
    month: Month,
    index: Int,
    contentData: List<Month>,
    selectStartModel: CalendarModel,
    selectEndModel: CalendarModel,
    currentYear: Int,
    currentMonth: Int,
    currentDay: Int,
    calendarUtils: CalendarUtils,
    isBefore6AM: Boolean,
    selectedBackgroundColor: Color,
    selectedBetweenBackgroundColor: Color,
    noUsedMessage: String,
    noSelectLeaveMessage: String,
    maxUseSelectDate: Int,
    onDayClick: (Int, Month) -> Unit,
) {
    Column {
        // 月份标题
        Text(
            text = month.headTitle,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF272A2B),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(12.dp)
        )

        // 日期网格
        val rows =
            if (month.days.size > 35 || (index == contentData.lastIndex && month.days.size == 35)) 6 else 5
        PkGridLayout(
            columns = 7,
            month.days.toMutableList(),
            columnSpacing = 0.dp
        ) { index, item ->
            DayCell(
                day = item,
                month = month,
                selectStartModel = selectStartModel,
                selectEndModel = selectEndModel,
                currentYear = currentYear,
                currentMonth = currentMonth,
                currentDay = currentDay,
                calendarUtils = calendarUtils,
                isBefore6AM = isBefore6AM,
                selectedBackgroundColor = selectedBackgroundColor,
                selectedBetweenBackgroundColor = selectedBetweenBackgroundColor,
                onDayClick = onDayClick
            )
        }
    }
}

@Composable
fun DayCell(
    day: Int,
    month: Month,
    selectStartModel: CalendarModel,
    selectEndModel: CalendarModel,
    currentYear: Int,
    currentMonth: Int,
    currentDay: Int,
    calendarUtils: CalendarUtils,
    isBefore6AM: Boolean,
    selectedBackgroundColor: Color,
    selectedBetweenBackgroundColor: Color,
    onDayClick: (Int, Month) -> Unit
) {
    val isToday = calendarUtils.isToday(CalendarModel(month.year, month.month, 0, day))
    val isStartDay = calendarUtils.isSameDay(month, day, selectStartModel)
    val isEndDay = calendarUtils.isSameDay(month, day, selectEndModel)
    val isBetween = calendarUtils.isSelectBetweenDay(
        day, month.month, month.year, selectStartModel, selectEndModel
    )
    val isUnselected = isDayUnselected(
        day, month, currentYear, currentMonth, currentDay, isBefore6AM
    )


    val backgroundColor = when {
        isStartDay || isEndDay -> selectedBackgroundColor
        isBetween -> selectedBetweenBackgroundColor
        else -> Color.White
    }

    val textColor = when {
        isUnselected -> Color(0xFFA9AAAA)
        isStartDay || isEndDay -> Color.White
        else -> Color(0xFF272A2B)
    }

    val borderRadius = when {
        isStartDay -> RoundedCornerShape(topStart = 4.dp, bottomStart = 4.dp)
        isEndDay -> RoundedCornerShape(topEnd = 4.dp, bottomEnd = 4.dp)
        isBetween -> RoundedCornerShape(0.dp)
        else -> RoundedCornerShape(4.dp)
    }

    Box(
        modifier = Modifier
            .clip(borderRadius)
            .background(backgroundColor)
            .clickable(
                enabled = day != 0 && !isUnselected,
                onClick = { onDayClick(day, month) }
            )
            .padding(4.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "今日",
                fontSize = 10.sp,
                color = Color.White.copy(alpha = 0.7f),
                modifier = Modifier.alpha(if (isToday && isStartDay) 1f else 0f)
            )
            Text(
                text = if (day == 0) "" else day.toString(),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = textColor
            )
            Text(
                text = if (isStartDay) "入住" else "离店",
                fontSize = 10.sp,
                color = Color.White,
                modifier = Modifier.alpha(if (isStartDay || isEndDay) 1f else 0f)
            )
        }
    }
}

// 日期点击处理逻辑
fun handleDayClick(
    day: Int,
    monthItem: Month,
    calendarUtils: CalendarUtils,
    selectStartModel: CalendarModel,
    selectEndModel: CalendarModel,
    onSelectStartModelChanged: (CalendarModel) -> Unit,
    onSelectEndModelChanged: (CalendarModel) -> Unit,
    noUsedMessage: String,
    noSelectLeaveMessage: String,
    maxUseSelectDate: Int,
    currentDay: Int,
    onMaxUseSelectDateCallback: () -> Unit,
    onSelectCalendarResult: (CalendarModel, CalendarModel) -> Unit
) {


    val weekIndex = monthItem.days.indexOf(day) % 7
    val clickedDate = CalendarModel(monthItem.year, monthItem.month, weekIndex, day)

    when {
        selectStartModel.day > 0 -> {
            if (calendarUtils.isSelectBetweenDay(
                    day, monthItem.month, monthItem.year, selectStartModel, selectEndModel
                )
            ) {
                // 选择在已选日期之间
                onSelectStartModelChanged(clickedDate)
                onSelectEndModelChanged(CalendarModel(0, 0, 0, 0))
            } else if (calendarUtils.isCalendarBefore(clickedDate, selectStartModel)) {
                // 选择在开始日期之前
                onSelectStartModelChanged(clickedDate)
                onSelectEndModelChanged(CalendarModel(0, 0, 0, 0))
            } else if (selectEndModel.day == 0) {
                // 选择结束日期
                if (calendarUtils.isSameDay(monthItem, day, selectStartModel)) return

                onSelectEndModelChanged(clickedDate)
                val dateDiff = calendarUtils.getDateDiff(selectStartModel, clickedDate)

                if (dateDiff > maxUseSelectDate) {
                    onMaxUseSelectDateCallback()
                    onSelectEndModelChanged(CalendarModel(0, 0, 0, 0))
                } else {
                    onSelectCalendarResult(selectStartModel, clickedDate)
                }
            } else {
                // 重新选择开始日期
                onSelectStartModelChanged(clickedDate)
                onSelectEndModelChanged(CalendarModel(0, 0, 0, 0))
            }
        }

        else -> {
            // 选择开始日期
            onSelectStartModelChanged(clickedDate)
        }
    }
}

// 日期是否不可选
fun isDayUnselected(
    day: Int,
    monthItem: Month,
    currentYear: Int,
    currentMonth: Int,
    currentDay: Int,
    isBefore6AM: Boolean
): Boolean {
    if (day == 0) return true // 占位日期不可选

    // 1. 年份早于当前年
    if (monthItem.year < currentYear) {
        return true
    }

    // 2. 同年但月份早于当前月
    if (monthItem.year == currentYear && monthItem.month < currentMonth) {
        return true
    }

    // 3. 同年同月但日期早于当前日
    if (monthItem.year == currentYear &&
        monthItem.month == currentMonth &&
        day < currentDay
    ) {

        // 处理6点前规则
        if (isBefore6AM) {
            val calendar = Calendar.getInstance()
            val currentHour = calendar.get(Calendar.HOUR_OF_DAY)

            // 如果是前一天且当前时间在6点之前，则可用
            if (day == currentDay - 1 && currentHour < 6) {
                return false
            }
            return true
        }
        return true
    }

    // 其他情况（未来日期）可选
    return false
}

// 圆角定义
data class BorderRadius(
    val topStart: Dp = 0.dp,
    val topEnd: Dp = 0.dp,
    val bottomStart: Dp = 0.dp,
    val bottomEnd: Dp = 0.dp
)