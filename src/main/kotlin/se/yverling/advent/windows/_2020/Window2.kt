package se.yverling.advent.windows._2020

import se.yverling.advent.Window

internal class Window2 : Window() {
    private var numberOfCorrectPasswords = 0
    private var dataBase = mutableListOf<String>()

    override fun setUp() {
        readDatabase()
    }

    override fun part1(): String {
        dataBase.forEach { entry ->
            // TODO Use regexp?
            val lowerLimit = entry.substringBefore('-').toInt()
            val upperLimit = entry.substringAfter('-').substringBefore(' ').toInt()
            val character = entry.substringAfter(' ').substringBefore(':').single()
            val password = entry.substringAfterLast(' ')

            val charactersInPassword = password.filter { it == character }.length

            if (charactersInPassword in lowerLimit..upperLimit) {
                numberOfCorrectPasswords++
            }
        }
        return "Number of correct passwords: $numberOfCorrectPasswords"
    }

    override fun part2(): String {
        numberOfCorrectPasswords = 0

        dataBase.forEach { entry ->
            // TODO Use regexp?
            val lowerPosition = entry.substringBefore('-').toInt()
            val upperPosition = entry.substringAfter('-').substringBefore(' ').toInt()
            val character = entry.substringAfter(' ').substringBefore(':').single()
            val password = entry.substringAfterLast(' ')

            if (password[lowerPosition - 1] == character && password[upperPosition - 1] != character ||
                password[lowerPosition - 1] != character && password[upperPosition - 1] == character
            ) {
                numberOfCorrectPasswords++
            }
        }
        return "Number of correct passwords: $numberOfCorrectPasswords"
    }

    private fun readDatabase() {
        reader.forEachLine { row ->
            dataBase.add(row)
        }
    }
}
