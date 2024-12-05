package se.yverling.advent.windows._2022

import se.yverling.advent.Window

internal class Window8 : Window() {
    private lateinit var forrest: MutableList<MutableList<Int>>

    override fun setUp() {
        forrest = populateForrest()
    }

    override fun part1(): String {
        return countVisibleTrees(forrest).toString()
    }

    override fun part2(): String {
        return findBestScenicScore(forrest).toString()
    }

    private fun populateForrest(): MutableList<MutableList<Int>> {
        val forrest: MutableList<MutableList<Int>> = mutableListOf()
        reader.forEachLine { line ->
            val treeRow = mutableListOf<Int>()
            line.forEach { treeHeight ->
                treeRow.add(treeHeight.digitToInt())
            }
            forrest.add(treeRow)
        }
        return forrest
    }

    private fun countVisibleTrees(forrest: MutableList<MutableList<Int>>): Int {
        var visibleTrees = 0

        forrest.forEachIndexed { currentY, treeRow ->
            treeRow.forEachIndexed { currentX, currentTreeHeight ->

                // All outermost trees are visible
                if (currentX == 0 || currentY == 0 || currentX == (treeRow.size - 1) || currentY == (forrest.size - 1)) {
                    visibleTrees++
                } else if (hasClearViewInRow(treeRow, currentX, currentTreeHeight) ||
                    hasClearViewInColumn(forrest, currentX, currentY, currentTreeHeight)
                ) {
                    visibleTrees++
                }
            }
        }
        return visibleTrees
    }

    private fun hasClearViewInRow(
        treeRow: MutableList<Int>,
        currentX: Int,
        currentTreeHeight: Int
    ): Boolean {
        val leftTreePath = treeRow.subList(0, currentX)
        val rightTreePath = treeRow.subList(currentX + 1, treeRow.size)

        return isAllTreesShorter(leftTreePath, currentTreeHeight) ||
                isAllTreesShorter(rightTreePath, currentTreeHeight)
    }

    private fun hasClearViewInColumn(
        forrest: MutableList<MutableList<Int>>,
        currentX: Int,
        currentY: Int,
        currentTreeHeight: Int
    ): Boolean {
        val (upperTreePath, lowerTreePath) = calculatePaths(forrest, currentY, currentX)

        return isAllTreesShorter(upperTreePath, currentTreeHeight) ||
                isAllTreesShorter(lowerTreePath, currentTreeHeight)
    }

    private fun isAllTreesShorter(treePath: MutableList<Int>, currentTreeHeight: Int): Boolean {
        return treePath.all { it < currentTreeHeight }
    }

    // ------

    private fun findBestScenicScore(forrest: MutableList<MutableList<Int>>): Int {
        var bestScenicScore = 0

        forrest.forEachIndexed { currentY, treeRow ->
            treeRow.forEachIndexed { currentX, currentTreeHeight ->
                val currentScenicScore =
                    findHorizontalScenicScore(treeRow, currentX, currentTreeHeight) * findVerticalScenicScore(
                        forrest,
                        currentX,
                        currentY,
                        currentTreeHeight
                    )
                if (currentScenicScore > bestScenicScore) bestScenicScore = currentScenicScore
            }
        }

        return bestScenicScore
    }

    private fun findHorizontalScenicScore(
        treeRow: MutableList<Int>,
        currentX: Int,
        currentTreeHeight: Int
    ): Int {
        val leftTreePath = treeRow.subList(0, currentX)
        val rightTreePath = treeRow.subList(currentX + 1, treeRow.size)

        return findNumberOfVisibleTreesInTreePath(leftTreePath, currentTreeHeight) * findNumberOfVisibleTreesInTreePath(
            rightTreePath,
            currentTreeHeight
        )
    }

    private fun findVerticalScenicScore(
        forrest: MutableList<MutableList<Int>>,
        currentX: Int,
        currentY: Int,
        currentTreeHeight: Int
    ): Int {
        val (upperTreePath, lowerTreePath) = calculatePaths(forrest, currentY, currentX)

        return findNumberOfVisibleTreesInTreePath(
            upperTreePath,
            currentTreeHeight
        ) * findNumberOfVisibleTreesInTreePath(lowerTreePath, currentTreeHeight)
    }

    private fun findNumberOfVisibleTreesInTreePath(treePath: MutableList<Int>, currentTreeHeight: Int): Int {
        var numberOfVisibleTrees = 0

        run search@{
            treePath.forEach { treeHeight ->
                numberOfVisibleTrees++
                if (treeHeight >= currentTreeHeight) {
                    return@search
                }
            }
        }

        return numberOfVisibleTrees
    }

    private fun calculatePaths(
        forrest: MutableList<MutableList<Int>>,
        currentY: Int,
        currentX: Int
    ): Pair<MutableList<Int>, MutableList<Int>> {
        val upperTreePath = mutableListOf<Int>()
        val lowerTreePath = mutableListOf<Int>()

        forrest.forEachIndexed { treeY, treeRow ->
            if (treeY < currentY) {
                upperTreePath.add(treeRow[currentX])
            } else if (treeY > currentY) {
                lowerTreePath.add(treeRow[currentX])
            }
        }
        return Pair(upperTreePath, lowerTreePath)
    }
}
