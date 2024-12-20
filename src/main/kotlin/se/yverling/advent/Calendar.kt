package se.yverling.advent

import kotlin.reflect.full.createInstance

fun main() {
    //openCalendar(2020)
    //openCalendar(2022)
    //openCalendar(2023)
    openCalendar(2024, 3)
}

private fun openCalendar(year: Int, window: Int? = null) {
    if (window != null) {
        openWindow(year, window)
    } else {
        println()
        println("=== ADVENT CALENDAR $year ===")
        println()

        for (windowNumber in 1..24) {
            openWindow(year, windowNumber)
        }
    }
}

private fun openWindow(year: Int, windowNumber: Int) {
    try {
        val kClass = Class.forName("se.yverling.advent.windows._$year.Window$windowNumber").kotlin
        val instance: Window = kClass.createInstance() as Window
        instance.reader = WindowFileReader(year, windowNumber)
        instance.open()
    } catch (e: ClassNotFoundException) {
        // Ignore widows not implemented yet
    }
}
