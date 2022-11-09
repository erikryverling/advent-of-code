package se.yverling.advent._2020

import se.yverling.advent.Window
import java.io.File
import kotlin.math.ceil

object Window3 : Window {
    override val windowNumber = 3

    private const val MAX_NUMBER_OF_STEPS = 7

    // Map meta data
    private var numberOfRows: Int = -1
    private var rowLength: Int = -1
    private var repetitions: Int = -1

    private val map = mutableMapOf<Pair<Int, Int>, Char>()

    init {
        initMapMetaData()
        buildMap()
    }

    override fun part1() {
        println("Number of trees: ${runSlope(3, 1)}")
    }

    override fun part2() {
        var product = runSlope(1, 1)
        product *= runSlope(3, 1)
        product *= runSlope(5, 1)
        product *= runSlope(7, 1)
        product *= runSlope(1, 2)

        println("Product: $product")
    }

    private fun initMapMetaData() {
        var currentNumberOfRows = 0

        File("$RES_FOLDER/window-3").forEachLine { row ->
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
        File("$RES_FOLDER/window-3").forEachLine { row ->
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
}