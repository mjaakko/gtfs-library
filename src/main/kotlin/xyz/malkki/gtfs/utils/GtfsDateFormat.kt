package xyz.malkki.gtfs.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

/**
 * Contains functions for parsing and formatting dates in GTFS format
 */
object GtfsDateFormat {
    private const val GTFS_DATE_FORMAT = "yyyyMMdd"

    private val dateTimeFormatter = DateTimeFormatter.ofPattern(GTFS_DATE_FORMAT)!!

    /**
     * Parses date string in GTFS format to a [LocalDate]
     *
     * @throws DateTimeParseException If parsing fails
     */
    @JvmStatic
    @Throws(DateTimeParseException::class)
    fun parseFromString(str: String) = LocalDate.parse(str, dateTimeFormatter)!!

    /**
     * Formats [LocalDate] to a date string in GTFS format
     */
    @JvmStatic
    fun formatToString(date: LocalDate): String = date.format(dateTimeFormatter)
}