package xyz.malkki.gtfs.parser.converters

import com.univocity.parsers.conversions.Conversion
import xyz.malkki.gtfs.utils.GtfsTimeUtils

internal class SecondsConversion : Conversion<String, Int> {
    override fun execute(input: String): Int = GtfsTimeUtils.parseFromString(input)

    override fun revert(input: Int): String = GtfsTimeUtils.formatToString(input)
}