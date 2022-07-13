package xyz.malkki.gtfs.parser.converters

import com.univocity.parsers.conversions.Conversion
import xyz.malkki.gtfs.utils.GtfsDateUtils
import java.time.LocalDate

internal class LocalDateConversion : Conversion<String?, LocalDate?> {
    override fun execute(input: String?): LocalDate? = try {
        input?.let { GtfsDateUtils.parseFromString(it) }
    } catch (e: Exception) {
        null
    }

    override fun revert(input: LocalDate?): String? = input?.let { GtfsDateUtils.formatToString(it) }
}