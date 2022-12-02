package se.yverling.advent._2022

import se.yverling.advent.WindowFileReader

const val RESOURCE_FOLDER = "src/main/res/2022/"

fun main() {
    Window1(WindowFileReader(RESOURCE_FOLDER, 1)).open()
    Window2(WindowFileReader(RESOURCE_FOLDER, 2)).open()
}
