package xyz.malkki.gtfs.serialization.writer

import com.univocity.parsers.common.processor.BeanWriterProcessor
import com.univocity.parsers.csv.CsvWriter
import com.univocity.parsers.csv.CsvWriterSettings
import xyz.malkki.gtfs.model.*
import java.io.IOException
import java.io.OutputStream

abstract class GtfsFeedWriter : AutoCloseable {
    protected inline fun <reified T> writeToStream(content: Collection<T>?, outputStream: OutputStream) {
        if (content != null && content.isNotEmpty()) {
            val csvWriterSettings = CsvWriterSettings().apply {
                isHeaderWritingEnabled = true
                isAutoConfigurationEnabled = true
                nullValue = ""
                rowWriterProcessor = BeanWriterProcessor(T::class.java)
            }

            val csvWriter = CsvWriter(outputStream, csvWriterSettings)
            csvWriter.processRecordsAndClose(content)
        }
    }

    @Throws(IOException::class)
    abstract fun writeGtfsFeed(
        agencies: Collection<Agency>?,
        attributions: Collection<Attribution>?,
        calendars: Collection<Calendar>?,
        calendarDates: Collection<CalendarDate>?,
        fareAttributes: Collection<FareAttribute>?,
        fareRules: Collection<FareRule>?,
        feedInfos: Collection<FeedInfo>?,
        frequencies: Collection<Frequency>?,
        levels: Collection<Level>?,
        pathways: Collection<Pathway>?,
        routes: Collection<Route>?,
        shapes: Collection<Shape>?,
        stops: Collection<Stop>?,
        stopTimes: Collection<StopTime>?,
        transfers: Collection<Transfer>?,
        translations: Collection<Translation>?,
        trips: Collection<Trip>?
    )
}