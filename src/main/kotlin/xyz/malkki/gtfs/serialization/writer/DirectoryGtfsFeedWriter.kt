package xyz.malkki.gtfs.serialization.writer

import java.io.BufferedOutputStream
import java.io.OutputStream
import java.nio.file.Files
import java.nio.file.Path

/**
 * Writes uncompressed files containing the GTFS feed to the specified directory
 *
 * @param directory Directory where the GTFS files will be written
 */
class DirectoryGtfsFeedWriter(private val directory: Path) : GtfsFeedWriter() {
    override fun getOutputStreamForFile(fileName: String): OutputStream {
        val file = directory.resolve(fileName)

        return BufferedOutputStream(Files.newOutputStream(file))
    }

    /**
     * Does nothing
     */
    override fun close() { }
}