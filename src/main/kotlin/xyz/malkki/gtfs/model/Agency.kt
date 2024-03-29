package xyz.malkki.gtfs.model

import com.univocity.parsers.annotations.Convert
import com.univocity.parsers.annotations.Parsed
import xyz.malkki.gtfs.NoArgConstructor
import xyz.malkki.gtfs.serialization.converters.ZoneIdConversion
import java.time.ZoneId
import java.util.*

/**
 * See [https://developers.google.com/transit/gtfs/reference#agencytxt](https://developers.google.com/transit/gtfs/reference#agencytxt)
 *
 * @property agencyLangAsLocale [agencyLang] as Java Locale. null if agencyLang is null or contains an unsupported language tag
 */
@NoArgConstructor
data class Agency(
    @Parsed(field = ["agency_id"]) val agencyId: String? = null,
    @Parsed(field = ["agency_name"]) val agencyName: String,
    @Parsed(field = ["agency_url"]) val agencyUrl: String,
    @Parsed(field = ["agency_timezone"], applyDefaultConversion = false) @Convert(conversionClass = ZoneIdConversion::class) val agencyTimezone: ZoneId,
    @Parsed(field = ["agency_lang"]) val agencyLang: String? = null,
    @Parsed(field = ["agency_phone"]) val agencyPhone: String? = null,
    @Parsed(field = ["agency_fare_url"]) val agencyFareUrl: String? = null,
    @Parsed(field = ["agency_email"]) val agencyEmail: String? = null
) {
    val agencyLangAsLocale by lazy { agencyLang?.let { Locale.forLanguageTag(it) } }
}