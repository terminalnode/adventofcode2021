package xyz.terminalnode.aoc2021.solution.day12

import xyz.terminalnode.aoc2021.util.NameNode

class CavePath(
  nodes: List<NameNode>,
  private var canPassSmallCave: Boolean = true,
) {
  constructor(node: NameNode) : this(listOf(node))

  private val nodes = nodes.toMutableList() // shallow mutable clone
  private val nodeNames = nodes.map { it.name }.toMutableList()

  private fun List<NameNode>.lastNode() = lastOrNull() ?: throw IllegalStateException("NameNodePath was empty!")

  fun isFinished() = nodes.lastNode().name == "end"

  private fun addNode(n: NameNode) {
    if (n.isLowerCase && nodeNames.contains(n.name)) {
      canPassSmallCave = false
    }

    nodes.add(n)
    nodeNames.add(n.name)
  }

  fun branch(enablePassTwice: Boolean = false) : List<CavePath> {
    val lastNode = nodes.lastNode()
    val canOverride = enablePassTwice && canPassSmallCave

    return lastNode.connections
      .filter { !it.isStart }
      .filter {
        val requireOverride = it.isLowerCase && nodeNames.contains(it.name)
        !requireOverride || canOverride
      }.map { newNode ->
        CavePath(nodes, canPassSmallCave).also { it.addNode(newNode) }
      }
  }

  override fun toString() = nodeNames.joinToString(",")
}