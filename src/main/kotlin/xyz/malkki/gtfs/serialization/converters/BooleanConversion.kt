package xyz.malkki.gtfs.serialization.converters

import com.univocity.parsers.conversions.Conversion

internal class BooleanConversion : Conversion<String, Boolean> {
    override fun execute(input: String): Boolean = input.trim() == "1" || input.isBlank()

    override fun revert(input: Boolean): String = if (input) { "1" } else { "0" }
}