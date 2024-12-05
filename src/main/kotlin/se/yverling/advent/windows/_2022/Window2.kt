package se.yverling.advent.windows._2022

import se.yverling.advent.Window

internal class Window2 : Window() {

    // A / X = Rock
    // B / Y = Paper
    // C / Z = Scissors
    private val winningScore: Map<Pair<Char, Char>, Int> = mapOf(
        Pair('A', 'X') to 3,
        Pair('A', 'Y') to 6,
        Pair('A', 'Z') to 0,
        Pair('B', 'X') to 0,
        Pair('B', 'Y') to 3,
        Pair('B', 'Z') to 6,
        Pair('C', 'X') to 6,
        Pair('C', 'Y') to 0,
        Pair('C', 'Z') to 3
    )

    private val moveScore: Map<Char, Int> = mapOf('X' to 1, 'Y' to 2, 'Z' to 3)

    override fun part1(): String {
        var totalScore = 0

        reader.forEachLine { line ->
            val opponentMove = line[0]
            val myMove = line[2]

            val move = Pair(opponentMove, myMove)

            totalScore += winningScore[move]!! + moveScore[myMove]!!
        }
        return totalScore.toString()
    }

    override fun part2(): String {
        var totalScore = 0

        // A / X = Rock
        // B / Y = Paper
        // C / Z = Scissors

        // Column 2
        // X = Loose
        // Y = Draw
        // Z = Win

        val intentionToMove: Map<Pair<Char, Char>, Char> = mapOf(
            Pair('A', 'X') to 'Z',
            Pair('A', 'Y') to 'X',
            Pair('A', 'Z') to 'Y',
            Pair('B', 'X') to 'X',
            Pair('B', 'Y') to 'Y',
            Pair('B', 'Z') to 'Z',
            Pair('C', 'X') to 'Y',
            Pair('C', 'Y') to 'Z',
            Pair('C', 'Z') to 'X'
        )

        reader.forEachLine { line ->
            val opponentMove = line[0]
            val myIntention = line[2]
            val myMove = intentionToMove[Pair(opponentMove, myIntention)]

            val move = Pair(opponentMove, myMove)

            totalScore += winningScore[move]!! + moveScore[myMove]!!
        }
        return totalScore.toString()
    }
}
