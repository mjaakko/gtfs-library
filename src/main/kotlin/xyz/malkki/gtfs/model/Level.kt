package xyz.malkki.gtfs.model

import com.univocity.parsers.annotations.Parsed
import xyz.malkki.gtfs.NoArgConstructor

/**
 * See https://developers.google.com/transit/gtfs/reference#levelstxt
 */
@NoArgConstructor
data class Level(
    @Parsed(field = ["level_id"]) val levelId: String,
    @Parsed(field = ["level_index"]) val levelIndex: Double,
    @Parsed(field = ["level_name"]) val levelName: String?
)
