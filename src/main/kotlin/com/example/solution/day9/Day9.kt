package com.example.solution.day9

import com.example.solution.Solution
import com.example.type.IntMatrix
import java.util.*

object Day9 : Solution(9, "Smoke Basin") {
  override fun run(part: Int): String {
    return when (part) {
      1 -> partOne()
      2 -> partTwo()
      else -> TODO("Not yet implemented")
    }
  }

  private fun partOne() : String {
    val map = readLines("day9.txt").map { line -> line.toCharArray().map { char -> char.digitToInt() } }
    return getLowPoints(map).sumOf { it.getValueFrom(map) }.toString()
  }

  private fun partTwo() : String {
    val map = readLines("day9.txt").map { line -> line.toCharArray().map { char -> char.digitToInt() } }
    return getLowPoints(map)
      .map { flowUp(map, it).size }
      .sortedDescending()
      .take(3)
      .reduce { acc, i -> acc * i }
      .toString()
  }

  private fun getLowPoints(map: IntMatrix): List<Point> {
    val maxXIndex = map[0].size - 1
    val maxYIndex = map.size - 1
    val xRange = 0..maxXIndex
    val yRange = 0..maxYIndex

    val lowPoints = mutableListOf<Point>()
    for (y in yRange) {
      for (x in xRange) {
        val point = Point(x, y)
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

  private fun flowUp(map: IntMatrix, point: Point): Set<Point> {
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