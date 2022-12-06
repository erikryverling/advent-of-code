package se.yverling.advent

import java.io.File

class WindowFileReader(year: Int) {
    private val resourceFolder = "src/main/res/$year"
    var windowNumber: Int = -1

    fun read(test: Boolean = false): File {
        return if (test) File("$resourceFolder/window-$windowNumber-test.txt")
        else File("$resourceFolder/window-$windowNumber.txt")
    }
}
