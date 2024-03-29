package xyz.malkki.gtfs.model

import com.univocity.parsers.annotations.Convert
import com.univocity.parsers.annotations.Parsed
import xyz.malkki.gtfs.NoArgConstructor
import xyz.malkki.gtfs.serialization.converters.NullableIntegerConversion

/**
 * See [https://developers.google.com/transit/gtfs/reference#tripstxt](https://developers.google.com/transit/gtfs/reference#tripstxt)
 */
@NoArgConstructor
data class Trip(
    @Parsed(field = ["route_id"]) val routeId: String,
    @Parsed(field = ["service_id"]) val serviceId: String,
    @Parsed(field = ["trip_id"]) val tripId: String,
    @Parsed(field = ["trip_headsign"]) val tripHeadsign: String? = null,
    @Parsed(field = ["trip_short_name"]) val tripShortName: String? = null,
    @Parsed(field = ["direction_id"], applyDefaultConversion = false) @Convert(conversionClass = NullableIntegerConversion::class) val directionId: Int? = null,
    @Parsed(field = ["block_id"]) val blockId: String? = null,
    @Parsed(field = ["shape_id"]) val shapeId: String? = null,
    @Parsed(field = ["wheelchair_accessible"], applyDefaultConversion = false) @Convert(conversionClass = NullableIntegerConversion::class) val wheelchairAccessible: Int? = null,
    @Parsed(field = ["bikes_allowed"], applyDefaultConversion = false) @Convert(conversionClass = NullableIntegerConversion::class) val bikesAllowed: Int? = null
)
