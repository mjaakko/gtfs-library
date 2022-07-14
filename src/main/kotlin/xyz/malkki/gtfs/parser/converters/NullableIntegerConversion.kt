package xyz.malkki.gtfs.parser.converters

import com.univocity.parsers.conversions.Conversion

class NullableIntegerConversion : Conversion<String?, Int?> {
    override fun execute(input: String?): Int? = input?.toIntOrNull()

    override fun revert(input: Int?): String? = input?.toString()
}