package se.yverling.advent._2022

import se.yverling.advent.Window
import se.yverling.advent.WindowFileReader

internal const val FOLDER_LIMIT = 100000

class Window7(reader: WindowFileReader) : Window(reader, 7) {
    private val changeDir = Regex("^\\$ cd (\\w+|\\W+)\$")
    private val listFiles = Regex("^\\\$ ls$")
    private val file = Regex("^(\\d+) (\\w+\\.*\\w*)$")
    private val directory = Regex("^dir (\\w+)$")

    private var sizeOfDirsBelowFolderLimit = 0L
    private var deletableDirs = mutableListOf<Long>()

    override fun part1(): String {
        val root = Node("/", 0)
        var currentNode: Node = root

        reader.read().forEachLine { line ->
            when {
                file.matches(line) -> {
                    val size = file.find(line)?.groupValues!![1].toLong()
                    val name = file.find(line)?.groupValues!![2]

                    currentNode.addChild(Node(name, size))
                }

                directory.matches(line) -> {
                    val name = directory.find(line)?.groupValues!![1]

                    currentNode.addChild(Node(name, 0))
                }

                listFiles.matches(line) -> {
                    // Do nothing
                }

                changeDir.matches(line) -> {
                    when (val name = changeDir.find(line)?.groupValues!![1]) {
                        "/" -> {
                            // Do nothing (or maybe init here)
                        }
                        ".." -> {
                            currentNode = currentNode.getParent()
                        }
                        else -> {
                            currentNode = currentNode.getChildByName(name)
                        }
                    }
                }

                else -> println("ERROR: Unknown input")
            }
        }

        calculateDirSizes(root)

        println(root)

        return sizeOfDirsBelowFolderLimit.toString()
    }

    override fun part2(): String {
        return deletableDirs.sorted()[0].toString()
    }

    private fun calculateDirSizes(node: Node): Long {
        if (node.children.isNotEmpty()) {
            node.children.forEach { child ->
                node.size += calculateDirSizes(child)
            }

            if (node.size < FOLDER_LIMIT) {
                sizeOfDirsBelowFolderLimit += node.size
            }

            // TODO Calculate this value!
            if (node.size > 208860) {
                deletableDirs.add(node.size)
            }
        }
        return node.size
    }
}

class Node(private val name: String, internal var size: Long) {
    private var parent: Node? = null

    internal var children: MutableList<Node> = mutableListOf()

    fun addChild(node: Node) {
        children.add(node)
        node.parent = this
    }

    fun getChildByName(name: String): Node {
        return children.first {
            it.name == name
        }
    }

    fun getParent(): Node {
        return parent ?: this
    }

    override fun toString(): String {
        var node = "$name (size=$size)"
        if (children.isNotEmpty()) {
            node += " [" + children.map { it.toString() } + " ]"
        }
        return node
    }
}
