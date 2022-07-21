package xyz.malkki.gtfs.utils

import org.junit.jupiter.api.assertThrows
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
}