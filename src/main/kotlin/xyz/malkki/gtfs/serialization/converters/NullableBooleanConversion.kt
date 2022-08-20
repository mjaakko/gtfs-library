package xyz.malkki.gtfs.serialization.converters

import com.univocity.parsers.conversions.Conversion

internal class NullableBooleanConversion : Conversion<String?, Boolean?> {
    override fun execute(input: String?): Boolean? = input?.let { it.trim() == "1" || it.isBlank() }

    override fun revert(input: Boolean?): String? = input?.let { if (it) { "1" } else { "0" } }
}