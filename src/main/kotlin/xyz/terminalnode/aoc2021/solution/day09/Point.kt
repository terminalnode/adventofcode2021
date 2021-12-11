package xyz.terminalnode.aoc2021.solution.day09

import xyz.terminalnode.aoc2021.type.IntMatrix

data class Point(
  val x: Int,
  val y: Int,
  var value: Int? = null,
) {
  fun getValueFrom(map: IntMatrix) : Int {
    value = map[y][x]
    return value!!
  }

  fun getNeighbors(): List<Point> = listOf(
    // Left
    Point(x-1, y-1), Point(x-1, y), Point(x-1, y+1),
    // Above / Under
    Point(x, y-1), Point(x, y+1),
    // Right
    Point(x+1, y-1), Point(x+1, y), Point(x+1, y+1),
  )

  fun getOrthogonalNeighbors(): List<Point> = listOf(
    Point(x-1, y), Point(x+1, y),
    Point(x, y-1), Point(x, y+1))
}