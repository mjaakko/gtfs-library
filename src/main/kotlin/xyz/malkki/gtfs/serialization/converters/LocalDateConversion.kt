package xyz.malkki.gtfs.serialization.converters

import com.univocity.parsers.conversions.Conversion
import xyz.malkki.gtfs.utils.GtfsDateFormat
import java.time.LocalDate

internal class LocalDateConversion : Conversion<String?, LocalDate?> {
    override fun execute(input: String?): LocalDate? = try {
        input?.let { GtfsDateFormat.parseFromString(it) }
    } catch (e: Exception) {
        null
    }

    override fun revert(input: LocalDate?): String? = input?.let { GtfsDateFormat.formatToString(it) }
}