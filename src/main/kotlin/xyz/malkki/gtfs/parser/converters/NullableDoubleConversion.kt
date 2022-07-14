package xyz.malkki.gtfs.parser.converters

import com.univocity.parsers.conversions.Conversion

class NullableDoubleConversion : Conversion<String?, Double?> {
    override fun execute(input: String?): Double? = input?.toDoubleOrNull()

    override fun revert(input: Double?): String? = input?.toString()
}