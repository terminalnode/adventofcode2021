package com.example.solution.day9

import com.example.solution.Solution
import com.example.type.IntMatrix

object Day9 : Solution(9, "Smoke Basin") {
  override fun run(part: Int): String {
    return when (part) {
      1 -> partOne()
      else -> TODO("Not yet implemented")
    }
  }

  private fun partOne() : String {
    val map = readLines("day9.txt").map { line -> line.toCharArray().map { char -> char.digitToInt() } }
    return getLowPoints(map).sumOf { it.getValueFrom(map) }.toString()
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
}