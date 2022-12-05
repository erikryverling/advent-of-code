package se.yverling.advent

import java.io.File

class WindowFileReader(private val resourceFolder: String) {
    var windowNumber: Int = -1

    fun read(test: Boolean = false): File {
        return if (test) File("$resourceFolder/window-$windowNumber-test.txt")
        else File("$resourceFolder/window-$windowNumber.txt")
    }
}
