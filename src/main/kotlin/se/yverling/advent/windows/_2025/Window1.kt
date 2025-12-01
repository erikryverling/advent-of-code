package se.yverling.advent.windows._2025

import se.yverling.advent.Window

internal class Window1 : Window() {
    private val lineMatcher = Regex("\\((\\w+)(\\d+)\\)")

    private var currentNumber = 50

    override fun part1() {
        reader.forEachLine(1) { line ->
            val match = lineMatcher.find(line)
            println(match!!.groups[1]!!.value)
            println(match!!.groups[2]!!.value)
        }
    }

    override fun part2()  {

    }
}
