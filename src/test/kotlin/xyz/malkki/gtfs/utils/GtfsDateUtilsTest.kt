package xyz.malkki.gtfs.utils

import org.junit.jupiter.api.assertThrows
import java.time.LocalDate
import java.time.format.DateTimeParseException
import kotlin.test.Test
import kotlin.test.assertEquals

class GtfsDateUtilsTest {
    @Test
    fun `Test parsing invalid date`() {
        assertThrows<DateTimeParseException> { GtfsDateUtils.parseFromString("abc") }
    }

    @Test
    fun `Test parsing valid date`() {
        assertEquals(LocalDate.of(2022, 1, 1), GtfsDateUtils.parseFromString("20220101"))
    }

    @Test
    fun `Test formatting date`() {
        assertEquals("20220101", GtfsDateUtils.formatToString(LocalDate.of(2022, 1, 1)))
    }
}