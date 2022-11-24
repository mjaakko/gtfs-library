package xyz.malkki.gtfs.model

import com.univocity.parsers.annotations.Convert
import com.univocity.parsers.annotations.Parsed
import xyz.malkki.gtfs.NoArgConstructor
import xyz.malkki.gtfs.serialization.converters.NullableBooleanConversion
import xyz.malkki.gtfs.serialization.converters.NullableDoubleConversion
import xyz.malkki.gtfs.serialization.converters.NullableIntegerConversion
import xyz.malkki.gtfs.serialization.converters.SecondsConversion

/**
 * See [https://developers.google.com/transit/gtfs/reference#stop_timestxt](https://developers.google.com/transit/gtfs/reference#stop_timestxt)
 */
@NoArgConstructor
data class StopTime(
    @Parsed(field = ["trip_id", "\uFEFFtrip_id"]) val tripId: String,
    @Parsed(field = ["arrival_time"], applyDefaultConversion = false) @Convert(conversionClass = SecondsConversion::class) val arrivalTime: Int? = null,
    @Parsed(field = ["departure_time"], applyDefaultConversion = false) @Convert(conversionClass = SecondsConversion::class) val departureTime: Int? = null,
    @Parsed(field = ["stop_id"]) val stopId: String,
    @Parsed(field = ["stop_sequence"]) val stopSequence: Int,
    @Parsed(field = ["stop_headsign"]) val stopHeadsign: String? = null,
    @Parsed(field = ["pickup_type"], applyDefaultConversion = false) @Convert(conversionClass = NullableIntegerConversion::class) val pickupType: Int? = null,
    @Parsed(field = ["drop_off_type"], applyDefaultConversion = false) @Convert(conversionClass = NullableIntegerConversion::class) val dropOffType: Int? = null,
    @Parsed(field = ["continuous_pickup"], applyDefaultConversion = false) @Convert(conversionClass = NullableIntegerConversion::class) val continuousPickup: Int? = null,
    @Parsed(field = ["continuous_dropoff"], applyDefaultConversion = false) @Convert(conversionClass = NullableIntegerConversion::class) val continuousDropOff: Int? = null,
    @Parsed(field = ["shape_dist_traveled"], applyDefaultConversion = false) @Convert(conversionClass = NullableDoubleConversion::class) val shapeDistTraveled: Double? = null,
    @Parsed(field = ["timepoint"], applyDefaultConversion = false) @Convert(conversionClass = NullableBooleanConversion::class) val timepoint: Boolean? = null
) : Comparable<StopTime> {
    /**
     * Compares this stop time to the other.
     *
     * If [tripId] is not the same for both stop times, comparison is done by comparing [tripId] with [String.compareTo].
     * If the [tripId] is the same for both stop times, comparison is done by comparing [stopSequence] with [Int.compareTo]
     *
     * @return -1 if this stop time is before the other shape, 0 if both stop times are the same, 1 if this stop time is after the other
     */
    override fun compareTo(other: StopTime): Int {
        val byTripId = tripId.compareTo(other.tripId)

        if (byTripId == 0) {
            return stopSequence.compareTo(other.stopSequence)
        }

        return byTripId
    }

}
