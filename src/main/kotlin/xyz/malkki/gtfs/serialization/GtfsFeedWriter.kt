package xyz.malkki.gtfs.serialization

import com.univocity.parsers.common.processor.BeanWriterProcessor
import com.univocity.parsers.csv.CsvWriter
import com.univocity.parsers.csv.CsvWriterSettings
import xyz.malkki.gtfs.model.*
import java.io.BufferedOutputStream
import java.io.IOException
import java.io.OutputStream
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import java.util.zip.Deflater
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

/**
 * @param output Path to the GTFS file
 * @param compressionLevel Compression level to be used, see java.util.zip.Deflater for possible values
 */
class GtfsFeedWriter @JvmOverloads @Throws(IOException::class) constructor(output: Path, compressionLevel: Int = Deflater.BEST_COMPRESSION) : AutoCloseable {
    private val zipOutputStream = ZipOutputStream(BufferedOutputStream(Files.newOutputStream(output)), StandardCharsets.UTF_8).apply {
        setLevel(compressionLevel)
    }

    /**
     * Writes GTFS data to the GTFS file. No files are created for lists that are empty or null.
     */
    @Throws(IOException::class)
    fun writeGtfsFeed(
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
    ) {
        writeFile(agencies, GtfsConstants.AGENCY_FILE)
        writeFile(attributions, GtfsConstants.ATTRIBUTIONS_FILE)
        writeFile(calendars, GtfsConstants.CALENDAR_FILE)
        writeFile(calendarDates, GtfsConstants.CALENDAR_DATES_FILE)
        writeFile(fareAttributes, GtfsConstants.FARE_ATTRIBUTES_FILE)
        writeFile(fareRules, GtfsConstants.FARE_RULES_FILE)
        writeFile(feedInfos, GtfsConstants.FEED_INFO_FILE)
        writeFile(frequencies, GtfsConstants.FREQUENCIES_FILE)
        writeFile(levels, GtfsConstants.LEVELS_FILE)
        writeFile(pathways, GtfsConstants.PATHWAYS_FILE)
        writeFile(routes, GtfsConstants.ROUTES_FILE)
        writeFile(shapes, GtfsConstants.SHAPES_FILE)
        writeFile(stops, GtfsConstants.STOPS_FILE)
        writeFile(stopTimes, GtfsConstants.STOP_TIMES_FILE)
        writeFile(transfers, GtfsConstants.TRANSFERS_FILE)
        writeFile(translations, GtfsConstants.TRANSLATIONS_FILE)
        writeFile(trips, GtfsConstants.TRIPS_FILE)
    }

    private inline fun <reified T> writeFile(content: Collection<T>?, fileName: String) {
        if (content != null && content.isNotEmpty()) {
            val csvWriterSettings = CsvWriterSettings().apply {
                isHeaderWritingEnabled = true
                isAutoConfigurationEnabled = true
                nullValue = ""
                rowWriterProcessor = BeanWriterProcessor(T::class.java)
            }

            val csvWriter = CsvWriter(ZipEntryOutputStream(zipOutputStream, fileName), csvWriterSettings)
            csvWriter.processRecordsAndClose(content)
        }
    }

    override fun close() = zipOutputStream.close()

    private class ZipEntryOutputStream(private val zipOutputStream: ZipOutputStream, entryName: String) : OutputStream() {
        init {
            zipOutputStream.putNextEntry(ZipEntry(entryName))
        }

        override fun flush() = zipOutputStream.flush()

        override fun write(b: Int) = zipOutputStream.write(b)

        override fun write(b: ByteArray, off: Int, len: Int) = zipOutputStream.write(b, off, len)

        override fun write(b: ByteArray) = zipOutputStream.write(b)

        override fun close() {
            zipOutputStream.closeEntry()
        }
    }
}