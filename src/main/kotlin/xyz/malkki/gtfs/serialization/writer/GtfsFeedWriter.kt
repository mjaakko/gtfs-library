package xyz.malkki.gtfs.serialization.writer

import com.univocity.parsers.common.processor.BeanWriterProcessor
import com.univocity.parsers.csv.CsvWriter
import com.univocity.parsers.csv.CsvWriterSettings
import xyz.malkki.gtfs.asIterable
import xyz.malkki.gtfs.model.*
import xyz.malkki.gtfs.serialization.GtfsConstants
import java.io.IOException
import java.io.OutputStream
import java.util.stream.Stream

abstract class GtfsFeedWriter : AutoCloseable {
    private inline fun <reified T> writeToStream(content: Iterable<T>?, outputStream: OutputStream) {
        if (content != null) {
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

    protected abstract fun getOutputStreamForFile(fileName: String): OutputStream

    /**
     * Writes agencies to the GTFS feed. If the file already exists, it will be overwritten
     *
     * @param agencies Agencies
     * @throws IOException
     */
    @Throws(IOException::class)
    fun writeAgencies(agencies: Iterable<Agency>) = writeToStream(agencies, getOutputStreamForFile(GtfsConstants.AGENCY_FILE))

    /**
     * Writes agencies to the GTFS feed. If the file already exists, it will be overwritten
     *
     * @param agencies Agencies
     * @throws IOException
     */
    @Throws(IOException::class)
    fun writeAgencies(agencies: Stream<Agency>) = writeAgencies(agencies.asIterable())

    /**
     * Writes attributions to the GTFS feed. If the file already exists, it will be overwritten
     *
     * @param attributions Attributions
     * @throws IOException
     */
    @Throws(IOException::class)
    fun writeAttributions(attributions: Iterable<Attribution>) = writeToStream(attributions, getOutputStreamForFile(GtfsConstants.ATTRIBUTIONS_FILE))

    /**
     * Writes attributions to the GTFS feed. If the file already exists, it will be overwritten
     *
     * @param attributions Attributions
     * @throws IOException
     */
    @Throws(IOException::class)
    fun writeAttributions(attributions: Stream<Attribution>) = writeAttributions(attributions.asIterable())

    /**
     * Writes calendars to the GTFS feed. If the file already exists, it will be overwritten
     *
     * @param calendars Calendars
     * @throws IOException
     */
    @Throws(IOException::class)
    fun writeCalendars(calendars: Iterable<Calendar>) = writeToStream(calendars, getOutputStreamForFile(GtfsConstants.CALENDAR_FILE))

    /**
     * Writes calendars to the GTFS feed. If the file already exists, it will be overwritten
     *
     * @param calendars Calendars
     * @throws IOException
     */
    @Throws(IOException::class)
    fun writeCalendars(calendars: Stream<Calendar>) = writeCalendars(calendars.asIterable())

    /**
     * Writes calendar dates to the GTFS feed. If the file already exists, it will be overwritten
     *
     * @param calendarDates Calendar dates
     * @throws IOException
     */
    @Throws(IOException::class)
    fun writeCalendarDates(calendarDates: Iterable<CalendarDate>) = writeToStream(calendarDates, getOutputStreamForFile(GtfsConstants.CALENDAR_DATES_FILE))

    /**
     * Writes calendar dates to the GTFS feed. If the file already exists, it will be overwritten
     *
     * @param calendarDates Calendar dates
     * @throws IOException
     */
    @Throws(IOException::class)
    fun writeCalendarDates(calendarDates: Stream<CalendarDate>) = writeCalendarDates(calendarDates.asIterable())

    /**
     * Writes fare attributes to the GTFS feed. If the file already exists, it will be overwritten
     *
     * @param fareAttributes Fare attributes
     * @throws IOException
     */
    @Throws(IOException::class)
    fun writeFareAttributes(fareAttributes: Iterable<FareAttribute>) = writeToStream(fareAttributes, getOutputStreamForFile(GtfsConstants.FARE_ATTRIBUTES_FILE))

    /**
     * Writes fare attributes to the GTFS feed. If the file already exists, it will be overwritten
     *
     * @param fareAttributes Fare attributes
     * @throws IOException
     */
    @Throws(IOException::class)
    fun writeFareAttributes(fareAttributes: Stream<FareAttribute>) = writeFareAttributes(fareAttributes.asIterable())

    /**
     * Writes fare rules to the GTFS feed. If the file already exists, it will be overwritten
     *
     * @param fareRules Fare rules
     * @throws IOException
     */
    @Throws(IOException::class)
    fun writeFareRules(fareRules: Iterable<FareRule>) = writeToStream(fareRules, getOutputStreamForFile(GtfsConstants.FARE_RULES_FILE))

    /**
     * Writes fare rules to the GTFS feed. If the file already exists, it will be overwritten
     *
     * @param fareRules Fare rules
     * @throws IOException
     */
    @Throws(IOException::class)
    fun writeFareRules(fareRules: Stream<FareRule>) = writeFareRules(fareRules.asIterable())

    /**
     * Writes feed info to the GTFS feed. If the file already exists, it will be overwritten
     *
     * @param feedInfos Feed info
     * @throws IOException
     */
    @Throws(IOException::class)
    fun writeFeedInfos(feedInfos: Iterable<FeedInfo>) = writeToStream(feedInfos, getOutputStreamForFile(GtfsConstants.FEED_INFO_FILE))

    /**
     * Writes feed info to the GTFS feed. If the file already exists, it will be overwritten
     *
     * @param feedInfos Feed info
     * @throws IOException
     */
    @Throws(IOException::class)
    fun writeFeedInfos(feedInfos: Stream<FeedInfo>) = writeFeedInfos(feedInfos.asIterable())

    /**
     * Writes frequencies to the GTFS feed. If the file already exists, it will be overwritten
     *
     * @param frequencies Frequencies
     * @throws IOException
     */
    @Throws(IOException::class)
    fun writeFrequencies(frequencies: Iterable<Frequency>) = writeToStream(frequencies, getOutputStreamForFile(GtfsConstants.FREQUENCIES_FILE))

    /**
     * Writes frequencies to the GTFS feed. If the file already exists, it will be overwritten
     *
     * @param frequencies Frequencies
     * @throws IOException
     */
    @Throws(IOException::class)
    fun writeFrequencies(frequencies: Stream<Frequency>) = writeFrequencies(frequencies.asIterable())

    /**
     * Writes levels to the GTFS feed. If the file already exists, it will be overwritten
     *
     * @param levels Levels
     * @throws IOException
     */
    @Throws(IOException::class)
    fun writeLevels(levels: Iterable<Level>) = writeToStream(levels, getOutputStreamForFile(GtfsConstants.LEVELS_FILE))

    /**
     * Writes levels to the GTFS feed. If the file already exists, it will be overwritten
     *
     * @param levels Levels
     * @throws IOException
     */
    @Throws(IOException::class)
    fun writeLevels(levels: Stream<Level>) = writeLevels(levels.asIterable())

    /**
     * Writes pathways to the GTFS feed. If the file already exists, it will be overwritten
     *
     * @param pathways Pathways
     * @throws IOException
     */
    @Throws(IOException::class)
    fun writePathways(pathways: Iterable<Pathway>) = writeToStream(pathways, getOutputStreamForFile(GtfsConstants.PATHWAYS_FILE))

    /**
     * Writes pathways to the GTFS feed. If the file already exists, it will be overwritten
     *
     * @param pathways Pathways
     * @throws IOException
     */
    @Throws(IOException::class)
    fun writePathways(pathways: Stream<Pathway>) = writePathways(pathways.asIterable())

    /**
     * Writes routes to the GTFS feed. If the file already exists, it will be overwritten
     *
     * @param routes Routes
     * @throws IOException
     */
    @Throws(IOException::class)
    fun writeRoutes(routes: Iterable<Route>) = writeToStream(routes, getOutputStreamForFile(GtfsConstants.ROUTES_FILE))

    /**
     * Writes routes to the GTFS feed. If the file already exists, it will be overwritten
     *
     * @param routes Routes
     * @throws IOException
     */
    @Throws(IOException::class)
    fun writeRoutes(routes: Stream<Route>) = writeRoutes(routes.asIterable())

    /**
     * Writes shapes to the GTFS feed. If the file already exists, it will be overwritten
     *
     * @param shapes Shapes
     * @throws IOException
     */
    @Throws(IOException::class)
    fun writeShapes(shapes: Iterable<Shape>) = writeToStream(shapes, getOutputStreamForFile(GtfsConstants.SHAPES_FILE))

    /**
     * Writes shapes to the GTFS feed. If the file already exists, it will be overwritten
     *
     * @param shapes Shapes
     * @throws IOException
     */
    @Throws(IOException::class)
    fun writeShapes(shapes: Stream<Shape>) = writeShapes(shapes.asIterable())

    /**
     * Writes stops to the GTFS feed. If the file already exists, it will be overwritten
     *
     * @param stops Stops
     * @throws IOException
     */
    @Throws(IOException::class)
    fun writeStops(stops: Iterable<Stop>) = writeToStream(stops, getOutputStreamForFile(GtfsConstants.STOPS_FILE))

    /**
     * Writes stops to the GTFS feed. If the file already exists, it will be overwritten
     *
     * @param stops Stops
     * @throws IOException
     */
    @Throws(IOException::class)
    fun writeStops(stops: Stream<Stop>) = writeStops(stops.asIterable())

    /**
     * Writes stop times to the GTFS feed. If the file already exists, it will be overwritten
     *
     * @param stopTimes Stop times
     * @throws IOException
     */
    @Throws(IOException::class)
    fun writeStopTimes(stopTimes: Iterable<StopTime>) = writeToStream(stopTimes, getOutputStreamForFile(GtfsConstants.STOP_TIMES_FILE))

    /**
     * Writes stop times to the GTFS feed. If the file already exists, it will be overwritten
     *
     * @param stopTimes Stop times
     * @throws IOException
     */
    @Throws(IOException::class)
    fun writeStopTimes(stopTimes: Stream<StopTime>) = writeStopTimes(stopTimes.asIterable())

    /**
     * Writes transfers to the GTFS feed. If the file already exists, it will be overwritten
     *
     * @param transfers Transfers
     * @throws IOException
     */
    @Throws(IOException::class)
    fun writeTransfers(transfers: Iterable<Transfer>) = writeToStream(transfers, getOutputStreamForFile(GtfsConstants.TRANSFERS_FILE))

    /**
     * Writes transfers to the GTFS feed. If the file already exists, it will be overwritten
     *
     * @param transfers Transfers
     * @throws IOException
     */
    @Throws(IOException::class)
    fun writeTransfers(transfers: Stream<Transfer>) = writeTransfers(transfers.asIterable())

    /**
     * Writes translations to the GTFS feed. If the file already exists, it will be overwritten
     *
     * @param translations Translations
     * @throws IOException
     */
    @Throws(IOException::class)
    fun writeTranslations(translations: Iterable<Translation>) = writeToStream(translations, getOutputStreamForFile(GtfsConstants.TRANSLATIONS_FILE))

    /**
     * Writes translations to the GTFS feed. If the file already exists, it will be overwritten
     *
     * @param translations Translations
     * @throws IOException
     */
    @Throws(IOException::class)
    fun writeTranslations(translations: Stream<Translation>) = writeTranslations(translations.asIterable())

    /**
     * Writes trips to the GTFS feed. If the file already exists, it will be overwritten
     *
     * @param trips Trips
     * @throws IOException
     */
    @Throws(IOException::class)
    fun writeTrips(trips: Iterable<Trip>) = writeToStream(trips, getOutputStreamForFile(GtfsConstants.TRIPS_FILE))

    /**
     * Writes trips to the GTFS feed. If the file already exists, it will be overwritten
     *
     * @param trips Trips
     * @throws IOException
     */
    @Throws(IOException::class)
    fun writeTrips(trips: Stream<Trip>) = writeTrips(trips.asIterable())

    /**
     * Writes content to the GTFS feed. Parameters which are null or contain an empty collection are not written
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
        if (!agencies.isNullOrEmpty()) {
            writeAgencies(agencies)
        }
        if (!attributions.isNullOrEmpty()) {
            writeAttributions(attributions)
        }
        if (!calendars.isNullOrEmpty()) {
            writeCalendars(calendars)
        }
        if (!calendarDates.isNullOrEmpty()) {
            writeCalendarDates(calendarDates)
        }
        if (!fareAttributes.isNullOrEmpty()) {
            writeFareAttributes(fareAttributes)
        }
        if (!fareRules.isNullOrEmpty()) {
            writeFareRules(fareRules)
        }
        if (!feedInfos.isNullOrEmpty()) {
            writeFeedInfos(feedInfos)
        }
        if (!frequencies.isNullOrEmpty()) {
            writeFrequencies(frequencies)
        }
        if (!levels.isNullOrEmpty()) {
            writeLevels(levels)
        }
        if (!pathways.isNullOrEmpty()) {
            writePathways(pathways)
        }
        if (!routes.isNullOrEmpty()) {
            writeRoutes(routes)
        }
        if (!shapes.isNullOrEmpty()) {
            writeShapes(shapes)
        }
        if (!stops.isNullOrEmpty()) {
            writeStops(stops)
        }
        if (!stopTimes.isNullOrEmpty()) {
            writeStopTimes(stopTimes)
        }
        if (!transfers.isNullOrEmpty()) {
            writeTransfers(transfers)
        }
        if (!translations.isNullOrEmpty()) {
            writeTranslations(translations)
        }
        if (!trips.isNullOrEmpty()) {
            writeTrips(trips)
        }
    }
}