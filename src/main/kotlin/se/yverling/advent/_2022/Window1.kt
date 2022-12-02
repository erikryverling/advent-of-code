package se.yverling.advent._2022

import se.yverling.advent.Window
import se.yverling.advent.WindowFileReader

class Window1(reader: WindowFileReader) : Window(reader) {
    override val windowNumber: Int = 1

    override fun part1(): Int {
        var maxNumberOfCalories = 0
        var currentSum = 0

        reader.read().forEachLine { line ->
            if (line.isEmpty()) {
                if (currentSum > maxNumberOfCalories) {
                    maxNumberOfCalories = currentSum
                }
                currentSum = 0
            } else {
                currentSum += line.toInt()
            }
        }

        return maxNumberOfCalories
    }

    override fun part2(): Int {
        var topThreeNumberOfCalories = mutableListOf<Int>()
        var currentSum = 0

        reader.read().forEachLine { line ->
            if (line.isEmpty()) {
                topThreeNumberOfCalories = updateList(topThreeNumberOfCalories, currentSum)
                currentSum = 0
            } else {
                currentSum += line.toInt()
            }
        }

        topThreeNumberOfCalories = updateList(topThreeNumberOfCalories, currentSum)

        return topThreeNumberOfCalories.sum()
    }

    private fun updateList(
        topThreeNumberOfCalories: MutableList<Int>,
        currentSum: Int
    ): MutableList<Int> {
        if (topThreeNumberOfCalories.size < 3) {
            topThreeNumberOfCalories.add(currentSum)
        } else if (topThreeNumberOfCalories.size == 3 && topThreeNumberOfCalories[0] < currentSum) {
            return mutableListOf(topThreeNumberOfCalories[1], topThreeNumberOfCalories[2], currentSum)
                .sorted()
                .toMutableList()
        }
        return topThreeNumberOfCalories
    }
}
