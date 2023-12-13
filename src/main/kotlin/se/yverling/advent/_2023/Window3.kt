package se.yverling.advent._2023

import se.yverling.advent.Window
import se.yverling.advent.WindowFileReader

class Window3(reader: WindowFileReader) : Window(reader, 3) {
    private val symbols = mutableMapOf<Pair<Int, Int>, String>()
    private val numbers = mutableListOf<List<Pair<Pair<Int, Int>, String>>>()

    init {
        initSymbolsAndNumbers(reader)
    }

    override fun part1(): Any {
        val adjacentNumbers = mutableListOf<List<Pair<Pair<Int, Int>, String>>>()
        numbers.forEach { number ->
            run digitLoop@{
                number.forEach { digit ->
                    if (isAdjacentToAnySymbol(digit)) {
                        adjacentNumbers.add(number)
                        return@digitLoop
                    }
                }
            }
        }
        return adjacentNumbers.sumOf { it.asInt }
    }

    override fun part2(): Any {
        val gears = symbols.filter { it.value == "*" }.toList()

        return gears.sumOf { gear ->
            getRatio(gear)
        }
    }

    private fun initSymbolsAndNumbers(reader: WindowFileReader) {
        val symbolMatcher = Regex("[^.\\d]")
        val digitMatcher = Regex("\\d")

        var x: Int
        var y = 0

        reader.file().forEachLine { line ->
            x = 0
            var currentNumber = mutableListOf<Pair<Pair<Int, Int>, String>>()
            var previousCharacter = ""
            line.forEachIndexed { index, character ->
                val currentPosition = character.toString()

                if (symbolMatcher.containsMatchIn(currentPosition)) {
                    symbols[Pair(x, y)] = symbolMatcher.find(currentPosition)!!.value
                } else if (digitMatcher.containsMatchIn(currentPosition)) {
                    currentNumber.add(Pair(Pair(x, y), digitMatcher.find(currentPosition)!!.value))
                }

                // End of number, but not end of line
                if (digitMatcher.containsMatchIn(previousCharacter) && !digitMatcher.containsMatchIn(currentPosition)) {
                    numbers.add(currentNumber)
                    currentNumber = mutableListOf()
                    // End of line
                } else if (line.lastIndex == index) {
                    numbers.add(currentNumber)
                }

                x++
                previousCharacter = currentPosition
            }
            y++
        }
    }

    private val List<Pair<Pair<Int, Int>, String>>.asInt: Int
        get() {
            return this.joinToString(separator = "") { it.second }.toInt()
        }

    private fun isAdjacentToAnySymbol(digit: Pair<Pair<Int, Int>, String>): Boolean {
        val digitX = digit.first.first
        val digitY = digit.first.second

        for (y in -1..1) {
            for (x in -1..1) {
                if (symbols[Pair(digitX + x, digitY + y)] != null) {
                    return true
                }
            }
        }

        return false
    }

    private fun isAdjacentToSymbol(digit: Pair<Pair<Int, Int>, String>, symbol: Pair<Pair<Int, Int>, String>): Boolean {
        val symbolX = symbol.first.first
        val symbolY = symbol.first.second

        for (y in -1..1) {
            for (x in -1..1) {
                if (digit.first.first == symbolX + x && digit.first.second == symbolY + y) {
                    return true
                }
            }
        }
        return false
    }

    private fun getRatio(gear: Pair<Pair<Int, Int>, String>): Int {
        val adjacentNumbers = mutableListOf<List<Pair<Pair<Int, Int>, String>>>()

        numbers.forEach { number ->
            run digitLoop@{
                number.forEach { digit ->
                    if (isAdjacentToSymbol(digit, gear)) {
                        adjacentNumbers.add(number)
                        return@digitLoop
                    }
                }
            }
        }

        if (adjacentNumbers.size == 2) {
            return adjacentNumbers[0].asInt * adjacentNumbers[1].asInt
        }

        return 0
    }
}