package se.yverling.advent

abstract class Window(val reader: WindowFileReader, private val windowNumber: Int) {
    init {
        reader.windowNumber = windowNumber
    }

    abstract fun part1(): String
    abstract fun part2(): String

    fun open() {
        println("*** Window $windowNumber ***")
        println(part1())
        println("---")
        println(part2())
        println("")
    }
}
