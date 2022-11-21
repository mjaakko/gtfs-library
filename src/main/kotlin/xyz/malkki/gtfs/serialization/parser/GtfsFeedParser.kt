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

    protected inline fun <reified T> parseCsv(inputStream: InputStream): Stream<T> {
        val settings = CsvParserSettings().apply {
            isHeaderExtractionEnabled = true
            nullValue = ""
        }

        return CsvRoutines(settings).iterate(T::class.java, inputStream, StandardCharsets.UTF_8).iterator().asSequence().asStream().onClose(inputStream::close)
    }

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
    abstract fun parseAgencies(): Stream<Agency>

    @Throws(IOException::class)
    abstract fun parseAttributions(): Stream<Attribution>

    @Throws(IOException::class)
    abstract fun parseCalendars(): Stream<Calendar>

    @Throws(IOException::class)
    abstract fun parseCalendarDates(): Stream<CalendarDate>

    @Throws(IOException::class)
    abstract fun parseFareRules(): Stream<FareRule>

    @Throws(IOException::class)
    abstract fun parseFareAttributes(): Stream<FareAttribute>

    @Throws(IOException::class)
    abstract fun parseFeedInfo(): Stream<FeedInfo>

    @Throws(IOException::class)
    abstract fun parseFrequencies(): Stream<Frequency>

    @Throws(IOException::class)
    abstract fun parseLevels(): Stream<Level>

    @Throws(IOException::class)
    abstract fun parsePathways(): Stream<Pathway>

    @Throws(IOException::class)
    abstract fun parseRoutes(): Stream<Route>

    @Throws(IOException::class)
    abstract fun parseShapes(): Stream<Shape>

    @Throws(IOException::class)
    abstract fun parseStops(): Stream<Stop>

    @Throws(IOException::class)
    abstract fun parseStopTimes(): Stream<StopTime>

    @Throws(IOException::class)
    abstract fun parseTransfers(): Stream<Transfer>

    @Throws(IOException::class)
    abstract fun parseTranslations(): Stream<Translation>

    @Throws(IOException::class)
    abstract fun parseTrips(): Stream<Trip>
}