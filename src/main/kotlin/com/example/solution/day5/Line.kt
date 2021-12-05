package com.example.solution.day5

class Line(
  val p1: Point,
  val p2: Point,
) {
  companion object {
    fun parseInput(input: String) =
      input.split(" -> ")
        .map { coord -> coord
          .split(",")
          .map { num -> num.toInt() }
        }.map { Point(it[0], it[1]) }
        .let { Line(it[0], it[1]) }
  }

  fun getCoordinates(orthoOnly: Boolean = false): List<Point> {
    if (orthoOnly && (p1.x != p2.x && p1.y != p2.y)) {
      return listOf()
    }

    // Not perfect, but handles k = 1 diagonal lines at least
    // For any other line you will get a diagonal line and then a straight one
    val result = mutableListOf(p1)
    var x = p1.x
    var y = p1.y

    while (x != p2.x || y != p2.y) {
      when {
        x < p2.x -> x++
        x > p2.x -> x--
      }

      when {
        y < p2.y -> y++
        y > p2.y -> y--
      }

      result.add(Point(x, y))
    }

    return result
  }

  override fun toString() = "$p1 -> $p2"
}