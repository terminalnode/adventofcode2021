package xyz.terminalnode.aoc2021.util

typealias PointList<T> = List<Point<T>>

data class Point<ValueType>(
  val x: Int,
  val y: Int,
  var value: ValueType? = null,
) {
  fun getValueFrom(map: Matrix<ValueType>) : ValueType {
    value = map[y][x]
    return value!!
  }

  fun getNeighbors(): PointList<ValueType> = listOf(
    // Left
    Point(x-1, y-1), Point(x-1, y), Point(x-1, y+1),
    // Above / Under
    Point(x, y-1), Point(x, y+1),
    // Right
    Point(x+1, y-1), Point(x+1, y), Point(x+1, y+1),
  )

  fun getOrthogonalNeighbors(): PointList<ValueType> = listOf(
    Point(x-1, y), Point(x+1, y),
    Point(x, y-1), Point(x, y+1)
  )
}