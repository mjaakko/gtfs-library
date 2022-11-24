package xyz.malkki.gtfs.model

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import kotlin.test.Test

class ShapeTest {
    @Test
    fun `Test comparing shapes with same shape ID`() {
        val shape1 = Shape(shapeId = "1", shapePtLat = 0.0, shapePtLon = 0.0, shapePtSequence = 1)
        val shape2 = Shape(shapeId = "1", shapePtLat = 0.0, shapePtLon = 0.0, shapePtSequence = 2)

        assertThat(shape1, Matchers.lessThan(shape2))
    }

    @Test
    fun `Test comparing shapes with different shape ID`() {
        val shape1 = Shape(shapeId = "a", shapePtLat = 0.0, shapePtLon = 0.0, shapePtSequence = 1)
        val shape2 = Shape(shapeId = "b", shapePtLat = 0.0, shapePtLon = 0.0, shapePtSequence = 1)

        assertThat(shape1, Matchers.lessThan(shape2))
    }
}