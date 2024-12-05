package se.yverling.advent.windows._2023

import se.yverling.advent.Window

internal class Window2 : Window() {
    private val gameMatcher = Regex("^Game (\\d+): (.*)\$")
    private val cubeMatcher = Regex("^(\\d+) (\\w+)$")

    override fun part1(): Any {
        return reader.sumOfLines { line ->
            val gameId = gameMatcher.find(line)!!.groupValues[1].toInt()

            if (allSetsValid(line.toSets())) gameId else 0
        }
    }

    override fun part2(): Any {
        return reader.sumOfLines { line ->
            powerOf(line.toSets())
        }
    }

    private fun allSetsValid(sets: List<String>): Boolean {
        return sets.all { set ->
            setValid(set)
        }
    }

    private fun setValid(set: String): Boolean {
        val cubes = set.toCubes()

        return cubes.all { cube ->
            cubeColorValid(cube.trim())
        }
    }

    private fun cubeColorValid(cube: String): Boolean {
        val number = cube.toNumber()

        val colorLimit = when (cube.toColor()) {
            "red" -> 12
            "green" -> 13
            "blue" -> 14
            else -> -1
        }

        return number <= colorLimit
    }

    private fun powerOf(sets: List<String>): Int {
        return maxColor(sets, "red") * maxColor(sets, "green") * maxColor(sets, "blue")
    }

    private fun maxColor(sets: List<String>, searchColor: String): Int {
        var maxNumber = 0
        sets.forEach { set ->
            set.toCubes().map { it.trim() }.forEach { cube ->
                val number = cube.toNumber()
                val color = cube.toColor()

                if (searchColor == color && number > maxNumber) {
                    maxNumber = number
                }
            }
        }
        return maxNumber
    }

    private fun String.toSets(): List<String> {
        val setsAsString = gameMatcher.find(this)!!.groupValues[2]
        return setsAsString.split(";")
    }

    private fun String.toColor() = cubeMatcher.find(this)!!.groupValues[2]

    private fun String.toNumber() = cubeMatcher.find(this)!!.groupValues[1].toInt()

    private fun String.toCubes() = this.split(",")
}
