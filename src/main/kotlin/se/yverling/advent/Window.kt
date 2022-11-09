package se.yverling.advent

interface Window {
    val windowNumber: Int

    fun part1()
    fun part2()

    operator fun invoke() {
        println("*** Window $windowNumber ***")
        part1()
        println("---")
        part2()
        println("")
    }
}