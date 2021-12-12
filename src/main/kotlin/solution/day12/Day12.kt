package xyz.terminalnode.aoc2021.solution.day12

import xyz.terminalnode.aoc2021.solution.Solution
import xyz.terminalnode.aoc2021.util.NameNode
import xyz.terminalnode.aoc2021.util.NameNodePath
import java.util.*

private typealias NodeMap = MutableMap<String, NameNode>
private typealias PathList = LinkedList<NameNodePath>

private fun NodeMap.addNode(name: String) : NameNode {
  putIfAbsent(name, NameNode(name))
  return get(name)!!
}

object Day12 : Solution(12, "Passage Pathing") {
  private fun generateNodeMap(fileName: String) : NodeMap {
    val nodes = mutableMapOf<String, NameNode>()
    readLines(fileName)
      .forEach { line ->
        val (a, b) = line.split("-").map { nodes.addNode(it) }
        a.addBidirectional(b)
      }

    return nodes
  }

  private fun initPathList(nodeMap: NodeMap) : PathList {
    val startNode = nodeMap["start"] ?: throw IllegalStateException("Node map is missing start")
    return PathList().also { it.add(NameNodePath(startNode)) }
  }

  override fun partOne(): String {
    var pathCount = 0
    val nodeMap = generateNodeMap("day12.txt")
    val ongoingPaths = initPathList(nodeMap)

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