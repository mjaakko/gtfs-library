package xyz.malkki.gtfs.model

import com.univocity.parsers.annotations.Convert
import com.univocity.parsers.annotations.Parsed
import xyz.malkki.gtfs.NoArgConstructor
import xyz.malkki.gtfs.serialization.converters.BooleanConversion
import xyz.malkki.gtfs.serialization.converters.DurationConversion
import xyz.malkki.gtfs.serialization.converters.SecondsConversion
import java.time.Duration

/**
 * See https://developers.google.com/transit/gtfs/reference#frequenciestxt
 */
@NoArgConstructor
data class Frequency(
    @Parsed(field = ["trip_id"]) val tripId: String,
    @Parsed(field = ["start_time"], applyDefaultConversion = false) @Convert(conversionClass = SecondsConversion::class) val startTime: Int,
    @Parsed(field = ["end_time"], applyDefaultConversion = false) @Convert(conversionClass = SecondsConversion::class) val endTime: Int,
    @Parsed(field = ["headway_secs"], applyDefaultConversion = false) @Convert(conversionClass = DurationConversion::class) val headwaySecs: Duration,
    @Parsed(field = ["exact_times"], applyDefaultConversion = false) @Convert(conversionClass = BooleanConversion::class) val exactTimes: Boolean?
)
