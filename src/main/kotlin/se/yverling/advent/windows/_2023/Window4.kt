package se.yverling.advent.windows._2023

import se.yverling.advent.Window

internal class Window4 : Window() {
    override fun part1(): Any {
        return reader.sumOfLines { line ->
            cardValue(line)
        }
    }

    override fun part2(): Any {
        val initialCardDeck = mutableMapOf<Int, Int>()

        reader.forEachLineIndexed { index, line ->
            initialCardDeck[index + 1] = cardWins(line)
        }

        val finalCardDeck = mutableListOf<Int>()

        copy(initialCardDeck, finalCardDeck)

        initialCardDeck.forEach { initialCard ->
            val copies = mutableListOf<Int>()

            finalCardDeck.forEach { cardNumber ->
                // Make copies for all instances of initialCard.key in finalCardDeck
                if (cardNumber == initialCard.key) {
                    for (nextCardInInitialDeck in 1..initialCardDeck[initialCard.key]!!) {
                        copies.add(initialCard.key + nextCardInInitialDeck)
                    }
                }
            }

            finalCardDeck.addAll(copies)
        }

        return finalCardDeck.size
    }

    private fun cardValue(line: String): Int {
        var multiplier = 0
        cardMatcher(line) {
            if (multiplier == 0) multiplier = 1 else multiplier *= 2
        }
        return multiplier
    }

    private fun cardWins(line: String): Int {
        var wins = 0
        cardMatcher(line) {
            wins++
        }
        return wins
    }

    private fun cardMatcher(line: String, onMatch: () -> Unit) {
        val cardMatcher = Regex("^Card\\s+\\d+: ([^|]+)\\|(.+)\$")
        val winningNumbers = cardMatcher.find(line)!!.groupValues[1].trim().split(Regex("\\s+"))
        val bettingNumbers = cardMatcher.find(line)!!.groupValues[2].trim().split(Regex("\\s+"))

        bettingNumbers.forEach { number ->
            if (winningNumbers.contains(number)) {
                onMatch()
            }
        }
    }

    private fun copy(initialCardDeck: MutableMap<Int, Int>, finalCardDeck: MutableList<Int>) {
        initialCardDeck.forEach {
            finalCardDeck.add(it.key)
        }
    }
}
