package se.yverling.advent.windows._2025

import se.yverling.advent.Window

internal class Window1 : Window() {
    override fun part1(): Int {
        var currentNumber = 50
        var clicks = 0

        reader.forEachLine() { line ->
            val direction = line.take(1)
            val number = line.substring(1).toInt()

            when (direction) {
                "L" -> {
                    val positionBackward = (currentNumber - number) % 100
                    currentNumber = if (positionBackward < 0) positionBackward + 100
                    else positionBackward
                }

                "R" -> {
                    val positionForward = (currentNumber + number) % 100
                    currentNumber = positionForward
                }
            }

            if (currentNumber == 0) clicks++
        }

        return clicks
    }

    override fun part2() {
        // TODO
    }
}
