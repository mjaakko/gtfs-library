package xyz.malkki.gtfs.utils

import java.time.LocalDate
import java.time.ZoneId
import java.time.ZonedDateTime

object GtfsTimeUtils {
    /**
     * Calculates real date-time from GTFS service day and GTFS time
     *
     * @param date Service day
     * @param seconds Seconds since midnight (arrival time or departure time from StopTime)
     * @param timezone Timezone that is used in the GTFS feed
     */
    @JvmStatic
    fun gtfsTimeToZonedDateTime(date: LocalDate, seconds: Int, timezone: ZoneId): ZonedDateTime {
        //See https://developers.google.com/transit/gtfs/reference#field_types
        return date.atTime(12, 0).atZone(timezone).minusHours(12).plusSeconds(seconds.toLong())
    }
}