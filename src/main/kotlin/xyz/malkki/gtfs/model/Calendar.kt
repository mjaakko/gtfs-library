package xyz.malkki.gtfs.model

import com.univocity.parsers.annotations.Convert
import com.univocity.parsers.annotations.Parsed
import xyz.malkki.gtfs.NoArgConstructor
import xyz.malkki.gtfs.serialization.converters.BooleanConversion
import xyz.malkki.gtfs.serialization.converters.LocalDateConversion
import java.time.DayOfWeek
import java.time.LocalDate

/**
 * See https://developers.google.com/transit/gtfs/reference#calendartxt
 */
@NoArgConstructor
data class Calendar(
    @Parsed(field = ["service_id"]) val serviceId: String,
    @Parsed(field = ["monday"], applyDefaultConversion = false) @Convert(conversionClass = BooleanConversion::class) val monday: Boolean,
    @Parsed(field = ["tuesday"], applyDefaultConversion = false) @Convert(conversionClass = BooleanConversion::class) val tuesday: Boolean,
    @Parsed(field = ["wednesday"], applyDefaultConversion = false) @Convert(conversionClass = BooleanConversion::class) val wednesday: Boolean,
    @Parsed(field = ["thursday"], applyDefaultConversion = false) @Convert(conversionClass = BooleanConversion::class) val thursday: Boolean,
    @Parsed(field = ["friday"], applyDefaultConversion = false) @Convert(conversionClass = BooleanConversion::class) val friday: Boolean,
    @Parsed(field = ["saturday"], applyDefaultConversion = false) @Convert(conversionClass = BooleanConversion::class) val saturday: Boolean,
    @Parsed(field = ["sunday"], applyDefaultConversion = false) @Convert(conversionClass = BooleanConversion::class) val sunday: Boolean,
    @Parsed(field = ["start_date"], applyDefaultConversion = false) @Convert(conversionClass = LocalDateConversion::class) val startDate: LocalDate,
    @Parsed(field = ["end_date"], applyDefaultConversion = false) @Convert(conversionClass = LocalDateConversion::class) val endDate: LocalDate
) : Iterable<LocalDate> {
    val daysOfWeek by lazy {
        setOfNotNull(
            if (monday) { DayOfWeek.MONDAY } else { null },
            if (tuesday) { DayOfWeek.TUESDAY } else { null },
            if (wednesday) { DayOfWeek.WEDNESDAY } else { null },
            if (thursday) { DayOfWeek.THURSDAY } else { null },
            if (friday) { DayOfWeek.FRIDAY } else { null },
            if (saturday) { DayOfWeek.SATURDAY } else { null },
            if (sunday) { DayOfWeek.SUNDAY } else { null }
        )
    }

    override fun iterator(): Iterator<LocalDate> = object : Iterator<LocalDate> {
        private var next: LocalDate? = findNext(startDate)

        private fun findNext(startDate: LocalDate): LocalDate? {
            var current = startDate
            while (current <= endDate) {
                if (current.dayOfWeek in daysOfWeek) {
                    return current
                } else {
                    current = current.plusDays(1)
                }
            }

            return null
        }

        override fun hasNext(): Boolean = next != null

        override fun next(): LocalDate {
            val output = next!!
            next = findNext(output.plusDays(1))
            return output
        }
    }
}
