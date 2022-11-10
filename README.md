# gtfs-library [![Run tests and build project](https://github.com/mjaakko/gtfs-library/actions/workflows/build.yml/badge.svg)](https://github.com/mjaakko/gtfs-library/actions/workflows/build.yml)
Kotlin library for handling GTFS files. Supports both parsing and writing GTFS files.

Documentation for latest version is available [here](https://gtfslibrary.malkki.xyz/)

## Usage

`gtfs-library` is published as a Maven artifact through GitHub packages. See [package page](https://github.com/mjaakko/gtfs-library/packages/1537290) for latest version.

Note that GitHub Packages registry must be configured to use the package. See GitHub's documentation for [Maven](https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-apache-maven-registry) and [Gradle](https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-gradle-registry) for more details.

### Examples

Reading list of stops:
```kotlin
val gtfsParser = ZipGtfsFeedParser(Paths.get("gtfs.zip"))

val stopList = gtfsParser.parseStops().toList()

gtfsParser.close()
```