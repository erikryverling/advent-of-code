package se.yverling.advent.windows._2024

import se.yverling.advent.Window
import kotlin.math.abs

internal class Window2 : Window() {
    override fun part1(): Any {
        var safeLists = 0

        reader.forEachLine { line ->
            val levels: List<Int> = line.toIntList()
            if (levels.isSafe()) safeLists++
        }

        return safeLists
    }

    override fun part2(): Any {
        var safeLists = 0

        reader.forEachLine { line ->
            val levels: List<Int> = line.toIntList()

            if (levels.isSafe()) {
                safeLists++
            } else {
                levels.forEachIndexed { index, _ ->
                    val dampenedList = levels.minusIndex(index)
                    if (dampenedList.isSafe()) {
                        safeLists++
                        return@forEachLine
                    }
                }
            }
        }

        return safeLists
    }
}

private fun String.toIntList() = split(' ').map { it.toInt() }

private fun List<Int>.isSafe(): Boolean {
    forEachIndexed { index, currentLevel ->
        if (index > 0) {
            val previousLevel = this[index - 1]

            val diff = abs(previousLevel - currentLevel)
            if (diff > 3 || diff < 1) {
                return false
            }

            if (index > 1) {
                val previousPreviousLevel = this[index - 2]

                val increasingToDecreasing =
                    previousLevel - currentLevel > 0 && previousPreviousLevel - previousLevel < 0
                val decreasingToIncreasing =
                    previousLevel - currentLevel < 0 && previousPreviousLevel - previousLevel > 0

                if (increasingToDecreasing || decreasingToIncreasing) return false
            }
        }
    }
    return true
}

private fun List<Int>.minusIndex(index: Int): List<Int> {
    val firstPartOfSplitList = toMutableList().subList(0, index)
    val secondPartOfSplitLift = toMutableList().subList(index + 1, size)

    return if (index == lastIndex) firstPartOfSplitList
    else firstPartOfSplitList + secondPartOfSplitLift
}
