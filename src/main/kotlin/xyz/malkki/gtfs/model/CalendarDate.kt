package xyz.malkki.gtfs.model

import com.univocity.parsers.annotations.Convert
import com.univocity.parsers.annotations.Parsed
import xyz.malkki.gtfs.NoArgConstructor
import xyz.malkki.gtfs.serialization.converters.LocalDateConversion
import java.time.LocalDate

/**
 * See https://developers.google.com/transit/gtfs/reference#calendar_datestxt
 */
@NoArgConstructor
data class CalendarDate(
    @Parsed(field = ["service_id"]) val serviceId: String,
    @Parsed(field = ["date"], applyDefaultConversion = false) @Convert(conversionClass = LocalDateConversion::class) val date: LocalDate,
    @Parsed(field = ["exception_type"]) val exceptionType: Int
) {
    companion object {
        const val EXCEPTION_TYPE_ADDED = 1
        const val EXCEPTION_TYPE_REMOVED = 2
    }
}