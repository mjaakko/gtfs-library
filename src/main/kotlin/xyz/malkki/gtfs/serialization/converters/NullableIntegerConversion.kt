package xyz.malkki.gtfs.serialization.converters

import com.univocity.parsers.conversions.Conversion

internal class NullableIntegerConversion : Conversion<String?, Int?> {
    override fun execute(input: String?): Int? = input?.toIntOrNull()

    override fun revert(input: Int?): String? = input?.toString()
}