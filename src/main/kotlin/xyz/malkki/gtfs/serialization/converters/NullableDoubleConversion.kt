package xyz.malkki.gtfs.serialization.converters

import com.univocity.parsers.conversions.Conversion

internal class NullableDoubleConversion : Conversion<String?, Double?> {
    override fun execute(input: String?): Double? = input?.toDoubleOrNull()

    override fun revert(input: Double?): String? = input?.toString()
}