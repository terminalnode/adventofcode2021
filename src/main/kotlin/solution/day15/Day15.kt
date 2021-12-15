package xyz.terminalnode.aoc2021.solution.day15

import xyz.terminalnode.aoc2021.solution.Solution
import xyz.terminalnode.aoc2021.util.*

private typealias ChitonNode = Point<ChitonData>

private data class ChitonData(
  val weight: Int,
  var minDistance: Long = Long.MAX_VALUE,
)

private fun ChitonNode.testPathFrom(other: ChitonNode, matrix: Matrix<ChitonNode>) : List<ChitonNode> {
  val distance = value!!.weight + other.value!!.minDistance
  if (distance < value!!.minDistance) {
    value!!.minDistance = distance
    return getOrthogonalNeighbors()
      .filter { it.x in matrix.getXRange() && it.y in matrix.getYRange() }
      .map { matrix.get(it.x, it.y) }
  }
  return listOf()
}

private fun ChitonNode.getNeighbours(matrix: Matrix<ChitonNode>) =
  getOrthogonalNeighbors()
    .filter { it.x in matrix.getXRange() && it.y in matrix.getYRange() }
    .map { matrix.get(it.x, it.y) }
    .map { Pair(this, it) }

private fun <T> Matrix<T>.getBottomRight() : T {
  val maxY = size - 1
  val maxX = get(0).size - 1
  return get(maxX, maxY)
}

object Day15 : Solution(15, "Chiton") {
  @Suppress("SameParameterValue")
  private fun parse(fileName: String) : Matrix<ChitonNode> {
    return readLines(fileName).mapIndexed { y, line ->
      line.mapIndexed { x, char ->
        Point(x, y, ChitonData(char.digitToInt()))
      }
    }
  }

  override fun partOne(): String {
    val matrix = parse("day15.txt")
    matrix[0][0].value!!.minDistance = 0

    var nodeList = matrix.get(0, 0).getNeighbours(matrix)
    while (nodeList.isNotEmpty()) {
      nodeList = nodeList.flatMap { (origin, destination) ->
        destination.testPathFrom(origin, matrix).map { Pair(destination, it) }
      }
    }

    val bottomRight = matrix.getBottomRight()
    return bottomRight.value!!.minDistance.toString()
  }

  override fun partTwo(): String {
    TODO("Not yet implemented")
  }
}