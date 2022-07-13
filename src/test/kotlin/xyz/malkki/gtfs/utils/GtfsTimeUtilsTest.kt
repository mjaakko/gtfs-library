package xyz.malkki.gtfs.utils

import org.junit.jupiter.api.assertThrows
import kotlin.test.Test
import kotlin.test.assertEquals

class GtfsTimeUtilsTest {
    @Test
    fun `Test parsing invalid time`() {
        assertThrows<IllegalArgumentException> { GtfsTimeUtils.parseFromString("abc") }
        assertThrows<IllegalArgumentException> { GtfsTimeUtils.parseFromString("aa:bb:cc") }
    }

    @Test
    fun `Test parsing valid time`() {
        assertEquals(38130, GtfsTimeUtils.parseFromString("10:35:30"))
    }

    @Test
    fun `Test formatting time`() {
        assertEquals("10:35:30", GtfsTimeUtils.formatToString(38130))
    }
}