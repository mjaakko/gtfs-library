package xyz.malkki.gtfs.utils

import java.time.LocalDate
import java.time.ZoneId
import java.time.ZonedDateTime

object GtfsTimeUtils {
    /**
     * Calculates [ZonedDateTime] from GTFS service day and GTFS time. Implementation is based on the description of **Time** field described [here](https://developers.google.com/transit/gtfs/reference#field_types).
     *
     * Example:
     * ```
     * gtfsTimeToZonedDateTime(LocalDate.of(2022, 1, 1), parseFromString("12:00:00"), ZoneId.of("Asia/Tokyo")) 🡒 2022-01-01T12:00+09:00[Asia/Tokyo]
     * ```
     *
     * @param date Service day
     * @param seconds Seconds since midnight of the service day (e.g. arrival time or departure time from [xyz.malkki.gtfs.model.StopTime])
     * @param timezone Timezone that is used in the GTFS feed (e.g. [xyz.malkki.gtfs.model.Agency.agencyTimezone])
     *
     * @return [ZonedDateTime] corresponding to the given parameters
     */
    @JvmStatic
    fun gtfsTimeToZonedDateTime(date: LocalDate, seconds: Int, timezone: ZoneId): ZonedDateTime {
        //See https://developers.google.com/transit/gtfs/reference#field_types
        return date.atTime(12, 0).atZone(timezone).minusHours(12).plusSeconds(seconds.toLong())
    }
}