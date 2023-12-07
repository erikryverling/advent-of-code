package se.yverling.advent._2023

import se.yverling.advent.Window
import se.yverling.advent.WindowFileReader

class Window1(reader: WindowFileReader) : Window(reader, 1) {
    override fun part1(): Any {
        return reader.readLines().sumOf { line ->
            val digits = line.filter { it.isDigit() }

            "${digits[0]}${digits[digits.lastIndex]}".toInt()
        }
    }

    override fun part2(): Any {
        return reader.readLines().sumOf { line ->
            val numericalDigits = (1..9).map { it.toString() }.toList()
            val textualDigits = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
            val digits = numericalDigits.plus(textualDigits)

            "${leftmostDigit(digits, line)}${rightmostDigit(digits, line)}".toInt()
        }
    }

    private fun leftmostDigit(digits: List<String>, line: String): String {
        var lowestIndex = Pair(line.lastIndex, "")
        digits.forEach { digit ->
            val index = line.indexOf(digit)
            if (index > -1 && index <= lowestIndex.first) {
                lowestIndex = Pair(index, digit)
            }
        }
        return toNumericalDigit(lowestIndex.second)
    }

    private fun rightmostDigit(digits: List<String>, line: String): String {
        var highestIndex = Pair(-1, "")
        digits.forEach { digit ->
            val index = line.lastIndexOf(digit)
            if (index > highestIndex.first) {
                highestIndex = Pair(index, digit)
            }
        }
        return toNumericalDigit(highestIndex.second)
    }

    private fun toNumericalDigit(digit: String): String {
        return when (digit) {
            "one" -> "1"
            "two" -> "2"
            "three" -> "3"
            "four" -> "4"
            "five" -> "5"
            "six" -> "6"
            "seven" -> "7"
            "eight" -> "8"
            "nine" -> "9"
            else -> digit
        }
    }
}