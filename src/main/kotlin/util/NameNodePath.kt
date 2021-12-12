package xyz.terminalnode.aoc2021.util

class NameNodePath(nodes: List<NameNode>) {
  constructor(node: NameNode) : this(listOf(node))

  private val nodes = nodes.toMutableList() // shallow mutable clone
  private val nodeNames = nodes.map { it.name }.toMutableSet()

  private fun List<NameNode>.lastNode() = lastOrNull() ?: throw IllegalStateException("NameNodePath was empty!")

  fun isFinished() = nodes.lastNode().name == "end"

  fun addNode(n: NameNode) {
    nodes.add(n)
    nodeNames.add(n.name)
  }

  fun branch() : List<NameNodePath> {
    val lastNode = nodes.lastNode()
    return lastNode.connections
      .mapNotNull { newNode ->
        if (newNode.isLowerCase && nodeNames.contains(newNode.name)) {
          return@mapNotNull null
        }

        NameNodePath(nodes).also { it.addNode(newNode) }
      }
  }

  override fun toString() = nodeNames.joinToString(",")
}