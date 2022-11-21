package xyz.malkki.gtfs.model

import com.univocity.parsers.annotations.Convert
import com.univocity.parsers.annotations.Parsed
import xyz.malkki.gtfs.NoArgConstructor
import xyz.malkki.gtfs.serialization.converters.DurationConversion
import xyz.malkki.gtfs.serialization.converters.NullableIntegerConversion
import java.time.Duration
import java.util.*

/**
 * See [https://developers.google.com/transit/gtfs/reference#fare_attributestxt](https://developers.google.com/transit/gtfs/reference#fare_attributestxt)
 *
 * @property currencyTypeAsCurrency Currency type as Java Currency. null if currency type is null or unsupported
 */
@NoArgConstructor
data class FareAttribute(
    @Parsed(field = ["fare_id"]) val fareId: String,
    @Parsed(field = ["price"]) val price: Double,
    @Parsed(field = ["currency_type"]) val currencyType: String,
    @Parsed(field = ["payment_method"]) val paymentMethod: Int,
    @Parsed(field = ["transfers"], applyDefaultConversion = false) @Convert(conversionClass = NullableIntegerConversion::class) val transfers: Int?,
    @Parsed(field = ["agency_id"]) val agencyId: String?,
    @Parsed(field = ["transfer_duration"], applyDefaultConversion = false) @Convert(conversionClass = DurationConversion::class) val transferDuration: Duration?
) {
    val currencyTypeAsCurrency: Currency? by lazy {
        try {
            Currency.getInstance(currencyType)
        } catch (ex: Exception) {
            null
        }
    }

    companion object {
        const val PAYMENT_METHOD_ON_BOARD = 0
        const val PAYMENT_METHOD_BEFORE_BOARDING = 1

        const val TRANSFERS_NONE = 0
        const val TRANSFERS_ONCE = 1
        const val TRANSFERS_TWICE = 2
    }
}
