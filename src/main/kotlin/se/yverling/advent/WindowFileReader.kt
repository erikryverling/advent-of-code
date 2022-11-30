package se.yverling.advent

import java.io.File

class WindowFileReader(private val resourceFolder: String) {
    fun read(windowNumber: Int): File {
        return File("$resourceFolder/window-$windowNumber")
    }
}
