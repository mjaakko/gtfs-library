package xyz.malkki.gtfs.serialization.parser

import java.io.IOException
import java.io.InputStream
import java.util.zip.ZipInputStream

/**
 * Parses GTFS feed from ZipInputStream.
 *
 * The input stream is lazily read fully into memory in uncompressed format. This can be inefficient for memory usage if only part of the feed is actually needed. Prefer [ZipGtfsFeedParser] in those cases.
 *
 * @param zipInputStream input stream from where the GTFS feed can be read from
 */
class ZipStreamGtfsFeedParser @Throws(IOException::class) constructor(private val zipInputStream: ZipInputStream) : GtfsFeedParser() {
    private val fileContents by lazy {
        zipInputStream.use {
            val output = mutableMapOf<String, ByteArray>()

            var currentEntry = zipInputStream.nextEntry
            while (currentEntry != null) {
                output[currentEntry.name] = zipInputStream.readBytes()
                zipInputStream.closeEntry()

                currentEntry = zipInputStream.nextEntry
            }

            return@lazy output
        }
    }

    override fun getInputStream(fileName: String): InputStream? = fileContents[fileName]?.inputStream()

    override fun getFileNames(): Set<String> = fileContents.keys

    /**
     * Does nothing. The ZIP input stream is fully read and closed when data is first accessed
     */
    override fun close() { }
}