package xyz.malkki.gtfs.serialization.writer

import java.io.BufferedOutputStream
import java.io.IOException
import java.io.OutputStream
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import java.util.zip.Deflater
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

/**
 * Writes the GTFS feed to a ZIP archive.
 *
 * @param outputStream Output stream where the GTFS zip will be written
 * @param compressionLevel Compression level to be used, see [java.util.zip.Deflater] for possible values
 */
class ZipGtfsFeedWriter @JvmOverloads @Throws(IOException::class) constructor(outputStream: OutputStream, compressionLevel: Int = Deflater.BEST_COMPRESSION) : GtfsFeedWriter() {
    /**
     * @param outputFile Path to the GTFS file
     * @param compressionLevel Compression level to be used, see [java.util.zip.Deflater] for possible values
     */
    @JvmOverloads
    @Throws(IOException::class)
    constructor(outputFile: Path, compressionLevel: Int = Deflater.BEST_COMPRESSION) : this(BufferedOutputStream(Files.newOutputStream(outputFile)), compressionLevel)

    private val zipOutputStream = ZipOutputStream(outputStream, StandardCharsets.UTF_8).apply {
        setLevel(compressionLevel)
    }

    override fun getOutputStreamForFile(fileName: String): OutputStream = ZipEntryOutputStream(zipOutputStream, fileName)

    /**
     * Closes the underlying output stream
     */
    override fun close() = zipOutputStream.close()

    private class ZipEntryOutputStream(private val zipOutputStream: ZipOutputStream, entryName: String) : OutputStream() {
        init {
            zipOutputStream.putNextEntry(ZipEntry(entryName))
        }

        override fun flush() = zipOutputStream.flush()

        override fun write(b: Int) = zipOutputStream.write(b)

        override fun write(b: ByteArray, off: Int, len: Int) = zipOutputStream.write(b, off, len)

        override fun write(b: ByteArray) = zipOutputStream.write(b)

        override fun close() {
            zipOutputStream.closeEntry()
        }
    }
}