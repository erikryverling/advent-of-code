package se.yverling.advent

import java.io.File

class WindowFileReader(year: Int) {
    private val resourceFolder = "src/main/res/$year"
    var windowNumber: Int = -1

    fun file(test: Int = 0): File {
        return if (test == 1) File("$resourceFolder/window-$windowNumber-test.txt")
        else File("$resourceFolder/window-$windowNumber.txt")
    }

    fun readLines(test: Int = 0): List<String> = file(test).readLines()
}