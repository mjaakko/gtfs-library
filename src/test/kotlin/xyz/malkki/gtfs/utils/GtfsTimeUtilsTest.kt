package xyz.malkki.gtfs.utils

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import kotlin.test.Test
import kotlin.test.assertEquals

class GtfsTimeUtilsTest {
    @Test
    fun `Test correct ZonedDateTime is calculated from GTFS service day and time`() {
        val dateTime = GtfsTimeUtils.gtfsTimeToZonedDateTime(LocalDate.of(2022, 1, 1), 12 * 60 * 60, ZoneId.of("Asia/Tokyo"))

        assertEquals(ZonedDateTime.of(LocalDateTime.of(2022, 1, 1, 12, 0, 0), ZoneId.of("Asia/Tokyo")), dateTime)
    }
}