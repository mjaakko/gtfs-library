package xyz.malkki.gtfs.serialization.parser

import com.univocity.parsers.csv.CsvParserSettings
import com.univocity.parsers.csv.CsvRoutines
import xyz.malkki.gtfs.model.*
import xyz.malkki.gtfs.serialization.GtfsConstants
import java.io.IOException
import java.io.InputStream
import java.nio.charset.StandardCharsets
import java.util.stream.Stream
import kotlin.streams.asStream

/**
 * Base class for GTFS parsers. This class implements the parsing CSV data from input streams and subclasses implement reading input streams from different sources
 */
abstract class GtfsFeedParser : AutoCloseable {
    companion object {
        private val REQUIRED_FILES = listOf(
            GtfsConstants.AGENCY_FILE,
            GtfsConstants.STOPS_FILE,
            GtfsConstants.ROUTES_FILE,
            GtfsConstants.TRIPS_FILE,
            GtfsConstants.STOP_TIMES_FILE
        )
    }

    private inline fun <reified T> parseCsv(inputStream: InputStream?): Stream<T> {
        if (inputStream == null) {
            return Stream.empty()
        }

        val settings = CsvParserSettings().apply {
            isHeaderExtractionEnabled = true
            nullValue = ""
        }

        return CsvRoutines(settings).iterate(T::class.java, inputStream, StandardCharsets.UTF_8).iterator().asSequence().asStream().onClose(inputStream::close)
    }

    @Throws(IOException::class)
    protected abstract fun getInputStream(fileName: String): InputStream?

    @Throws(IOException::class)
    protected abstract fun getFileNames(): Set<String>

    /**
     * Verifies that the GTFS feed contains all necessary files. Only the presence of files is validated, not their content.
     *
     * See [https://developers.google.com/transit/gtfs/reference#dataset_files](https://developers.google.com/transit/gtfs/reference#dataset_files) for list of files that are required for a valid GTFS feed.
     *
     * @return true if the GTFS feed contains all necessary files, false if not
     */
    @Throws(IOException::class)
    fun validateFiles(): Boolean {
        val fileNames = getFileNames()

        return REQUIRED_FILES.all { it in fileNames }
                && (GtfsConstants.CALENDAR_FILE in fileNames || GtfsConstants.CALENDAR_DATES_FILE in fileNames)
                && ((GtfsConstants.TRANSLATIONS_FILE in fileNames && GtfsConstants.FEED_INFO_FILE in fileNames) || GtfsConstants.TRANSLATIONS_FILE !in GtfsConstants.FEED_INFO_FILE)
    }

    /**
     * Parses agencies file from the GTFS feed
     *
     * @return Stream of agencies
     */
    @Throws(IOException::class)
    fun parseAgencies(): Stream<Agency> = parseCsv(getInputStream(GtfsConstants.AGENCY_FILE))

    /**
     * Parses attributions file from the GTFS feed
     *
     * @return Stream of attributions
     */
    @Throws(IOException::class)
    fun parseAttributions(): Stream<Attribution> = parseCsv(getInputStream(GtfsConstants.ATTRIBUTIONS_FILE))

    /**
     * Parses calendars file from the GTFS feed
     *
     * @return Stream of calendars
     */
    @Throws(IOException::class)
    fun parseCalendars(): Stream<Calendar> = parseCsv(getInputStream(GtfsConstants.CALENDAR_FILE))

    /**
     * Parses calendar dates file from the GTFS feed
     *
     * @return Stream of calendar dates
     */
    @Throws(IOException::class)
    fun parseCalendarDates(): Stream<CalendarDate> = parseCsv(getInputStream(GtfsConstants.CALENDAR_DATES_FILE))

    /**
     * Parses fare rules file from the GTFS feed
     *
     * @return Stream of fare rules
     */
    @Throws(IOException::class)
    fun parseFareRules(): Stream<FareRule> = parseCsv(getInputStream(GtfsConstants.FARE_RULES_FILE))

    /**
     * Parses fare attributes file from the GTFS feed
     *
     * @return Stream of fare attributes
     */
    @Throws(IOException::class)
    fun parseFareAttributes(): Stream<FareAttribute> = parseCsv(getInputStream(GtfsConstants.FARE_ATTRIBUTES_FILE))

    /**
     * Parses feed info file from the GTFS feed
     *
     * @return Stream of feed info
     */
    @Throws(IOException::class)
    fun parseFeedInfo(): Stream<FeedInfo> = parseCsv(getInputStream(GtfsConstants.FEED_INFO_FILE))

    /**
     * Parses frequencies file from the GTFS feed
     *
     * @return Stream of frequencies
     */
    @Throws(IOException::class)
    fun parseFrequencies(): Stream<Frequency> = parseCsv(getInputStream(GtfsConstants.FREQUENCIES_FILE))

    /**
     * Parses levels file from the GTFS feed
     *
     * @return Stream of levels
     */
    @Throws(IOException::class)
    fun parseLevels(): Stream<Level> = parseCsv(getInputStream(GtfsConstants.LEVELS_FILE))

    /**
     * Parses pathways file from the GTFS feed
     *
     * @return Stream of pathways
     */
    @Throws(IOException::class)
    fun parsePathways(): Stream<Pathway> = parseCsv(getInputStream(GtfsConstants.PATHWAYS_FILE))

    /**
     * Parses routes file from the GTFS feed
     *
     * @return Stream of routes
     */
    @Throws(IOException::class)
    fun parseRoutes(): Stream<Route> = parseCsv(getInputStream(GtfsConstants.ROUTES_FILE))

    /**
     * Parses shapes file from the GTFS feed
     *
     * @return Stream of shapes
     */
    @Throws(IOException::class)
    fun parseShapes(): Stream<Shape> = parseCsv(getInputStream(GtfsConstants.SHAPES_FILE))

    /**
     * Parses stops file from the GTFS feed
     *
     * @return Stream of stops
     */
    @Throws(IOException::class)
    fun parseStops(): Stream<Stop> = parseCsv(getInputStream(GtfsConstants.STOPS_FILE))

    /**
     * Parses stop times file from the GTFS feed
     *
     * @return Stream of stop times
     */
    @Throws(IOException::class)
    fun parseStopTimes(): Stream<StopTime> = parseCsv(getInputStream(GtfsConstants.STOP_TIMES_FILE))

    /**
     * Parses transfers file from the GTFS feed
     *
     * @return Stream of transfers
     */
    @Throws(IOException::class)
    fun parseTransfers(): Stream<Transfer> = parseCsv(getInputStream(GtfsConstants.TRANSFERS_FILE))

    /**
     * Parses translations file from the GTFS feed
     *
     * @return Stream of translations
     */
    @Throws(IOException::class)
    fun parseTranslations(): Stream<Translation> = parseCsv(getInputStream(GtfsConstants.TRANSLATIONS_FILE))

    /**
     * Parses trips file from the GTFS feed
     *
     * @return Stream of trips
     */
    @Throws(IOException::class)
    fun parseTrips(): Stream<Trip> = parseCsv(getInputStream(GtfsConstants.TRIPS_FILE))
}