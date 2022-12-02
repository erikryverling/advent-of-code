package se.yverling.advent

abstract class Window(val reader: WindowFileReader) {
    abstract val windowNumber: Int

    abstract fun part1(): Any
    abstract fun part2(): Any

    fun open() {
        println("*** Window $windowNumber ***")
        println(part1())
        println("---")
        println(part2())
        println("")
    }
}
