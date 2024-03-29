package xyz.malkki.gtfs.model

import com.univocity.parsers.annotations.Convert
import com.univocity.parsers.annotations.Parsed
import xyz.malkki.gtfs.NoArgConstructor
import xyz.malkki.gtfs.serialization.converters.NullableBooleanConversion

/**
 * See [https://developers.google.com/transit/gtfs/reference#attributionstxt](https://developers.google.com/transit/gtfs/reference#attributionstxt)
 */
@NoArgConstructor
data class Attribution(
    @Parsed(field = ["attribution_id"]) val attributionId: String? = null,
    @Parsed(field = ["agency_id"]) val agencyId: String? = null,
    @Parsed(field = ["route_id"]) val routeId: String? = null,
    @Parsed(field = ["trip_id"]) val tripId: String? = null,
    @Parsed(field = ["organization_name"]) val organizationName: String,
    @Parsed(field = ["is_producer"], applyDefaultConversion = false) @Convert(conversionClass = NullableBooleanConversion::class) val isProducer: Boolean? = null,
    @Parsed(field = ["is_operator"], applyDefaultConversion = false) @Convert(conversionClass = NullableBooleanConversion::class) val isOperator: Boolean? = null,
    @Parsed(field = ["is_authority"], applyDefaultConversion = false) @Convert(conversionClass = NullableBooleanConversion::class) val isAuthority: Boolean? = null,
    @Parsed(field = ["attribution_url"]) val attributionUrl: String? = null,
    @Parsed(field = ["attribution_email"]) val attributionEmail: String? = null,
    @Parsed(field = ["attribution_phone"]) val attributionPhone: String? = null
)
