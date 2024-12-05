package se.yverling.advent

abstract class Window {
    var reader: WindowFileReader = WindowFileReader.EMPTY

    protected open fun setUp() {}

    abstract fun part1(): Any
    abstract fun part2(): Any

    fun open() {
        setUp()

        println("*** Window ${reader.windowNumber} ***")
        println(part1())
        println("---")
        println(part2())
        println("")
    }
}
