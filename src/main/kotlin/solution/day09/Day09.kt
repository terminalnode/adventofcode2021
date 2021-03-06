package xyz.terminalnode.aoc2021.solution.day09

import xyz.terminalnode.aoc2021.solution.Solution
import xyz.terminalnode.aoc2021.util.Matrix
import xyz.terminalnode.aoc2021.util.Point
import java.util.*

object Day09 : Solution(9, "Smoke Basin") {
  override fun partOne() : String {
    val map = readLines("day01-10/day09.txt").map { line -> line.toCharArray().map { char -> char.digitToInt() } }
    return getLowPoints(map).sumOf { it.getValueFrom(map) }.toString()
  }

  override fun partTwo() : String {
    val map = readLines("day01-10/day09.txt").map { line -> line.toCharArray().map { char -> char.digitToInt() } }
    return getLowPoints(map)
      .map { flowUp(map, it).size }
      .sortedDescending()
      .take(3)
      .reduce { acc, i -> acc * i }
      .toString()
  }

  private fun getLowPoints(map: Matrix<Int>): List<Point<Int>> {
    val maxXIndex = map[0].size - 1
    val maxYIndex = map.size - 1
    val xRange = 0..maxXIndex
    val yRange = 0..maxYIndex

    val lowPoints = mutableListOf<Point<Int>>()
    for (y in yRange) {
      for (x in xRange) {
        val point = Point<Int>(x, y)
        point.getValueFrom(map)

        val isLowPoint = point.getNeighbors()
          .filter { it.x in xRange && it.y in yRange }
          .onEach { it.getValueFrom(map) }
          .all { it.value!! > point.value!! }

        if (isLowPoint) {
          lowPoints.add(point)
        }
      }
    }

    return lowPoints
  }

  private fun flowUp(map: Matrix<Int>, point: Point<Int>): Set<Point<Int>> {
    val maxXIndex = map[0].size - 1
    val maxYIndex = map.size - 1
    val xRange = 0..maxXIndex
    val yRange = 0..maxYIndex

    val seenSet = mutableSetOf(point)
    val newPoints = LinkedList(listOf(point))
    while (!newPoints.isEmpty()) {
      val newPoint = newPoints.pop()

      newPoint.getOrthogonalNeighbors()
        .filter { it.x in xRange && it.y in yRange }
        .onEach { it.getValueFrom(map) }
        .filter { it.value!! < 9 }
        .forEach {
          if (!seenSet.contains(it)) {
            seenSet.add(it)
            newPoints.add(it)
          }
        }
    }
    return seenSet
  }
}