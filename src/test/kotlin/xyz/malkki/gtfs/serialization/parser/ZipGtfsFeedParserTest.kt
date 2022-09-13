package xyz.malkki.gtfs.serialization.parser

import java.nio.file.Path
import kotlin.io.path.toPath
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ZipGtfsFeedParserTest {
    private fun getResourcePath(resourceName: String): Path = ZipGtfsFeedParserTest::class.java.classLoader.getResource(resourceName).toURI().toPath()

    @Test
    fun `Test validating valid GTFS package`() {
        val file = getResourcePath("valid_gtfs.zip")
        ZipGtfsFeedParser(file).use {
            assertTrue(it.validateFiles())
        }
    }

    @Test
    fun `Test validating invalid GTFS package`() {
        val file = getResourcePath("invalid_gtfs.zip")
        ZipGtfsFeedParser(file).use {
            assertFalse(it.validateFiles())
        }
    }

    @Test
    fun `Test reading routes from valid GTFS package`() {
        val file = getResourcePath("valid_gtfs.zip")
        ZipGtfsFeedParser(file).use {
            assertEquals(1, it.parseRoutes().count())
        }
    }
}