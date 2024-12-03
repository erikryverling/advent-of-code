package se.yverling.advent

import kotlin.reflect.full.primaryConstructor

fun main() {
    //openCalendar(2020)
    //openCalendar(2022)
    //openCalendar(2023)
    openCalendar(2024, window = 1)
}

fun openCalendar(year: Int, window: Int? = null) {
    val reader = WindowFileReader(year)

    if (window != null) {
        openWindow(year, window, reader)
    } else {
        println()
        println("=== ADVENT CALENDAR $year ===")
        println()

        for (windowNumber in 1..24) {
            openWindow(year, windowNumber, reader)
        }
    }
}

private fun openWindow(year: Int, windowNumber: Int, reader: WindowFileReader) {
    try {
        val kClass = Class.forName("se.yverling.advent._$year.Window$windowNumber").kotlin
        val primaryConstructor = kClass.primaryConstructor
        val instance: Window = primaryConstructor!!.call(reader) as Window
        instance.open()
    } catch (e: ClassNotFoundException) {
        // Ignore widows not implemented yet
    }
}
