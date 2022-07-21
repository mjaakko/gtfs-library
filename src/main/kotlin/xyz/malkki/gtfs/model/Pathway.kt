package xyz.malkki.gtfs.model

import com.univocity.parsers.annotations.Convert
import com.univocity.parsers.annotations.Parsed
import xyz.malkki.gtfs.NoArgConstructor
import xyz.malkki.gtfs.serialization.converters.DurationConversion
import xyz.malkki.gtfs.serialization.converters.NullableIntegerConversion
import java.time.Duration

/**
 * See https://developers.google.com/transit/gtfs/reference#pathwaystxt
 */
@NoArgConstructor
data class Pathway(
    @Parsed(field = ["pathway_id"]) val pathwayId: String,
    @Parsed(field = ["from_stop_id"]) val fromStopId: String,
    @Parsed(field = ["to_stop_id"]) val toStopId: String,
    @Parsed(field = ["pathway_mode"]) val pathwayMode: Int,
    @Parsed(field = ["is_bidirectional"]) val isBidirectional: Int,
    @Parsed(field = ["length"]) val length: Double?,
    @Parsed(field = ["traversal_time"], applyDefaultConversion = false) @Convert(conversionClass = DurationConversion::class) val traversalTime: Duration?,
    @Parsed(field = ["stair_count"], applyDefaultConversion = false) @Convert(conversionClass = NullableIntegerConversion::class) val stairCount: Int?,
    @Parsed(field = ["max_slope"]) val maxSlope: Double?,
    @Parsed(field = ["min_width"]) val minWidth: Double?,
    @Parsed(field = ["signposted_as"]) val signpostedAs: String?,
    @Parsed(field = ["reversed_signposted_as"]) val reversedSignpostedAs: String?
)
