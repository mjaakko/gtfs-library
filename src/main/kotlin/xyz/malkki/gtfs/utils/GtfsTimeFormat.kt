package xyz.malkki.gtfs.utils

object GtfsTimeFormat {
    /**
     * Parses time in GTFS format (`HH:mm:ss`) to a number of seconds. Note that the amount of hours can be more than 23
     *
     * Example:
     * ```
     * parseFromString("15:45:30") == 56730
     * parseFromString("26:45:30") == 96330
     * ```
     *
     * @throws IllegalArgumentException If the time string is not in correct format
     * @return Number of seconds
     */
    @JvmStatic
    fun parseFromString(str: String): Int {
        val strParts = str.split(":", limit = 3)
        if (strParts.size != 3) {
            throw IllegalArgumentException("Invalid time string: $str")
        }
        try {
            val (hours, minutes, seconds) = strParts.map { it.toInt() }

            return hours * 60 * 60 + minutes * 60 + seconds
        } catch (nfe: NumberFormatException) {
            throw IllegalArgumentException("Invalid time string: $str")
        }
    }

    /**
     * Formats the amount of seconds to time string in GTFS format. Note that the amount of hours can be more than 23
     *
     * Example:
     * ```
     * formatToString(56730) == "15:45:30"
     * formatToString(96330) == "26:45:30"
     * ```
     *
     * @return Time string in GTFS format
     */
    @JvmStatic
    fun formatToString(value: Int): String {
        val hours = value / 3600
        val minutes = (value - hours * 3600) / 60
        val seconds = value - hours * 3600 - minutes * 60

        return "${hours.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}"
    }
}