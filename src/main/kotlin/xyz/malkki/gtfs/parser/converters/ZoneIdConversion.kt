package xyz.malkki.gtfs.parser.converters

import com.univocity.parsers.conversions.Conversion
import java.time.ZoneId

internal class ZoneIdConversion : Conversion<String?, ZoneId?> {
    override fun execute(input: String?): ZoneId? = input?.let { ZoneId.of(it) }

    override fun revert(input: ZoneId?): String? = input?.id
}