package se.yverling.advent.windows._2022

import se.yverling.advent.Window

internal class Window3 : Window() {
    override fun part1(): String {
        var sumOfPriorities = 0
        reader.forEachLine { line ->
            val firstCompartment = (line.substring(0, (line.length / 2))).toSet()
            val secondCompartment = (line.substring((line.length / 2), line.length)).toSet()

            firstCompartment.forEach { item ->
                if (secondCompartment.contains(item)) {
                    sumOfPriorities += priority(item)
                }
            }
        }
        return sumOfPriorities.toString()
    }

    override fun part2(): String {
        var lineNumber = 0
        var sumOfPriorities = 0

        var group = mutableListOf<Set<Char>>()

        reader.forEachLine { line ->
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
        return sumOfPriorities.toString()
    }

    private fun priority(item: Char): Int {
        var priority = -1
        val ss = ALPHABET.plus(ALPHABET.uppercase())
        ss.forEachIndexed { index, letter ->
            if (letter == item) {
                priority = index + 1
                return@forEachIndexed
            }
        }
        return priority
    }

    companion object {
        const val ALPHABET = "abcdefghijklmnopqrstuvwxyz"
    }
}
