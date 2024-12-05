package se.yverling.advent.windows._2022

import java.util.*
import se.yverling.advent.Window

internal class Window5 : Window() {
    private val inputMatcher = Regex("move (\\d*) from (\\d) to (\\d)")
    // TODO Parse initial state of stacks? :fist:

    // Test
    /*  private val stacksInput = listOf(
          listOf('N', 'Z'),
          listOf('D', 'C', 'M'),
          listOf('P')
      )*/

    private val stacksInput = listOf(
        listOf('Z', 'V', 'T', 'B', 'J', 'G', 'R'),
        listOf('L', 'V', 'R', 'J'),
        listOf('F', 'Q', 'S'),
        listOf('G', 'Q', 'V', 'F', 'L', 'N', 'H', 'Z'),
        listOf('W', 'M', 'S', 'C', 'J', 'T', 'Q', 'R'),
        listOf('F', 'H', 'C', 'T', 'W', 'S'),
        listOf('J', 'N', 'F', 'V', 'C', 'Z', 'D'),
        listOf('Q', 'F', 'R', 'W', 'D', 'Z', 'G', 'L'),
        listOf('P', 'V', 'W', 'B', 'J')
    )

    override fun part1(): String {
        val stacks = copyInputStack()

        reader.forEachLine { line ->
            val groupValues = inputMatcher.find(line)?.groupValues

            // On a move row
            if (groupValues != null) {
                val numberOfMoves = groupValues[1].toInt()
                val fromStack = groupValues[2].toInt()
                val toStack = groupValues[3].toInt()

                for (i in 0 until numberOfMoves) {
                    val crateToBeMoved = stacks[fromStack - 1].remove()
                    stacks[toStack - 1].addFirst(crateToBeMoved)
                }
            }
        }

        return buildString { stacks.forEach { append(it.peekFirst()) } }
    }

    override fun part2(): String {
        val stacks = copyInputStack()

        reader.forEachLine { line ->
            val groupValues = inputMatcher.find(line)?.groupValues

            // TODO Duplicates
            // On a move row
            if (groupValues != null) {
                val numberOfMoves = groupValues[1].toInt()
                val fromStack = groupValues[2].toInt()
                val toStack = groupValues[3].toInt()

                // TODO Maybe there's a smarter way?
                val cratesToBeMoved = LinkedList<Char>()
                for (i in 0 until numberOfMoves) {
                    val crateToBeMoved = stacks[fromStack - 1].remove()
                    cratesToBeMoved.addFirst(crateToBeMoved)
                }

                cratesToBeMoved.forEach {
                    stacks[toStack - 1].addFirst(it)
                }
            }
        }
        return buildString { stacks.forEach { append(it.peekFirst()) } }
    }

    private fun copyInputStack(): MutableList<LinkedList<Char>> {
        val stacks = mutableListOf<LinkedList<Char>>()
        stacksInput.forEachIndexed { index, _ ->
            stacks.add(LinkedList(stacksInput[index].toList()))
        }
        return stacks
    }
}
