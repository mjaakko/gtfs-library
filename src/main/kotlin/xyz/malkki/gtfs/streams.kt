package xyz.malkki.gtfs

import java.util.stream.Stream

/**
 * Returns the stream as an iterable. **Warning:** the iterator can only be used once
 */
internal fun <T> Stream<T>.asIterable(): Iterable<T> = object : Iterable<T> {
    override fun iterator(): Iterator<T> = this@asIterable.iterator()
}