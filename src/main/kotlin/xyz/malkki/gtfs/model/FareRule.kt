package xyz.malkki.gtfs.model

import com.univocity.parsers.annotations.Parsed
import xyz.malkki.gtfs.NoArgConstructor

/**
 * See [https://developers.google.com/transit/gtfs/reference#fare_rulestxt](https://developers.google.com/transit/gtfs/reference#fare_rulestxt)
 */
@NoArgConstructor
data class FareRule(
    @Parsed(field = ["fare_id"]) val fareId: String,
    @Parsed(field = ["route_id"]) val routeId: String? = null,
    @Parsed(field = ["origin_id"]) val originId: String? = null,
    @Parsed(field = ["destination_id"]) val destinationId: String? = null,
    @Parsed(field = ["contains_id"]) val containsId: String? = null,
)
