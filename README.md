# gtfs-library [![Run tests and build project](https://github.com/mjaakko/gtfs-library/actions/workflows/build.yml/badge.svg)](https://github.com/mjaakko/gtfs-library/actions/workflows/build.yml)
Kotlin library for handling GTFS files, with focus on Java interoperability. Requires Java 11+. Supports both parsing and writing GTFS files.

Documentation for latest version is available [here](https://gtfslibrary.malkki.xyz/).

## Features

* Parsing GTFS data from ZIP archives and uncompressed CSV files
* Writing GTFS data to ZIP archives and uncompressed CSV files
* Data classes for GTFS data types
  * Properties use corresponding Java types when it makes sense (e.g. `LocalDate` for dates)
* Utilities for handling GTFS date and time values

## Usage

`gtfs-library` is published as a Maven artifact through GitHub packages. See [package page](https://github.com/mjaakko/gtfs-library/packages/1537290) for latest version.

**Note:** GitHub Packages registry must be configured to use the package. See GitHub's documentation for [Maven](https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-apache-maven-registry) and [Gradle](https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-gradle-registry) for more details.

### Examples

* Print all stop IDs and names:
```kotlin
val gtfsParser = ZipGtfsFeedParser(Paths.get("gtfs.zip"))

val stops = gtfsParser.parseStops().toList()
stops.forEach { stop ->
  println("${stop.stopId} - ${stop.stopName}")
}

gtfsParser.close()
```