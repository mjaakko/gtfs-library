package xyz.malkki.gtfs.model

import com.univocity.parsers.annotations.Convert
import com.univocity.parsers.annotations.Parsed
import xyz.malkki.gtfs.NoArgConstructor
import xyz.malkki.gtfs.serialization.converters.LocalDateConversion
import java.time.LocalDate
import java.util.*

/**
 * See [https://developers.google.com/transit/gtfs/reference#feed_infotxt](https://developers.google.com/transit/gtfs/reference#feed_infotxt)
 *
 * @property feedLangAsLocale [feedLang] as Java Locale. null if feedLang is null or contains an unsupported language tag
 * @property defaultLangAsLocale [defaultLang] as Java Locale. null if defaultLang is null or contains an unsupported language tag
 */
@NoArgConstructor
data class FeedInfo(
    @Parsed(field = ["feed_publisher_name"]) val feedPublisherName: String,
    @Parsed(field = ["feed_publisher_url"]) val feedPublisherUrl: String,
    @Parsed(field = ["feed_lang"]) val feedLang: String,
    @Parsed(field = ["default_lang"]) val defaultLang: String? = null,
    @Parsed(field = ["feed_start_date"], applyDefaultConversion = false) @Convert(conversionClass = LocalDateConversion::class) val feedStartDate: LocalDate? = null,
    @Parsed(field = ["feed_end_date"], applyDefaultConversion = false) @Convert(conversionClass = LocalDateConversion::class) val feedEndDate: LocalDate? = null,
    @Parsed(field = ["feed_version"]) val feedVersion: String? = null,
    @Parsed(field = ["feed_contact_email"]) val feedContactEmail: String? = null,
    @Parsed(field = ["feed_contact_url"]) val feedContactUrl: String? = null
) {
    val feedLangAsLocale by lazy { Locale.forLanguageTag(feedLang)!! }
    val defaultLangAsLocale by lazy { defaultLang?.let { Locale.forLanguageTag(defaultLang) } }
}
