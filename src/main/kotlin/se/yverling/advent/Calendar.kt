package se.yverling.advent

import java.util.*
import kotlin.reflect.full.primaryConstructor

internal const val DECEMBER = 11

fun main() {
    openCalendar(2022, todayOnly = true)
}

fun openCalendar(year: Int, todayOnly: Boolean = false) {
    val reader = WindowFileReader(year)
    if (todayOnly) {
        val calendar = Calendar.getInstance()

        val dayOfMonth = calendar[Calendar.DAY_OF_MONTH]

        if (calendar[Calendar.MONTH] != DECEMBER || dayOfMonth > 25) {
            throw IllegalStateException("*** Sorry! todayOnly only works during Christmas ***")
        }

        val kClass = Class.forName("se.yverling.advent._$year.Window$dayOfMonth").kotlin
        val primaryConstructor = kClass.primaryConstructor
        val instance: Window = primaryConstructor!!.call(reader) as Window
        instance.open()
    } else {
        println()
        println("=== ADVENT CALENDAR $year ===")
        println()

        for (windowNumber in 1..24) {
            try {
                val kClass = Class.forName("se.yverling.advent._$year.Window$windowNumber").kotlin
                val primaryConstructor = kClass.primaryConstructor
                val instance: Window = primaryConstructor!!.call(reader) as Window
                instance.open()
            } catch (e: ClassNotFoundException) {
                // Ignore widows not implemented yet
            }
        }
    }
}
