package se.yverling.advent._2020

import se.yverling.advent.WindowFileReader

const val RESOURCE_FOLDER = "src/main/res/2020/"

fun main() {
    val reader = WindowFileReader(RESOURCE_FOLDER)

    Window1(reader).open()
    Window2(reader).open()
    Window3(reader).open()
}
