package xyz.malkki.gtfs.model

import com.univocity.parsers.annotations.Convert
import com.univocity.parsers.annotations.Parsed
import xyz.malkki.gtfs.NoArgConstructor
import xyz.malkki.gtfs.serialization.converters.NullableIntegerConversion

/**
 * See https://developers.google.com/transit/gtfs/reference#routestxt
 */
@NoArgConstructor
data class Route(
    @Parsed(field = ["route_id"]) val routeId: String,
    @Parsed(field = ["agency_id"]) val agencyId: String?,
    @Parsed(field = ["route_short_name"]) val routeShortName: String?,
    @Parsed(field = ["route_long_name"]) val routeLongName: String?,
    @Parsed(field = ["route_desc"]) val routeDesc: String?,
    @Parsed(field = ["route_type"]) val routeType: Int,
    @Parsed(field = ["route_url"]) val routeUrl: String?,
    @Parsed(field = ["route_color"]) val routeColor: String?,
    @Parsed(field = ["route_text_color"]) val routeTextColor: String?,
    @Parsed(field = ["route_sort_order"], applyDefaultConversion = false) @Convert(conversionClass = NullableIntegerConversion::class) val routeSortOrder: Int?,
    @Parsed(field = ["continuous_pickup"], applyDefaultConversion = false) @Convert(conversionClass = NullableIntegerConversion::class) val continuousPickup: Int?,
    @Parsed(field = ["continuous_drop_off"], applyDefaultConversion = false) @Convert(conversionClass = NullableIntegerConversion::class) val continuousDropOff: Int?
) {
    companion object {
        const val ROUTE_TYPE_TRAM = 0
        const val ROUTE_TYPE_SUBWAY = 1
        const val ROUTE_TYPE_RAIL = 2
        const val ROUTE_TYPE_BUS = 3
        const val ROUTE_TYPE_FERRY = 4
        const val ROUTE_TYPE_CABLE_TRAM = 5
        const val ROUTE_TYPE_AERIAL_LIFT = 6
        const val ROUTE_TYPE_FUNICULAR = 7
        const val ROUTE_TYPE_TROLLEYBUS = 11
        const val ROUTE_TYPE_MONORAIL = 12
    }
}
