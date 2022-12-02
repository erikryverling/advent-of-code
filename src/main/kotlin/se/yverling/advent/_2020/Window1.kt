package se.yverling.advent._2020

import se.yverling.advent.Window
import se.yverling.advent.WindowFileReader

class Window1(reader: WindowFileReader) : Window(reader) {
    override val windowNumber: Int = 1

    private var expenseReport = mutableListOf<Int>()

    init {
        readExpenseReport()
    }

    override fun part1(): String {
        expenseReport.forEachIndexed { outerIndex, outerElement ->
            expenseReport.subList(outerIndex + 1, expenseReport.size).forEach { innerElement ->
                if (outerElement + innerElement == 2020) {
                    val product = outerElement * innerElement
                    return "$outerElement * $innerElement == $product"
                }
            }
        }
        return "Error: Something went wrong"
    }

    override fun part2(): String {
        expenseReport.forEachIndexed { firstLevelIndex, firstLevelElement ->
            val secondLevelList = expenseReport.subList(firstLevelIndex + 1, expenseReport.size)

            secondLevelList.forEachIndexed { secondLevelIndex, secondLevelElement ->
                expenseReport.subList(secondLevelIndex + 1, secondLevelList.size).forEach { thirdLevelElement ->
                    if (firstLevelElement + secondLevelElement + thirdLevelElement == 2020) {
                        val product = firstLevelElement * secondLevelElement * thirdLevelElement
                        return "$firstLevelElement * $secondLevelElement * $thirdLevelElement == $product"
                    }
                }
            }
        }
        return "Error: Something went wrong"
    }

    private fun readExpenseReport() {
        reader.read().forEachLine { row ->
            expenseReport.add(row.toInt())
        }
    }
}
