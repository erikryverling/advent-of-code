package se.yverling.advent.windows._2020

import se.yverling.advent.Window
import kotlin.math.ceil

internal class Window3 : Window() {
    // Map meta data
    private var numberOfRows: Int = -1
    private var rowLength: Int = -1
    private var repetitions: Int = -1

    private val map = mutableMapOf<Pair<Int, Int>, Char>()

    override fun setUp() {
        initMapMetaData()
        buildMap()
    }

    override fun part1(): String {
        return "Number of trees: ${runSlope(3, 1)}"
    }

    override fun part2(): String {
        var product = runSlope(1, 1)
        product *= runSlope(3, 1)
        product *= runSlope(5, 1)
        product *= runSlope(7, 1)
        product *= runSlope(1, 2)

        return "Product: $product"
    }

    private fun initMapMetaData() {
        var currentNumberOfRows = 0

        reader.forEachLine { row ->
            if (currentNumberOfRows == 0) {
                rowLength = row.length
            }
            currentNumberOfRows++
        }

        numberOfRows = currentNumberOfRows

        // Round up
        repetitions = ceil((MAX_NUMBER_OF_STEPS.toFloat() * numberOfRows.toFloat()) / rowLength.toFloat()).toInt()
    }

    private fun buildMap() {
        var fileRow = 0
        reader.forEachLine { row ->
            for (offset in 0 until rowLength * repetitions step rowLength) {
                row.forEachIndexed { fileColumn, symbol ->
                    map[Pair(offset + fileColumn, fileRow)] = symbol
                }
            }
            fileRow++
        }
    }

    private fun runSlope(rightMoves: Int, downMoves: Int): Int {
        var treesEncountered = 0
        var x = rightMoves
        for (y in downMoves..numberOfRows step downMoves) {
            if (map[Pair(x, y)] == '#') {
                treesEncountered++
            }
            x += rightMoves
        }
        return treesEncountered
    }

    companion object {
        const val MAX_NUMBER_OF_STEPS = 7
    }
}
