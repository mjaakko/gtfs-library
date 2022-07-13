package xyz.malkki.gtfs.model

import org.junit.jupiter.api.Test
import java.time.LocalDate
import kotlin.test.assertContentEquals

class CalendarTest {
    @Test
    fun `Test calendar dates iterator`() {
        val calendar = Calendar("1", true, false, false, false, false, false, true, LocalDate.of(2022, 7, 11), LocalDate.of(2022, 7, 17))

        assertContentEquals(listOf(LocalDate.of(2022, 7, 11), LocalDate.of(2022, 7, 17)), calendar.toList())
    }
}