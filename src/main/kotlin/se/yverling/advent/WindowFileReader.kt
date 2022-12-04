package se.yverling.advent

import java.io.File

class WindowFileReader(private val resourceFolder: String) {
    var windowNumber: Int = -1

    fun read(test: Boolean = false): File {
        return if (test) File("$resourceFolder/window-$windowNumber-test")
        else File("$resourceFolder/window-$windowNumber")
    }
}
