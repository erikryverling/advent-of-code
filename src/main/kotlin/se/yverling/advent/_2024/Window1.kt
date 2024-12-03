package se.yverling.advent._2024

import se.yverling.advent.Window
import se.yverling.advent.WindowFileReader
import kotlin.math.abs

class Window1(reader: WindowFileReader) : Window(reader, 1) {
    private val firstList = mutableListOf<Int>()
    private val secondList = mutableListOf<Int>()

    override fun part1(): Int {
        reader.file().forEachLine { line ->
            firstList.add(line.substringBefore(DELIMITER).toInt())
            secondList.add(line.substringAfter(DELIMITER).toInt())
        }

        firstList.sort()
        secondList.sort()

        return firstList.zip(secondList) { firstListItem, secondListItem ->
            abs(firstListItem - secondListItem)
        }.sum()
    }

    override fun part2() = firstList.sumOf { firstListItem ->
        firstListItem * secondList.count { it == firstListItem }
    }

    companion object {
        private const val DELIMITER = "   "
    }
}
