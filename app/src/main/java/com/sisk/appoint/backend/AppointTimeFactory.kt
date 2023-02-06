package com.sisk.appoint.backend

import com.sisk.appoint.model.Period
import java.time.Duration
import java.time.LocalTime
import java.util.*

class AppointTimeFactory {
    private val morningSession = Session(
        start = LocalTime.of(8, 0),
        end = LocalTime.of(13, 0),
        interval = Duration.ofMinutes(60),
        name = "Morning"
    )

    private val afternoonSession = Session(
        start = LocalTime.of(14, 0),
        end = LocalTime.of(17, 0),
        interval = Duration.ofMinutes(60),
        name = "Morning"
    )

    fun getWorkingPeriods(): Map<String, List<Period>> {
        val morning = workingPeriods(morningSession).map { it.toPeriod() }
        val afternoon = workingPeriods(afternoonSession).map { it.toPeriod() }

        return mapOf("Morning" to morning, "Afternoon" to afternoon)


    }

    private fun workingPeriods(session: Session): TreeSet<WorkPeriod> {
        val remainder = session.sessionDuration % session.interval.toMinutes()
        val seed = WorkPeriod(start = session.start, end = session.start.plus(session.interval), session = session.name)
        val periods = generateSequence(seed){
            WorkPeriod(start = it.end, end = it.end.plus(session.interval), session = session.name)
        }.takeWhile { it.end.isBefore(session.end) || it.end.compareTo(session.end) == 0 }
            .toCollection(TreeSet<WorkPeriod>())

        if (remainder > 0){
            val lastPeriod = periods.pollLast()
            val copy = lastPeriod?.copy(end = lastPeriod.end.plusMinutes(remainder))
            copy?.let {
                periods.add(it)
            }
        }
        return periods
    }
}