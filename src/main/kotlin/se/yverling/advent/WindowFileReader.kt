package se.yverling.advent

import java.io.File

class WindowFileReader(year: Int, val windowNumber: Int) {
    private val resourceFolder = "src/main/res/windows/$year"

    fun file(test: Int = 0): File =
        if (test == 1) File("$resourceFolder/window-$windowNumber-test.txt")
        else File("$resourceFolder/window-$windowNumber.txt")

    fun forEachLine(test: Int = 0, forEachLine: (String) -> Unit) = file(test).forEachLine { forEachLine(it) }

    fun forEachLineIndexed(test: Int = 0, forEachLineIndexed: (index: Int, line: String) -> Unit) {
        var index = 0
        file(test).forEachLine { forEachLineIndexed.invoke(index++, it) }
    }

    fun sumOfLines(test: Int = 0, sumOf: (String) -> Int) = file(test).readLines().sumOf { sumOf(it) }

    companion object {
        val EMPTY = WindowFileReader(-1, -1)
    }
}
