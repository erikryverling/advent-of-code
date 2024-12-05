package se.yverling.advent.windows._2022

import se.yverling.advent.Window

internal class Window9 : Window() {
    private val isTest = false // <-- Update this to switch between Test and Real input

    private val bridgeHeight = if (isTest) 21 else 58
    private val bridgeWidth = if (isTest) 26 else 43

    private lateinit var rope: MutableList<Pair<Int, Int>>

    private var visitedTailPositions = mutableSetOf<Pair<Int, Int>>()

    private val inputMatcher = Regex("^(\\w) (\\d+)$")

    override fun part1(): String {
        rope = mutableListOf(Pair(11, 15), Pair(11, 15))

        moveRope()

        return visitedTailPositions.size.toString()
    }

    override fun part2(): String {
        rope = mutableListOf(
            Pair(11, 15),
            Pair(11, 15),
            Pair(11, 15),
            Pair(11, 15),
            Pair(11, 15),
            Pair(11, 15),
            Pair(11, 15),
            Pair(11, 15),
            Pair(11, 15),
            Pair(11, 15)
        )

        visitedTailPositions = mutableSetOf()

        moveRope()

        return visitedTailPositions.size.toString()
    }

    private fun moveRope() {
        renderInitialState(isTest)

        reader.forEachLine(if (isTest) 1 else 0) { line ->
            val direction = inputMatcher.find(line)?.groupValues!![1].toCharArray()[0]
            val steps = inputMatcher.find(line)?.groupValues!![2].toInt()

            renderStepHeader(isTest, direction, steps)

            for (step in 1..steps) {
                updateHeadPositionByOneStep(direction)

                // Iterate over the knots after the head
                for (knotIndex in 1 until rope.size) {
                    updateKnotPositionAccordingToItsPredecessorsPosition(knotIndex)
                }
                saveVisitedTailPosition()
            }
            renderBridge(isTest)
        }

        renderVisitedTailPositions()
    }

    private fun updateHeadPositionByOneStep(directionOfHead: Char) {
        when (directionOfHead) {
            'R' -> {
                rope[0] = Pair(rope[0].first + 1, rope[0].second)
            }

            'L' -> {
                rope[0] = Pair(rope[0].first - 1, rope[0].second)
            }

            'U' -> {
                rope[0] = Pair(rope[0].first, rope[0].second - 1)
            }

            'D' -> {
                rope[0] = Pair(rope[0].first, rope[0].second + 1)
            }
        }
    }

