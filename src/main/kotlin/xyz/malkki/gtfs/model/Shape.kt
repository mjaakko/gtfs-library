package xyz.malkki.gtfs.model

import com.univocity.parsers.annotations.Parsed
import xyz.malkki.gtfs.NoArgConstructor

/**
 * See https://developers.google.com/transit/gtfs/reference#shapestxt
 */
@NoArgConstructor
data class Shape(
    @Parsed(field = ["shape_id"]) val shapeId: String,
    @Parsed(field = ["shape_pt_lat"]) val shapePtLat: Double,
    @Parsed(field = ["shape_pt_lon"]) val shapePtLon: Double,
    @Parsed(field = ["shape_pt_sequence"]) val shapePtSequence: Int,
    @Parsed(field = ["shape_dist_traveled"]) val shapeDistTraveled: Double?
) : Comparable<Shape> {
    override fun compareTo(other: Shape): Int {
        val byShapeId = shapeId.compareTo(other.shapeId)

        if (byShapeId == 0) {
            return shapePtSequence.compareTo(other.shapePtSequence)
        }

        return byShapeId
    }
}
