package xyz.malkki.gtfs.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object GtfsDateFormat {
    private const val GTFS_DATE_FORMAT = "yyyyMMdd"

    private val dateTimeFormatter = DateTimeFormatter.ofPattern(GTFS_DATE_FORMAT)!!

    @JvmStatic
    fun parseFromString(str: String) = LocalDate.parse(str, dateTimeFormatter)!!

    @JvmStatic
    fun formatToString(date: LocalDate) = date.format(dateTimeFormatter)
}