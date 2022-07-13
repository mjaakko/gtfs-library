package xyz.malkki.gtfs.model

import com.univocity.parsers.annotations.Convert
import com.univocity.parsers.annotations.Parsed
import xyz.malkki.gtfs.NoArgConstructor
import xyz.malkki.gtfs.parser.converters.BooleanConversion
import xyz.malkki.gtfs.parser.converters.SecondsConversion

/**
 * See https://developers.google.com/transit/gtfs/reference#stop_timestxt
 */
@NoArgConstructor
data class StopTime(
    @Parsed(field = ["trip_id", "\uFEFFtrip_id"]) val tripId: String,
    @Parsed(field = ["arrival_time"], applyDefaultConversion = false) @Convert(conversionClass = SecondsConversion::class) val arrivalTime: Int?,
    @Parsed(field = ["departure_time"], applyDefaultConversion = false) @Convert(conversionClass = SecondsConversion::class) val departureTime: Int?,
    @Parsed(field = ["stop_id"]) val stopId: String,
    @Parsed(field = ["stop_sequence"]) val stopSequence: Int,
    @Parsed(field = ["stop_headsign"]) val stopHeadsign: String?,
    @Parsed(field = ["pickup_type"]) val pickupType: Int?,
    @Parsed(field = ["drop_off_type"]) val dropOffType: Int?,
    @Parsed(field = ["continuous_pickup"]) val continuousPickup: Int?,
    @Parsed(field = ["continuous_dropoff"]) val continuousDropOff: Int?,
    @Parsed(field = ["shape_dist_traveled"]) val shapeDistTraveled: Double?,
    @Parsed(field = ["timepoint"], applyDefaultConversion = false) @Convert(conversionClass = BooleanConversion::class) val timepoint: Boolean?
) : Comparable<StopTime> {
    override fun compareTo(other: StopTime): Int {
        val byTripId = tripId.compareTo(other.tripId)

        if (byTripId == 0) {
            return stopSequence.compareTo(other.stopSequence)
        }

        return byTripId
    }

}
