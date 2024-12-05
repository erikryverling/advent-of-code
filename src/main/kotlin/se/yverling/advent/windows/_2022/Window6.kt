package se.yverling.advent.windows._2022

import java.util.*
import se.yverling.advent.Window

internal class Window6 : Window() {
    override fun part1(): String {
        val line = reader.file().readLines()[0]
        return (numberOfCharactersBeforeMarker(line, markerSize = 4) - 1).toString()
    }

    override fun part2(): String {
        val line = reader.file().readLines()[0]
        return (numberOfCharactersBeforeMarker(line, markerSize = 14) - 1).toString()
    }

    private fun numberOfCharactersBeforeMarker(line: String, markerSize: Int): Int {
        var readChars = 0
        // TODO Use substring instead?
        val marker = LinkedList<Char>()
        run scan@{
            line.forEach { char ->
                readChars++
                if (marker.size == markerSize) {
                    if (isUnique(marker)) {
                        return@scan
                    }
                    marker.remove()
                }
                marker.add(char)
            }
        }
        return readChars
    }

    private fun isUnique(marker: LinkedList<Char>): Boolean {
        val result = marker.toMutableList()
        marker.toSet().forEach { char ->
            result.remove(char)
        }
        return result.isEmpty()
    }
}
