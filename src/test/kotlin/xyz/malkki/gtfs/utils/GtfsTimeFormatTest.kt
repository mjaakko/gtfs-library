package xyz.malkki.gtfs.utils

import org.junit.jupiter.api.assertThrows
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import kotlin.test.Test
import kotlin.test.assertEquals

class GtfsTimeFormatTest {
    @Test
    fun `Test parsing invalid time`() {
        assertThrows<IllegalArgumentException> { GtfsTimeFormat.parseFromString("abc") }
        assertThrows<IllegalArgumentException> { GtfsTimeFormat.parseFromString("aa:bb:cc") }
    }

    @Test
    fun `Test parsing valid time`() {
        assertEquals(38130, GtfsTimeFormat.parseFromString("10:35:30"))
    }

    @Test
    fun `Test formatting time`() {
        assertEquals("10:35:30", GtfsTimeFormat.formatToString(38130))
    }

    @Test
    fun `Test creating ZonedDateTime`() {
        val date = LocalDate.of(2022, 7, 13)
        val seconds = GtfsTimeFormat.parseFromString("19:30:00")

        val expected = LocalDateTime.of(2022, 7, 13, 19, 30, 0).atZone(ZoneId.of("Europe/Helsinki"))
        assertEquals(expected, GtfsTimeFormat.gtfsTimeToZonedDateTime(date, seconds, ZoneId.of("Europe/Helsinki")))
    }
}