package xyz.malkki.gtfs.utils

import org.junit.jupiter.api.assertThrows
import java.time.LocalDate
import java.time.format.DateTimeParseException
import kotlin.test.Test
import kotlin.test.assertEquals

class GtfsDateFormatTest {
    @Test
    fun `Test parsing invalid date`() {
        assertThrows<DateTimeParseException> { GtfsDateFormat.parseFromString("abc") }
    }

    @Test
    fun `Test parsing valid date`() {
        assertEquals(LocalDate.of(2022, 1, 1), GtfsDateFormat.parseFromString("20220101"))
    }

    @Test
    fun `Test formatting date`() {
        assertEquals("20220101", GtfsDateFormat.formatToString(LocalDate.of(2022, 1, 1)))
    }
}