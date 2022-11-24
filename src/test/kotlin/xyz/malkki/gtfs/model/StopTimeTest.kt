package xyz.malkki.gtfs.model

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import kotlin.test.Test

class StopTimeTest {
    @Test
    fun `Test comparing stop times of same trip`() {
        val stopTime1 = StopTime(tripId = "a", stopId = "1", stopSequence = 1)
        val stopTime2 = StopTime(tripId = "a", stopId = "2", stopSequence = 2)

        assertThat(stopTime1, Matchers.lessThan(stopTime2))
    }

    @Test
    fun `Test comparing stop times of different trip`() {
        val stopTime1 = StopTime(tripId = "a", stopId = "1", stopSequence = 1)
        val stopTime2 = StopTime(tripId = "b", stopId = "1", stopSequence = 1)

        assertThat(stopTime1, Matchers.lessThan(stopTime2))
    }
}