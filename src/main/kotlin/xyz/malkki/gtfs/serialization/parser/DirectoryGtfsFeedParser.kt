package xyz.malkki.gtfs.serialization.parser

import xyz.malkki.gtfs.model.*
import xyz.malkki.gtfs.serialization.GtfsConstants
import java.io.BufferedInputStream
import java.nio.file.Files
import java.nio.file.Path
import java.util.stream.Stream
import kotlin.streams.toList

/**
 * Parses uncompressed GTFS files from a directory. The directory must contain all files of the GTFS feed
 *
 * @param dirPath Path to the directory which contains files of the GTFS feed
 */
class DirectoryGtfsFeedParser(private val dirPath: Path) : GtfsFeedParser() {
    override fun getFileNames(): Set<String> {
        return Files.list(dirPath).map { it.fileName.toString() }.toList().toSet()
    }

    private inline fun <reified T> parseFile(fileName: String): Stream<T> {
        val filePath = dirPath.resolve(fileName)
        if (Files.notExists(filePath)) {
            return Stream.empty()
        }

        return parseCsv(BufferedInputStream(Files.newInputStream(filePath)))
    }

    override fun parseAgencies(): Stream<Agency> = parseFile(GtfsConstants.AGENCY_FILE)

    override fun parseAttributions(): Stream<Attribution> = parseFile(GtfsConstants.ATTRIBUTIONS_FILE)

    override fun parseCalendars(): Stream<Calendar> = parseFile(GtfsConstants.CALENDAR_FILE)

    override fun parseCalendarDates(): Stream<CalendarDate> = parseFile(GtfsConstants.CALENDAR_DATES_FILE)

    override fun parseFareRules(): Stream<FareRule> = parseFile(GtfsConstants.FARE_RULES_FILE)

    override fun parseFareAttributes(): Stream<FareAttribute> = parseFile(GtfsConstants.FARE_ATTRIBUTES_FILE)

    override fun parseFeedInfo(): Stream<FeedInfo> = parseFile(GtfsConstants.FEED_INFO_FILE)

    override fun parseFrequencies(): Stream<Frequency> = parseFile(GtfsConstants.FREQUENCIES_FILE)

    override fun parseLevels(): Stream<Level> = parseFile(GtfsConstants.LEVELS_FILE)

    override fun parsePathways(): Stream<Pathway> = parseFile(GtfsConstants.PATHWAYS_FILE)

    override fun parseRoutes(): Stream<Route> = parseFile(GtfsConstants.ROUTES_FILE)

    override fun parseShapes(): Stream<Shape> = parseFile(GtfsConstants.SHAPES_FILE)

    override fun parseStops(): Stream<Stop> = parseFile(GtfsConstants.STOPS_FILE)

    override fun parseStopTimes(): Stream<StopTime> = parseFile(GtfsConstants.STOP_TIMES_FILE)

    override fun parseTransfers(): Stream<Transfer> = parseFile(GtfsConstants.TRANSFERS_FILE)

    override fun parseTranslations(): Stream<Translation> = parseFile(GtfsConstants.TRANSLATIONS_FILE)

    override fun parseTrips(): Stream<Trip> = parseFile(GtfsConstants.TRIPS_FILE)

    override fun close() {

    }
}