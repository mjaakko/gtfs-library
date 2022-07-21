package xyz.malkki.gtfs.model

import com.univocity.parsers.annotations.Convert
import com.univocity.parsers.annotations.Parsed
import xyz.malkki.gtfs.NoArgConstructor
import xyz.malkki.gtfs.serialization.converters.BooleanConversion

/**
 * See https://developers.google.com/transit/gtfs/reference#attributionstxt
 */
@NoArgConstructor
data class Attribution(
    @Parsed(field = ["attribution_id"]) val attributionId: String?,
    @Parsed(field = ["agency_id"]) val agencyId: String?,
    @Parsed(field = ["route_id"]) val routeId: String?,
    @Parsed(field = ["trip_id"]) val tripId: String?,
    @Parsed(field = ["organization_name"]) val organizationName: String,
    @Parsed(field = ["is_producer"], applyDefaultConversion = false) @Convert(conversionClass = BooleanConversion::class) val isProducer: Boolean?,
    @Parsed(field = ["is_operator"], applyDefaultConversion = false) @Convert(conversionClass = BooleanConversion::class) val isOperator: Boolean?,
    @Parsed(field = ["is_authority"], applyDefaultConversion = false) @Convert(conversionClass = BooleanConversion::class) val isAuthority: Boolean?,
    @Parsed(field = ["attribution_url"]) val attributionUrl: String?,
    @Parsed(field = ["attribution_email"]) val attributionEmail: String?,
    @Parsed(field = ["attribution_phone"]) val attributionPhone: String?
)
