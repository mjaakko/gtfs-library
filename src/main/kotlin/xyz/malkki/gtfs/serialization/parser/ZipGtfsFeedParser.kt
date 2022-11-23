package xyz.malkki.gtfs.serialization.parser

import java.io.BufferedInputStream
import java.io.IOException
import java.io.InputStream
import java.nio.charset.StandardCharsets
import java.nio.file.Path
import java.util.zip.ZipFile

/**
 * Parses GTFS feed from a ZIP archive
 *
 * @param zipFile ZIP file that contains the GTFS feed
 */
class ZipGtfsFeedParser @Throws(IOException::class) constructor(private val zipFile: ZipFile) : GtfsFeedParser() {
    /**
     * @param path Path to the ZIP file that contains the GTFS feed
     */
    @Throws(IOException::class)
    constructor(path: Path) : this(ZipFile(path.toFile(), StandardCharsets.UTF_8))

    override fun getInputStream(fileName: String): InputStream? = zipFile.getEntry(fileName)?.let { BufferedInputStream(zipFile.getInputStream(it)) }

    override fun getFileNames(): Set<String> {
        return zipFile.entries().asSequence().map { it.name }.toSet()
    }

    /**
     * Closes the ZIP file
     */
    override fun close() {
        zipFile.close()
    }
}