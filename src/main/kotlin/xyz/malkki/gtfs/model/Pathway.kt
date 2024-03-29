package xyz.malkki.gtfs.model

import com.univocity.parsers.annotations.Convert
import com.univocity.parsers.annotations.Parsed
import xyz.malkki.gtfs.NoArgConstructor
import xyz.malkki.gtfs.serialization.converters.DurationConversion
import xyz.malkki.gtfs.serialization.converters.NullableBooleanConversion
import xyz.malkki.gtfs.serialization.converters.NullableDoubleConversion
import xyz.malkki.gtfs.serialization.converters.NullableIntegerConversion
import java.time.Duration

/**
 * See [https://developers.google.com/transit/gtfs/reference#pathwaystxt](https://developers.google.com/transit/gtfs/reference#pathwaystxt)
 */
@NoArgConstructor
data class Pathway(
    @Parsed(field = ["pathway_id"]) val pathwayId: String,
    @Parsed(field = ["from_stop_id"]) val fromStopId: String,
    @Parsed(field = ["to_stop_id"]) val toStopId: String,
    @Parsed(field = ["pathway_mode"]) val pathwayMode: Int,
    @Parsed(field = ["is_bidirectional"], applyDefaultConversion = false) @Convert(conversionClass = NullableBooleanConversion::class) val isBidirectional: Boolean,
    @Parsed(field = ["length"], applyDefaultConversion = false) @Convert(conversionClass = NullableDoubleConversion::class) val length: Double? = null,
    @Parsed(field = ["traversal_time"], applyDefaultConversion = false) @Convert(conversionClass = DurationConversion::class) val traversalTime: Duration? = null,
    @Parsed(field = ["stair_count"], applyDefaultConversion = false) @Convert(conversionClass = NullableIntegerConversion::class) val stairCount: Int? = null,
    @Parsed(field = ["max_slope"], applyDefaultConversion = false) @Convert(conversionClass = NullableDoubleConversion::class) val maxSlope: Double? = null,
    @Parsed(field = ["max_width"], applyDefaultConversion = false) @Convert(conversionClass = NullableDoubleConversion::class) val minWidth: Double? = null,
    @Parsed(field = ["signposted_as"]) val signpostedAs: String? = null,
    @Parsed(field = ["reversed_signposted_as"]) val reversedSignpostedAs: String? = null
) {
    companion object {
        const val PATHWAY_MODE_WALKWAY = 1
        const val PATHWAY_MODE_STAIRS = 2
        const val PATHWAY_MOVING_SIDEWALK = 3
        const val PATHWAY_ESCALATOR = 4
        const val PATHWAY_ELEVATOR = 4
        const val PATHWAY_FARE_GATE = 6
        const val PATHWAY_EXIT_GATE = 7
    }
}
