package se.yverling.advent._2022

import se.yverling.advent.Window
import se.yverling.advent.WindowFileReader

private const val CRT_WIDTH = 40
private const val CRT_HEIGHT = 6

class Window10(reader: WindowFileReader) : Window(reader, 10) {
    private val inputMatcher = Regex("^(\\w+)\\s*(-*\\d*)$")
    private var clock = 1
    private var registerX = 1
    private var signalStrengths = mutableListOf<Int>()
    private val pixels = mutableListOf<Char>()

    override fun part1(): String {
        reader.file().forEachLine { line ->
            when (inputMatcher.find(line)?.groupValues!![1]) {
                "addx" -> {
                    val value = inputMatcher.find(line)?.groupValues!![2].toInt()

                    tickClock(2)

                    registerX += value
                }

                "noop" -> {
                    tickClock(1)
                }
            }
        }

        return signalStrengths.sum().toString()
    }

    override fun part2(): String {
        render(pixels)

        return ""
    }

    private fun tickClock(ticks: Int) {
        for (tick in clock + 1..clock + ticks) {
            if (inSignalStrengthsMessurePoints()) {
                signalStrengths.add(registerX * clock)
            }
            drawPixel()
            clock++
        }
    }

    private fun inSignalStrengthsMessurePoints() = listOf(20, 60, 100, 140, 180, 220).any { it == clock }

    private fun drawPixel() {
        val currentPositionOnScreen = clock % 240
        val currentPositionOnRow = (currentPositionOnScreen - 1) % CRT_WIDTH

        if (inSprite(currentPositionOnRow)) pixels.add('#')
        else pixels.add('.')
    }

    private fun inSprite(currentPositionOnRow: Int) = (currentPositionOnRow - registerX) in -1..1

    private fun render(pixels: MutableList<Char>) {
        for (y in 0 until CRT_HEIGHT) {
            for (x in 0 until CRT_WIDTH) {
                print(pixels[x + (y * CRT_WIDTH)])
            }
            println()
        }
    }
}
