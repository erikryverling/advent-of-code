package se.yverling.advent

abstract class Window(val reader: WindowFileReader) {
    abstract val windowNumber: Int

    abstract fun part1()
    abstract fun part2()

    fun open() {
        println("*** Window $windowNumber ***")
        part1()
        println("---")
        part2()
        println("")
    }
}
