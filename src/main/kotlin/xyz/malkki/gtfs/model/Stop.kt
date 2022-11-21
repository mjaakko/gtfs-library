package xyz.malkki.gtfs.model

import com.univocity.parsers.annotations.Convert
import com.univocity.parsers.annotations.Parsed
import xyz.malkki.gtfs.NoArgConstructor
import xyz.malkki.gtfs.serialization.converters.NullableIntegerConversion
import xyz.malkki.gtfs.serialization.converters.ZoneIdConversion
import java.time.ZoneId

/**
 * See [https://developers.google.com/transit/gtfs/reference#stopstxt](https://developers.google.com/transit/gtfs/reference#stopstxt)
 */
@NoArgConstructor
data class Stop(
    @Parsed(field = ["stop_id"]) val stopId: String,
    @Parsed(field = ["stop_code"]) val stopCode: String?,
    @Parsed(field = ["stop_name"]) val stopName: String?,
    @Parsed(field = ["stop_desc"]) val stopDesc: String?,
    @Parsed(field = ["stop_lat"]) val stopLat: Double?,
    @Parsed(field = ["stop_lon"]) val stopLon: Double?,
    @Parsed(field = ["zone_id"]) val zoneId: String?,
    @Parsed(field = ["stop_url"]) val stopUrl: String?,
    @Parsed(field = ["location_type"], applyDefaultConversion = false) @Convert(conversionClass = NullableIntegerConversion::class) val locationType: Int?,
    @Parsed(field = ["parent_station"]) val parentStation: String?,
    @Parsed(field = ["stop_timezone"], applyDefaultConversion = false) @Convert(conversionClass = ZoneIdConversion::class) val stopTimezone: ZoneId?,
    @Parsed(field = ["wheelchair_boarding"], applyDefaultConversion = false) @Convert(conversionClass = NullableIntegerConversion::class) val wheelchairBoarding: Int?,
    @Parsed(field = ["level_id"]) val levelId: String?,
    @Parsed(field = ["platform_code"]) val platformCode: String?
) {
    companion object {
        const val LOCATION_TYPE_STOP = 0
        const val LOCATION_TYPE_STATION = 1
        const val LOCATION_TYPE_ENTRANCE_EXIT = 2
        const val LOCATION_TYPE_GENERIC_NODE = 3
        const val LOCATION_TYPE_BOARDING_AREA = 4
    }
}
