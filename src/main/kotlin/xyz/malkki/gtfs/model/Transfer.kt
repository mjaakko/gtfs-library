package xyz.malkki.gtfs.model

import com.univocity.parsers.annotations.Convert
import com.univocity.parsers.annotations.Parsed
import xyz.malkki.gtfs.NoArgConstructor
import xyz.malkki.gtfs.serialization.converters.DurationConversion
import java.time.Duration

/**
 * See https://developers.google.com/transit/gtfs/reference#transferstxt
 */
@NoArgConstructor
data class Transfer(
    @Parsed(field = ["from_stop_id"]) val fromStopId: String,
    @Parsed(field = ["to_stop_id"]) val toStopId: String,
    @Parsed(field = ["transfer_type"]) val transferType: Int,
    @Parsed(field = ["min_transfer_time"], applyDefaultConversion = false) @Convert(conversionClass = DurationConversion::class) val minTransferTime: Duration?
)