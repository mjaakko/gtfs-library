package xyz.malkki.gtfs.serialization

import com.univocity.parsers.csv.CsvParserSettings
import com.univocity.parsers.csv.CsvRoutines
import xyz.malkki.gtfs.model.*
import xyz.malkki.gtfs.serialization.GtfsConstants.AGENCY_FILE
import xyz.malkki.gtfs.serialization.GtfsConstants.ATTRIBUTIONS_FILE
import xyz.malkki.gtfs.serialization.GtfsConstants.CALENDAR_DATES_FILE
import xyz.malkki.gtfs.serialization.GtfsConstants.CALENDAR_FILE
import xyz.malkki.gtfs.serialization.GtfsConstants.FARE_ATTRIBUTES_FILE
import xyz.malkki.gtfs.serialization.GtfsConstants.FARE_RULES_FILE
import xyz.malkki.gtfs.serialization.GtfsConstants.FEED_INFO_FILE
import xyz.malkki.gtfs.serialization.GtfsConstants.FREQUENCIES_FILE
import xyz.malkki.gtfs.serialization.GtfsConstants.LEVELS_FILE
import xyz.malkki.gtfs.serialization.GtfsConstants.PATHWAYS_FILE
import xyz.malkki.gtfs.serialization.GtfsConstants.ROUTES_FILE
import xyz.malkki.gtfs.serialization.GtfsConstants.SHAPES_FILE
import xyz.malkki.gtfs.serialization.GtfsConstants.STOPS_FILE
import xyz.malkki.gtfs.serialization.GtfsConstants.STOP_TIMES_FILE
import xyz.malkki.gtfs.serialization.GtfsConstants.TRANSFERS_FILE
import xyz.malkki.gtfs.serialization.GtfsConstants.TRANSLATIONS_FILE
import xyz.malkki.gtfs.serialization.GtfsConstants.TRIPS_FILE
import java.io.BufferedInputStream
import java.io.IOException
import java.io.InputStream
import java.nio.charset.StandardCharsets
import java.nio.file.Path
import java.util.stream.Stream
import java.util.zip.ZipFile
import kotlin.streams.asStream

/**
 * @param zipFile ZIP file that contains the GTFS feed
 */
class GtfsFeedParser @Throws(IOException::class) constructor(private val zipFile: ZipFile) : AutoCloseable {
    @Throws(IOException::class)
    constructor(path: Path) : this(ZipFile(path.toFile(), StandardCharsets.UTF_8))

    companion object {
        private val REQUIRED_FILES = listOf(AGENCY_FILE, STOPS_FILE, ROUTES_FILE, TRIPS_FILE, STOP_TIMES_FILE)
    }

    private inline fun <reified T> parseCsv(inputStream: InputStream): Stream<T> {
        val settings = CsvParserSettings().apply {
            isHeaderExtractionEnabled = true
            nullValue = ""
        }

        return CsvRoutines(settings).iterate(T::class.java, inputStream, StandardCharsets.UTF_8).iterator().asSequence().asStream().onClose(inputStream::close)
    }

    private inline fun <reified T> parseFile(fileName: String): Stream<T> {
        val zipEntry = zipFile.getEntry(fileName) ?: return Stream.empty()

        return parseCsv(BufferedInputStream(zipFile.getInputStream(zipEntry)))
    }

    /**
     * Validates that all necessary files are included in the ZIP file. File contents are not validated
     *
     * @return true if GTFS file contains all necessary files, false otherwise
     */
    @Throws(IOException::class)
    fun validateFiles(): Boolean {
        val zipEntryNames = zipFile.entries().toList().map { it.name }.toSet()

        return REQUIRED_FILES.all { it in zipEntryNames }
                && (CALENDAR_FILE in zipEntryNames || CALENDAR_DATES_FILE in zipEntryNames)
                && ((TRANSLATIONS_FILE in zipEntryNames && FEED_INFO_FILE in zipEntryNames) || TRANSLATIONS_FILE !in FEED_INFO_FILE)
    }

    @Throws(IOException::class)
    fun parseAgencies(): Stream<Agency> = parseFile(AGENCY_FILE)

    @Throws(IOException::class)
    fun parseAttributions(): Stream<Attribution> = parseFile(ATTRIBUTIONS_FILE)

    @Throws(IOException::class)
    fun parseCalendars(): Stream<Calendar> = parseFile(CALENDAR_FILE)

    @Throws(IOException::class)
    fun parseCalendarDates(): Stream<CalendarDate> = parseFile(CALENDAR_DATES_FILE)

    @Throws(IOException::class)
    fun parseFareRules(): Stream<FareRule> = parseFile(FARE_RULES_FILE)

    @Throws(IOException::class)
    fun parseFareAttributes(): Stream<FareAttribute> = parseFile(FARE_ATTRIBUTES_FILE)

    @Throws(IOException::class)
    fun parseFeedInfo(): Stream<FeedInfo> = parseFile(FEED_INFO_FILE)

    @Throws(IOException::class)
    fun parseFrequencies(): Stream<Frequency> = parseFile(FREQUENCIES_FILE)

    @Throws(IOException::class)
    fun parseLevels(): Stream<Level> = parseFile(LEVELS_FILE)

    @Throws(IOException::class)
    fun parsePathways(): Stream<Pathway> = parseFile(PATHWAYS_FILE)

    @Throws(IOException::class)
    fun parseRoutes(): Stream<Route> = parseFile(ROUTES_FILE)

    @Throws(IOException::class)
    fun parseShapes(): Stream<Shape> = parseFile(SHAPES_FILE)

    @Throws(IOException::class)
    fun parseStops(): Stream<Stop> = parseFile(STOPS_FILE)

    @Throws(IOException::class)
    fun parseStopTimes(): Stream<StopTime> = parseFile(STOP_TIMES_FILE)

    @Throws(IOException::class)
    fun parseTransfers(): Stream<Transfer> = parseFile(TRANSFERS_FILE)

    @Throws(IOException::class)
    fun parseTranslations(): Stream<Translation> = parseFile(TRANSLATIONS_FILE)

    @Throws(IOException::class)
    fun parseTrips(): Stream<Trip> = parseFile(TRIPS_FILE)

    override fun close() {
        zipFile.close()
    }
}