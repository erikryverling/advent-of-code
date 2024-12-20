package se.yverling.advent.windows._2024

import se.yverling.advent.Window
import se.yverling.advent.utils.groupValueAsInt

internal class Window3() : Window() {
    private val part1Matcher = Regex("mul\\((\\d+),(\\d+)\\)")
    private val part2Matcher = Regex("mul\\((\\d+),(\\d+)\\)|don't|do")

    override fun part1(): Int {
        return reader.sumOfLines { line ->
            part1Matcher.findAll(line).sumOf { matchResult ->
                val firstProduct = matchResult.groupValueAsInt(1)
                val secondProduct = matchResult.groupValueAsInt(2)

                firstProduct * secondProduct
            }
        }
    }

    override fun part2(): Int {
        return reader.sumOfLines { line ->
            var sum = 0
            var enabled = true

            part2Matcher.findAll(line).forEach { matchResult ->
                when (matchResult.value) {
                    "do" -> enabled = true
                    "don't" -> enabled = false
                    else -> {
                        if (enabled) {
                            val firstProduct = matchResult.groupValueAsInt(1)
                            val secondProduct = matchResult.groupValueAsInt(1)

                            sum += firstProduct * secondProduct
                        }
                    }
                }
            }
            sum
        }
    }
}
