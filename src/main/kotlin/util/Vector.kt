package xyz.terminalnode.aoc2021.util

class Vector<PointT>(
  val point1: Point<PointT>,
  val point2: Point<PointT>,
) {
  companion object {
    fun parseInput(input: String) =
      input.split(" -> ")
        .map { coordinate -> coordinate
          .split(",")
          .map { num -> num.toInt() }
        }.map { Point<Unit>(it[0], it[1]) }
        .let { Vector(it[0], it[1]) }
  }

  fun getCoordinates(orthoOnly: Boolean = false): List<Point<PointT>> {
    if (orthoOnly && (point1.x != point2.x && point1.y != point2.y)) {
      return listOf()
    }

    // Not perfect, but handles k = 1 diagonal lines at least
    // For any other line you will get a diagonal line and then a straight one
    val result = mutableListOf(point1)
    var x = point1.x
    var y = point1.y

    while (x != point2.x || y != point2.y) {
      when {
        x < point2.x -> x++
        x > point2.x -> x--
      }

      when {
        y < point2.y -> y++
        y > point2.y -> y--
      }

      result.add(Point(x, y))
    }

    return result
  }

  override fun toString() = "$point1 -> $point2"
}