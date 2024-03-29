package xyz.malkki.gtfs.model

import com.univocity.parsers.annotations.Parsed
import xyz.malkki.gtfs.NoArgConstructor
import java.util.*

/**
 * See [https://developers.google.com/transit/gtfs/reference#translationstxt](https://developers.google.com/transit/gtfs/reference#translationstxt)
 *
 * @property languageAsLocale [language] as Java Locale
 */
@NoArgConstructor
data class Translation(
    @Parsed(field = ["table_name"]) val tableName: String,
    @Parsed(field = ["field_name"]) val fieldName: String,
    @Parsed(field = ["language"]) val language: String,
    @Parsed(field = ["translation"]) val translation: String,
    @Parsed(field = ["record_id"]) val recordId: String? = null,
    @Parsed(field = ["record_sub_id"]) val recordSubId: String? = null,
    @Parsed(field = ["field_value"]) val fieldValue: String? = null
) {
    val languageAsLocale by lazy { Locale.forLanguageTag(language)!! }
}
