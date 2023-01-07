package xyz.malkki.gtfs.serialization.parser

import java.nio.file.Path
import java.util.zip.ZipInputStream
import kotlin.io.path.inputStream
import kotlin.io.path.toPath
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ZipStreamGtfsFeedParserTest {
    private fun getResourcePath(resourceName: String): Path = ZipStreamGtfsFeedParserTest::class.java.classLoader.getResource(resourceName).toURI().toPath()

    @Test
    fun `Test validating valid GTFS package`() {
        val file = getResourcePath("valid_gtfs.zip")
        ZipStreamGtfsFeedParser(ZipInputStream(file.inputStream())).use {
            assertTrue(it.validateFiles())
        }
    }

    @Test
    fun `Test validating invalid GTFS package`() {
        val file = getResourcePath("invalid_gtfs.zip")
        ZipStreamGtfsFeedParser(ZipInputStream(file.inputStream())).use {
            assertFalse(it.validateFiles())
        }
    }

    @Test
    fun `Test reading routes from valid GTFS package`() {
        val file = getResourcePath("valid_gtfs.zip")
        ZipStreamGtfsFeedParser(ZipInputStream(file.inputStream())).use {
            assertEquals(1, it.parseRoutes().count())
        }
    }
}