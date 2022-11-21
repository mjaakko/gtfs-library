package xyz.malkki.gtfs.serialization.writer

import xyz.malkki.gtfs.model.*
import xyz.malkki.gtfs.serialization.GtfsConstants
import java.io.BufferedOutputStream
import java.nio.file.Files
import java.nio.file.Path

/**
 * Writes uncompressed files containing the GTFS feed to the specified directory
 *
 * @param directory Directory where the GTFS files will be written
 */
class DirectoryGtfsFeedWriter(private val directory: Path) : GtfsFeedWriter() {
    private inline fun <reified T> writeFile(content: Collection<T>?, fileName: String) {
        val file = directory.resolve(fileName)

        writeToStream(content, BufferedOutputStream(Files.newOutputStream(file)))
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

    override fun close() { }
}