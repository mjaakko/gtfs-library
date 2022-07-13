package xyz.malkki.gtfs.model

import com.univocity.parsers.annotations.Convert
import com.univocity.parsers.annotations.Parsed
import xyz.malkki.gtfs.NoArgConstructor
import xyz.malkki.gtfs.parser.converters.LocalDateConversion
import java.time.LocalDate

/**
 * See https://developers.google.com/transit/gtfs/reference#feed_infotxt
 */
@NoArgConstructor
data class FeedInfo(
    @Parsed(field = ["feed_publisher_name"]) val feedPublisherName: String,
    @Parsed(field = ["feed_publisher_url"]) val feedPublisherUrl: String,
    @Parsed(field = ["feed_lang"]) val feedLang: String,
    @Parsed(field = ["default_lang"]) val defaultLang: String?,
    @Parsed(field = ["feed_start_date"], applyDefaultConversion = false) @Convert(conversionClass = LocalDateConversion::class) val feedStartDate: LocalDate?,
    @Parsed(field = ["feed_end_date"], applyDefaultConversion = false) @Convert(conversionClass = LocalDateConversion::class) val feedEndDate: LocalDate?,
    @Parsed(field = ["feed_version"]) val feedVersion: String?,
    @Parsed(field = ["feed_contact_email"]) val feedContactEmail: String?,
    @Parsed(field = ["feed_contact_url"]) val feedContactUrl: String?
)
