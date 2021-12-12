package xyz.terminalnode.aoc2021.solution.day12

import xyz.terminalnode.aoc2021.solution.Solution
import xyz.terminalnode.aoc2021.util.NameNode
import xyz.terminalnode.aoc2021.util.NameNodePath
import java.util.*

private fun MutableMap<String, NameNode>.addNode(name: String) : NameNode {
  putIfAbsent(name, NameNode(name))
  return get(name)!!
}

object Day12 : Solution(12, "Passage Pathing") {
  override fun partOne(): String {
    val nodes = mutableMapOf<String, NameNode>()
    readLines("day12.txt")
      .forEach { line ->
        val (a, b) = line.split("-").map { nodes.addNode(it) }
        a.addBidirectional(b)
      }

    var pathCount = 0
    val ongoingPaths = LinkedList<NameNodePath>().also {
      it.add(NameNodePath(listOf(nodes["start"]!!)))
    }

    while (ongoingPaths.isNotEmpty()) {
      val newPaths = ongoingPaths.removeFirst().branch()
      val unfinishedPaths = newPaths.filter { !it.isFinished() }

      pathCount += newPaths.size - unfinishedPaths.size
      ongoingPaths.addAll(unfinishedPaths)
    }

    return "Number of paths: $pathCount"
  }

  override fun partTwo(): String {
    TODO("Not yet implemented")
  }
}