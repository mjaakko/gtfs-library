package xyz.malkki.gtfs.serialization.parser

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
import java.nio.charset.StandardCharsets
import java.nio.file.Path
import java.util.stream.Stream
import java.util.zip.ZipFile

/**
 * @param zipFile ZIP file that contains the GTFS feed
 */
class ZipGtfsFeedParser @Throws(IOException::class) constructor(private val zipFile: ZipFile) : GtfsFeedParser() {
    @Throws(IOException::class)
    constructor(path: Path) : this(ZipFile(path.toFile(), StandardCharsets.UTF_8))

    private inline fun <reified T> parseFile(fileName: String): Stream<T> {
        val zipEntry = zipFile.getEntry(fileName) ?: return Stream.empty()

        return parseCsv(BufferedInputStream(zipFile.getInputStream(zipEntry)))
    }

    override fun getFileNames(): Set<String> {
        return zipFile.entries().asSequence().map { it.name }.toSet()
    }

    @Throws(IOException::class)
    override fun parseAgencies(): Stream<Agency> = parseFile(AGENCY_FILE)

    @Throws(IOException::class)
    override fun parseAttributions(): Stream<Attribution> = parseFile(ATTRIBUTIONS_FILE)

    @Throws(IOException::class)
    override fun parseCalendars(): Stream<Calendar> = parseFile(CALENDAR_FILE)

    @Throws(IOException::class)
    override fun parseCalendarDates(): Stream<CalendarDate> = parseFile(CALENDAR_DATES_FILE)

    @Throws(IOException::class)
    override fun parseFareRules(): Stream<FareRule> = parseFile(FARE_RULES_FILE)

    @Throws(IOException::class)
    override fun parseFareAttributes(): Stream<FareAttribute> = parseFile(FARE_ATTRIBUTES_FILE)

    @Throws(IOException::class)
    override fun parseFeedInfo(): Stream<FeedInfo> = parseFile(FEED_INFO_FILE)

    @Throws(IOException::class)
    override fun parseFrequencies(): Stream<Frequency> = parseFile(FREQUENCIES_FILE)

    @Throws(IOException::class)
    override fun parseLevels(): Stream<Level> = parseFile(LEVELS_FILE)

    @Throws(IOException::class)
    override fun parsePathways(): Stream<Pathway> = parseFile(PATHWAYS_FILE)

    @Throws(IOException::class)
    override fun parseRoutes(): Stream<Route> = parseFile(ROUTES_FILE)

    @Throws(IOException::class)
    override fun parseShapes(): Stream<Shape> = parseFile(SHAPES_FILE)

    @Throws(IOException::class)
    override fun parseStops(): Stream<Stop> = parseFile(STOPS_FILE)

    @Throws(IOException::class)
    override fun parseStopTimes(): Stream<StopTime> = parseFile(STOP_TIMES_FILE)

    @Throws(IOException::class)
    override fun parseTransfers(): Stream<Transfer> = parseFile(TRANSFERS_FILE)

    @Throws(IOException::class)
    override fun parseTranslations(): Stream<Translation> = parseFile(TRANSLATIONS_FILE)

    @Throws(IOException::class)
    override fun parseTrips(): Stream<Trip> = parseFile(TRIPS_FILE)

    override fun close() {
        zipFile.close()
    }
}