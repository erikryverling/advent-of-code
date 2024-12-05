package se.yverling.advent.windows._2022

import se.yverling.advent.Window

internal class Window1 : Window() {
    override fun part1(): String {
        var maxNumberOfCalories = 0
        var currentSum = 0

        reader.forEachLine { line ->
            if (line.isEmpty()) {
                if (currentSum > maxNumberOfCalories) {
                    maxNumberOfCalories = currentSum
                }
                currentSum = 0
            } else {
                currentSum += line.toInt()
            }
        }

        return maxNumberOfCalories.toString()
    }

    override fun part2(): String {
        var topThreeNumberOfCalories = mutableListOf<Int>()
        var currentSum = 0

        reader.forEachLine { line ->
            if (line.isEmpty()) {
                topThreeNumberOfCalories = updateList(topThreeNumberOfCalories, currentSum)
                currentSum = 0
            } else {
                currentSum += line.toInt()
            }
        }

        topThreeNumberOfCalories = updateList(topThreeNumberOfCalories, currentSum)

        return topThreeNumberOfCalories.sum().toString()
    }

    private fun updateList(
        topThreeNumberOfCalories: MutableList<Int>,
        currentSum: Int
    ): MutableList<Int> {
        if (topThreeNumberOfCalories.size < 3) {
            topThreeNumberOfCalories.add(currentSum)
        } else if (topThreeNumberOfCalories.size == 3 && topThreeNumberOfCalories[0] < currentSum) {
            return mutableListOf(
                topThreeNumberOfCalories[1],
                topThreeNumberOfCalories[2],
                currentSum
            )
                .sorted()
                .toMutableList()
        }
        return topThreeNumberOfCalories
    }
}
