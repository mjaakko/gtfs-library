package xyz.malkki.gtfs.serialization.writer

import xyz.malkki.gtfs.model.*
import xyz.malkki.gtfs.serialization.GtfsConstants
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
 * Writes the GTFS feed to a ZIP archive
 *
 * @param outputStream Output stream where the GTFS zip will be written
 * @param compressionLevel Compression level to be used, see [java.util.zip.Deflater] for possible values
 */
class ZipGtfsFeedWriter @JvmOverloads @Throws(IOException::class) constructor(outputStream: OutputStream, compressionLevel: Int = Deflater.BEST_COMPRESSION) : GtfsFeedWriter() {
    /**
     * @param outputFile Path to the GTFS file
     * @param compressionLevel Compression level to be used, see [java.util.zip.Deflater] for possible values
     */
    @JvmOverloads
    @Throws(IOException::class)
    constructor(outputFile: Path, compressionLevel: Int = Deflater.BEST_COMPRESSION) : this(BufferedOutputStream(Files.newOutputStream(outputFile)), compressionLevel)

    private val zipOutputStream = ZipOutputStream(outputStream, StandardCharsets.UTF_8).apply {
        setLevel(compressionLevel)
    }

    private inline fun <reified T> writeFile(content: Collection<T>?, fileName: String) {
        writeToStream(content, ZipEntryOutputStream(zipOutputStream, fileName))
    }

    override fun writeGtfsFeed(
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

    /**
     * Closes the underlying output stream
     */
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