    private fun updateKnotPositionAccordingToItsPredecessorsPosition(knotIndex: Int) {
        // Head has pulled to far away to the righter down (H1)
        if (rope[knotIndex - 1].first - rope[knotIndex].first == 2 && rope[knotIndex - 1].second - rope[knotIndex].second == 1) {
            rope[knotIndex] = Pair(rope[knotIndex].first + 1, rope[knotIndex].second + 1)

            // Head has pulled too far away to the righter up (H2)
        } else if (rope[knotIndex - 1].first - rope[knotIndex].first == 2 && rope[knotIndex - 1].second - rope[knotIndex].second == -1) {
            rope[knotIndex] = Pair(rope[knotIndex].first + 1, rope[knotIndex].second - 1)

            // Head has pulled too far away to the lefter down (H3)
        } else if (rope[knotIndex - 1].first - rope[knotIndex].first == -2 && rope[knotIndex - 1].second - rope[knotIndex].second == 1) {
            rope[knotIndex] = Pair(rope[knotIndex].first - 1, rope[knotIndex].second + 1)

            // Head has pulled too far away to the lefter down (H4)
        } else if (rope[knotIndex - 1].first - rope[knotIndex].first == -2 && rope[knotIndex - 1].second - rope[knotIndex].second == -1) {
            rope[knotIndex] = Pair(rope[knotIndex].first - 1, rope[knotIndex].second - 1)

            // Head has pulled too far away to the downer right  (H5)
        } else if (rope[knotIndex - 1].first - rope[knotIndex].first == 1 && rope[knotIndex - 1].second - rope[knotIndex].second == 2) {
            rope[knotIndex] = Pair(rope[knotIndex].first + 1, rope[knotIndex].second + 1)

            // Head has pulled too far away to the upper right (H6)
        } else if (rope[knotIndex - 1].first - rope[knotIndex].first == 1 && rope[knotIndex - 1].second - rope[knotIndex].second == -2) {
            rope[knotIndex] = Pair(rope[knotIndex].first + 1, rope[knotIndex].second - 1)

            // Head has pulled too far away to the downer left (H7)
        } else if (rope[knotIndex - 1].first - rope[knotIndex].first == -1 && rope[knotIndex - 1].second - rope[knotIndex].second == 2) {
            rope[knotIndex] = Pair(rope[knotIndex].first - 1, rope[knotIndex].second + 1)

            // Head has pulled too far away to the upper left (H8)
        } else if (rope[knotIndex - 1].first - rope[knotIndex].first == -1 && rope[knotIndex - 1].second - rope[knotIndex].second == -2) {
            rope[knotIndex] = Pair(rope[knotIndex].first - 1, rope[knotIndex].second - 1)

            // Head has pulled too far away to the the right (H9)
        } else if (rope[knotIndex - 1].first - rope[knotIndex].first == 2 && rope[knotIndex - 1].second - rope[knotIndex].second == 0) {
            rope[knotIndex] = Pair(rope[knotIndex].first + 1, rope[knotIndex].second)

            // Head has pulled too far away to the left (H10)
        } else if (rope[knotIndex - 1].first - rope[knotIndex].first == -2 && rope[knotIndex - 1].second - rope[knotIndex].second == 0) {
            rope[knotIndex] = Pair(rope[knotIndex].first - 1, rope[knotIndex].second)

            // Head has pulled too far away downwards (H11)
        } else if (rope[knotIndex - 1].first - rope[knotIndex].first == 0 && rope[knotIndex - 1].second - rope[knotIndex].second == 2) {
            rope[knotIndex] = Pair(rope[knotIndex].first, rope[knotIndex].second + 1)

            // Head has pulled too far away upwards (H12)
        } else if (rope[knotIndex - 1].first - rope[knotIndex].first == 0 && rope[knotIndex - 1].second - rope[knotIndex].second == -2) {
            rope[knotIndex] = Pair(rope[knotIndex].first, rope[knotIndex].second - 1)

            // Head has pulled too far away to the downer righter (H13)
        } else if (rope[knotIndex - 1].first - rope[knotIndex].first == 2 && rope[knotIndex - 1].second - rope[knotIndex].second == 2) {
            rope[knotIndex] = Pair(rope[knotIndex].first + 1, rope[knotIndex].second + 1)

            // Head has pulled too far away to the upper righter (H14)
        } else if (rope[knotIndex - 1].first - rope[knotIndex].first == 2 && rope[knotIndex - 1].second - rope[knotIndex].second == -2) {
            rope[knotIndex] = Pair(rope[knotIndex].first + 1, rope[knotIndex].second - 1)

            // Head has pulled too far downer lefter (H15)
        } else if (rope[knotIndex - 1].first - rope[knotIndex].first == -2 && rope[knotIndex - 1].second - rope[knotIndex].second == 2) {
            rope[knotIndex] = Pair(rope[knotIndex].first - 1, rope[knotIndex].second + 1)

            // Head has pulled too far upper lefter (H16)
        } else if (rope[knotIndex - 1].first - rope[knotIndex].first == -2 && rope[knotIndex - 1].second - rope[knotIndex].second == -2) {
            rope[knotIndex] = Pair(rope[knotIndex].first - 1, rope[knotIndex].second - 1)
        }
    }

    private fun renderStepHeader(isTest: Boolean, direction: Char, steps: Int) {
        if (!isTest) return
        println("== $direction $steps ==\n")
    }

    private fun renderInitialState(isTest: Boolean) {
        if (!isTest) return
        println("== Initial State ==\n")
        renderBridge(isTest)
    }

    private fun saveVisitedTailPosition() {
        visitedTailPositions.add(rope[rope.size - 1])
    }

    private fun renderBridge(isTest: Boolean) {
        if (!isTest) return
        for (y in 0 until bridgeHeight) {
            for (x in 0 until bridgeWidth) {
                if (rope[0].first == x && rope[0].second == y) {
                    print("H")
                } else if (rope.contains(Pair(x, y))) {
                    print(rope.indexOf(Pair(x, y)))
                } else {
                    print(".")
                }
            }
            println()
        }
        println()
    }

    private fun renderVisitedTailPositions() {
        for (y in 0 until bridgeHeight) {
            for (x in 0 until bridgeWidth) {
                if (visitedTailPositions.contains(Pair(x, y))) print("#")
                else print(".")
            }
            println()
        }
        println()
    }
}
