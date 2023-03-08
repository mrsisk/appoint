package com.sisk.appoint.model

import java.time.LocalDate
import java.util.TreeSet

data class WorkDay(
    val dayOfWeek: String = "",
    val dayOfMonth: String = "",
    val fullDate: LocalDate = LocalDate.now(),
    val workPeriods: TreeSet<WorkPeriod> = TreeSet()
)

data class WorkDayUi(
    val dayOfWeek: String = "",
    val dayOfMonth: String = "",
    val fullDate: LocalDate = LocalDate.now(),
    val workPeriod: Map<SessionType, List<WorkPeriod>> = emptyMap()
)

fun WorkDay.toUiModel(): WorkDayUi{
    val wp = workPeriods.groupBy { it.session }
    return WorkDayUi(dayOfWeek, dayOfMonth, fullDate, wp)
}
