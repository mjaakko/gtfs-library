package xyz.malkki.gtfs.serialization.converters

import com.univocity.parsers.conversions.Conversion
import xyz.malkki.gtfs.utils.GtfsTimeFormat

internal class SecondsConversion : Conversion<String, Int> {
    override fun execute(input: String): Int = GtfsTimeFormat.parseFromString(input)

    override fun revert(input: Int): String = GtfsTimeFormat.formatToString(input)
}