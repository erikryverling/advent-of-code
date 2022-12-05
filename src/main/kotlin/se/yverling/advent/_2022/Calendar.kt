package se.yverling.advent._2022

import se.yverling.advent.WindowFileReader

const val RESOURCE_FOLDER = "src/main/res/2022/"

fun main() {
    val reader = WindowFileReader(RESOURCE_FOLDER)

    // TODO Perhaps interate over all classes in _2022 folder?
    Window1(reader).open()
    Window2(reader).open()
    Window3(reader).open()
    Window4(reader).open()
    Window5(reader).open()
}
