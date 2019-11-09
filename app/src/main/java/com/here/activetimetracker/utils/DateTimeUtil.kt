package com.here.activetimetracker.utils

import java.util.concurrent.TimeUnit

object DateTimeUtil {


    fun getTimeInDays(milliseconds: Long): Long {
        return (TimeUnit.MILLISECONDS.toDays(milliseconds))
    }


    fun getTimeInHours(milliseconds: Long): Long {
        return (TimeUnit.MILLISECONDS.toHours(milliseconds)
                - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(milliseconds)))
    }


    fun getTimeInMins(milliseconds: Long): Long {
        return (TimeUnit.MILLISECONDS.toMinutes(milliseconds)
                - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(milliseconds)))
    }


    fun getTimeInSeconds(milliseconds: Long): Long {
        return (TimeUnit.MILLISECONDS.toSeconds(milliseconds)
                - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliseconds)))
    }


    fun isTimeInSeconds(currentTime: Long): Boolean {
        return DateTimeUtil.getTimeInMins(milliseconds = currentTime) < 1 && DateTimeUtil.getTimeInHours(milliseconds = currentTime) < 1 && DateTimeUtil.getTimeInDays(
            milliseconds = currentTime
        ) < 1
    }


    fun isTimeInMins(currentTime: Long): Boolean {
        return (DateTimeUtil.getTimeInMins(milliseconds = currentTime) > 0) && DateTimeUtil.getTimeInHours(milliseconds = currentTime) < 1
    }

    fun isTimeInHours(currentTime: Long): Boolean {
        return (DateTimeUtil.getTimeInHours(milliseconds = currentTime) > 0) && DateTimeUtil.getTimeInDays(milliseconds = currentTime) < 1
    }

    fun isTimeInDays(currentTime: Long): Boolean {
        return (DateTimeUtil.getTimeInDays(milliseconds = currentTime) > 0) && DateTimeUtil.getTimeInDays(milliseconds = currentTime) > 0
    }

    fun setTimeFormatInDays(currentTime: Long): String {
        return String.format(
            "%dd %dh %dm %ds",
            getTimeInDays(milliseconds = currentTime),
            getTimeInHours(milliseconds = currentTime),
            getTimeInMins(milliseconds = currentTime),
            getTimeInSeconds(milliseconds = currentTime)
        )
    }


    fun setTimeFormatInHours(currentTime: Long): String {
        return String.format(
            "%dh %dm %ds",
            getTimeInHours(milliseconds = currentTime),
            getTimeInMins(milliseconds = currentTime),
            getTimeInSeconds(milliseconds = currentTime)
        )
    }


    fun setTimeFormatInMins(currentTime: Long): String {
        return String.format(
            "%dm %ds",
            DateTimeUtil.getTimeInMins(milliseconds = currentTime),
            DateTimeUtil.getTimeInSeconds(milliseconds = currentTime)
        )

    }

    fun setTimeFormatInSeconds(currentTime: Long): String {
        return String.format("%d s", DateTimeUtil.getTimeInSeconds(milliseconds = currentTime))
    }


}