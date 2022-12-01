package se.yverling.advent

import java.io.File

class WindowFileReader(private val resourceFolder: String, private val windowNumber: Int) {
    fun read(): File {
        return File("$resourceFolder/window-$windowNumber")
    }

    fun readTest(): File {
        return File("$resourceFolder/window-$windowNumber-test")
    }
}
