package se.yverling.advent._2022

import se.yverling.advent.Window
import se.yverling.advent.WindowFileReader

class Window3(reader: WindowFileReader) : Window(reader, 3) {
    override fun part1(): Int {
        var sumOfPriorities = 0
        reader.read().forEachLine { line ->
            val firstCompartment = (line.substring(0, (line.length / 2))).toSet()
            val secondCompartment = (line.substring((line.length / 2), line.length)).toSet()

            firstCompartment.forEach { item ->
                if (secondCompartment.contains(item)) {
                    sumOfPriorities += priority(item)
                }
            }
        }
        return sumOfPriorities
    }

    override fun part2(): Int {
        var lineNumber = 0
        var sumOfPriorities = 0

        var group = mutableListOf<Set<Char>>()

        reader.read().forEachLine { line ->
            lineNumber++

            group.add(line.toSet())

            if (lineNumber % 3 == 0) {
                group[0].forEach { item ->
                    if (group[1].contains(item) && group[2].contains(item)) {
                        sumOfPriorities += priority(item)
                    }

                }
                group = mutableListOf()
            }
        }
        return sumOfPriorities
    }

    private fun priority(item: Char): Int {
        val alphabet = "abcdefghijklmnopqrstuvwxyz"
        var priority = -1
        val ss = alphabet.plus(alphabet.uppercase())
        ss.forEachIndexed { index, letter ->
            if (letter == item) {
                priority = index + 1
                return@forEachIndexed
            }
        }
        return priority
    }
}
