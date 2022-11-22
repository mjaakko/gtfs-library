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

    @Throws(IOException::class)
    fun parseAgencies(): Stream<Agency> = parseCsv(getInputStream(GtfsConstants.AGENCY_FILE))

    @Throws(IOException::class)
    fun parseAttributions(): Stream<Attribution> = parseCsv(getInputStream(GtfsConstants.ATTRIBUTIONS_FILE))

    @Throws(IOException::class)
    fun parseCalendars(): Stream<Calendar> = parseCsv(getInputStream(GtfsConstants.CALENDAR_FILE))

    @Throws(IOException::class)
    fun parseCalendarDates(): Stream<CalendarDate> = parseCsv(getInputStream(GtfsConstants.CALENDAR_DATES_FILE))

    @Throws(IOException::class)
    fun parseFareRules(): Stream<FareRule> = parseCsv(getInputStream(GtfsConstants.FARE_RULES_FILE))

    @Throws(IOException::class)
    fun parseFareAttributes(): Stream<FareAttribute> = parseCsv(getInputStream(GtfsConstants.FARE_ATTRIBUTES_FILE))

    @Throws(IOException::class)
    fun parseFeedInfo(): Stream<FeedInfo> = parseCsv(getInputStream(GtfsConstants.FEED_INFO_FILE))

    @Throws(IOException::class)
    fun parseFrequencies(): Stream<Frequency> = parseCsv(getInputStream(GtfsConstants.FREQUENCIES_FILE))

    @Throws(IOException::class)
    fun parseLevels(): Stream<Level> = parseCsv(getInputStream(GtfsConstants.LEVELS_FILE))

    @Throws(IOException::class)
    fun parsePathways(): Stream<Pathway> = parseCsv(getInputStream(GtfsConstants.PATHWAYS_FILE))

    @Throws(IOException::class)
    fun parseRoutes(): Stream<Route> = parseCsv(getInputStream(GtfsConstants.ROUTES_FILE))

    @Throws(IOException::class)
    fun parseShapes(): Stream<Shape> = parseCsv(getInputStream(GtfsConstants.SHAPES_FILE))

    @Throws(IOException::class)
    fun parseStops(): Stream<Stop> = parseCsv(getInputStream(GtfsConstants.STOPS_FILE))

    @Throws(IOException::class)
    fun parseStopTimes(): Stream<StopTime> = parseCsv(getInputStream(GtfsConstants.STOP_TIMES_FILE))

    @Throws(IOException::class)
    fun parseTransfers(): Stream<Transfer> = parseCsv(getInputStream(GtfsConstants.TRANSFERS_FILE))

    @Throws(IOException::class)
    fun parseTranslations(): Stream<Translation> = parseCsv(getInputStream(GtfsConstants.TRANSLATIONS_FILE))

    @Throws(IOException::class)
    fun parseTrips(): Stream<Trip> = parseCsv(getInputStream(GtfsConstants.TRIPS_FILE))
}