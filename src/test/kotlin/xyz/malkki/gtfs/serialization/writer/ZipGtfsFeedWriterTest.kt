package xyz.malkki.gtfs.serialization.writer

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.junit.jupiter.api.io.TempDir
import xyz.malkki.gtfs.model.Calendar
import xyz.malkki.gtfs.model.Route
import xyz.malkki.gtfs.model.Stop
import xyz.malkki.gtfs.model.Trip
import xyz.malkki.gtfs.serialization.GtfsConstants
import java.io.File
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.time.LocalDate
import java.util.zip.ZipFile
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class ZipGtfsFeedWriterTest {
    @field:TempDir
    lateinit var tempFolder: File

    @Test
    fun `Test writing data to GTFS ZIP archive`() {
        val gtfsFile = tempFolder.toPath().resolve("gtfs.zip")

        val writer = ZipGtfsFeedWriter(gtfsFile)

        writer.use {
            it.writeStops(listOf(
                Stop(stopId = "1"),
                Stop(stopId = "2"),
                Stop(stopId = "3")
            ))
            it.writeTrips(listOf(
                Trip(routeId = "a", serviceId = "service_a", tripId = "trip_1"),
                Trip(routeId = "a", serviceId = "service_a", tripId = "trip_2"),
                Trip(routeId = "a", serviceId = "service_a", tripId = "trip_3")
            ))
            it.writeRoutes(listOf(
                Route(routeId = "a", routeType = Route.ROUTE_TYPE_BUS)
            ))
            it.writeCalendars(listOf(
                Calendar(
                    serviceId = "service_a",
                    monday = true,
                    tuesday = true,
                    wednesday = true,
                    thursday = true,
                    friday = true,
                    saturday = true,
                    sunday = true,
                    startDate = LocalDate.now(),
                    endDate = LocalDate.now().plusDays(100)
                )
            ))
        }

        assertThat(Files.size(gtfsFile), Matchers.greaterThan(0))

        val zipEntries = ZipFile(gtfsFile.toFile(), StandardCharsets.UTF_8).use { zipFile -> zipFile.entries().toList().map { it.name } }

        assertEquals(4, zipEntries.size)
        assertContains(zipEntries, GtfsConstants.ROUTES_FILE)
        assertContains(zipEntries, GtfsConstants.STOPS_FILE)
        assertContains(zipEntries, GtfsConstants.TRIPS_FILE)
        assertContains(zipEntries, GtfsConstants.CALENDAR_FILE)
    }
}