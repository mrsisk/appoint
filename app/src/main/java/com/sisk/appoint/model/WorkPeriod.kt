package com.sisk.appoint.model

import java.time.LocalTime
import java.time.ZonedDateTime

data class WorkPeriod(
    val start: ZonedDateTime = ZonedDateTime.now(),
    val end: ZonedDateTime = ZonedDateTime.now(),
    val session: SessionType = SessionType.MORNING
): Comparable<WorkPeriod> {
    override fun compareTo(other: WorkPeriod): Int {
        return start.compareTo(other.start)
    }
}
