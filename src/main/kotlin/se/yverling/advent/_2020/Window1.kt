package se.yverling.advent._2020

import se.yverling.advent.Window

object Window1 : Window {
    override val windowNumber: Int = 1

    private var expenseReport = mutableListOf<Int>()

    init {
        readExpenseReport()
    }

    override fun part1() {
        expenseReport.forEachIndexed { outerIndex, outerElement ->
            expenseReport.subList(outerIndex + 1, expenseReport.size).forEach { innerElement ->
                if (outerElement + innerElement == 2020) {
                    val product = outerElement * innerElement
                    println("$outerElement * $innerElement == $product")
                    return
                }
            }
        }
    }

    override fun part2() {
        expenseReport.forEachIndexed { firstLevelIndex, firstLevelElement ->
            val secondLevelList = expenseReport.subList(firstLevelIndex + 1, expenseReport.size)

            secondLevelList.forEachIndexed { secondLevelIndex, secondLevelElement ->
                expenseReport.subList(secondLevelIndex + 1, secondLevelList.size).forEach { thirdLevelElement ->
                    if (firstLevelElement + secondLevelElement + thirdLevelElement == 2020) {
                        val product = firstLevelElement * secondLevelElement * thirdLevelElement
                        println("$firstLevelElement * $secondLevelElement * $thirdLevelElement == $product")
                        return
                    }
                }
            }
        }
    }

    private fun readExpenseReport() {
        reader.read(1).forEachLine { row ->
            expenseReport.add(row.toInt())
        }
    }
}
