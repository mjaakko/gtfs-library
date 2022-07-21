package xyz.malkki.gtfs.utils

object GtfsTimeFormat {
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

    @JvmStatic
    fun formatToString(value: Int): String {
        val hours = value / 3600
        val minutes = (value - hours * 3600) / 60
        val seconds = value - hours * 3600 - minutes * 60

        return "${hours.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}"
    }
}