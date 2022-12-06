package se.yverling.advent

import kotlin.reflect.full.primaryConstructor

fun openCalendar(year: Int) {
    val reader = WindowFileReader(year)
    for (window in 1..24) {
        try {
            val kClass = Class.forName("se.yverling.advent._$year.Window$window").kotlin
            val primaryConstructor = kClass.primaryConstructor
            val instance: Window = primaryConstructor!!.call(reader) as Window
            instance.open()
        } catch (e: ClassNotFoundException) {
            // Ignore widows not implemented yet
        }
    }
}
