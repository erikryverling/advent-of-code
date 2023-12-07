package se.yverling.advent._2023

import se.yverling.advent.Window
import se.yverling.advent.WindowFileReader

class Window2(reader: WindowFileReader) : Window(reader, 2) {
    override fun part1(): Any {
        return reader.readLines().sumOf { line ->
            val matcher = Regex("^Game (\\d+): (.*)\$")

            val gameId = matcher.find(line)!!.groupValues[1].toInt()

            val setsAsString = matcher.find(line)!!.groupValues[2]
            val sets = setsAsString.split(";")

            if (allSetsValid(sets)) gameId else 0
        }
    }

    override fun part2(): String {
        reader.file(1).forEachLine { line ->

        }
        return "<Not implemented>"
    }


    private fun allSetsValid(sets: List<String>): Boolean {
        return sets.all { set ->
            setValid(set)
        }
    }

    private fun setValid(set: String): Boolean {
        val cubeColors = set.split(",")

        return cubeColors.all { cubeColor ->
            cubeColorValid(cubeColor.trim())
        }
    }

    private fun cubeColorValid(cubeColor: String): Boolean {
        val matcher = Regex("^(\\d+) (\\w+)$")

        val number = matcher.find(cubeColor)!!.groupValues[1].toInt()
        val color = matcher.find(cubeColor)!!.groupValues[2]

        val colorLimit = when (color) {
            "red" -> 12
            "green" -> 13
            "blue" -> 14
            else -> -1
        }

        return number <= colorLimit
    }
}
