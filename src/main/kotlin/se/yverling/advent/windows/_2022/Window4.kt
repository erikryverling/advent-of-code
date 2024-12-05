package se.yverling.advent.windows._2022

import se.yverling.advent.Window

internal class Window4 : Window() {
    private val inputMatcher = Regex("(\\d*)-(\\d*),(\\d*)-(\\d*)")

    override fun part1(): String {
        var fullyContains = 0

        reader.forEachLine { line ->
            val groupValues = inputMatcher.find(line)?.groupValues

            val firstElfBounds = Pair(groupValues?.get(1)!!.toInt(), groupValues[2].toInt())
            val secondElfBounds = Pair(groupValues[3].toInt(), groupValues[4].toInt())

            // TODO Use range from kotlin?
            if ((firstElfBounds.first <= secondElfBounds.first && firstElfBounds.second >= secondElfBounds.second) ||
                (secondElfBounds.first <= firstElfBounds.first && secondElfBounds.second >= firstElfBounds.second)
            ) {
                fullyContains++
            }
        }

        return fullyContains.toString()
    }

    override fun part2(): String {
        var noOverlap = 0
        var totalLines = 0

        reader.forEachLine { line ->
            totalLines++

            val groupValues = inputMatcher.find(line)?.groupValues

            val firstElfBounds = Pair(groupValues?.get(1)!!.toInt(), groupValues[2].toInt())
            val secondElfBounds = Pair(groupValues[3].toInt(), groupValues[4].toInt())

            if ((firstElfBounds.second < secondElfBounds.first || firstElfBounds.first > secondElfBounds.second)) {
                noOverlap++
            }
        }
        return (totalLines - noOverlap).toString()
    }
}
