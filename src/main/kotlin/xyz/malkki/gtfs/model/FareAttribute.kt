package xyz.malkki.gtfs.model

import com.univocity.parsers.annotations.Convert
import com.univocity.parsers.annotations.Parsed
import xyz.malkki.gtfs.NoArgConstructor
import xyz.malkki.gtfs.serialization.converters.DurationConversion
import java.time.Duration
import java.util.*

/**
 * See https://developers.google.com/transit/gtfs/reference#fare_attributestxt
 *
 * @property currencyTypeAsCurrency Currency type as Java Currency. null if currency type is null or unsupported
 */
@NoArgConstructor
data class FareAttribute(
    @Parsed(field = ["fare_id"]) val fareId: String,
    @Parsed(field = ["price"]) val price: Double,
    @Parsed(field = ["currency_type"]) val currencyType: String,
    @Parsed(field = ["payment_method"]) val paymentMethod: Int,
    @Parsed(field = ["transfers"]) val transfers: Int,
    @Parsed(field = ["agency_id"]) val agencyId: String?,
    @Parsed(field = ["transfer_duration"], applyDefaultConversion = false) @Convert(conversionClass = DurationConversion::class) val transferDuration: Duration?
) {
    val currencyTypeAsCurrency: Currency? = try {
        Currency.getInstance(currencyType)
    } catch (ex: Exception) {
        null
    }
}
