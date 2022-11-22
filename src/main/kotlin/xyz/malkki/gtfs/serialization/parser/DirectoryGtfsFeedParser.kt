package xyz.malkki.gtfs.serialization.parser

import java.io.BufferedInputStream
import java.io.InputStream
import java.nio.file.Files
import java.nio.file.Path
import kotlin.streams.toList

/**
 * Parses uncompressed GTFS files from a directory. The directory must contain all files of the GTFS feed
 *
 * @param dirPath Path to the directory which contains files of the GTFS feed
 */
class DirectoryGtfsFeedParser(private val dirPath: Path) : GtfsFeedParser() {
    override fun getInputStream(fileName: String): InputStream? {
        val filePath = dirPath.resolve(fileName)
        if (Files.notExists(filePath)) {
            return null
        } else {
            return BufferedInputStream(Files.newInputStream(filePath))
        }
    }

    override fun getFileNames(): Set<String> {
        return Files.list(dirPath).map { it.fileName.toString() }.toList().toSet()
    }

    override fun close() {

    }
}