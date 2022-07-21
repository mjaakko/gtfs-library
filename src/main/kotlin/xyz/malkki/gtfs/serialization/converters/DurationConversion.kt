package xyz.malkki.gtfs.serialization.converters

import com.univocity.parsers.conversions.Conversion
import java.time.Duration

internal class DurationConversion : Conversion<String?, Duration?> {
    override fun execute(input: String?): Duration? = input?.toLongOrNull()?.let { Duration.ofSeconds(it) }

    override fun revert(input: Duration?): String? = input?.toSeconds()?.toString()
}