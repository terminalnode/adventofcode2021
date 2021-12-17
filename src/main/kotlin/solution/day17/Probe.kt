package xyz.terminalnode.aoc2021.solution.day17

import xyz.terminalnode.aoc2021.util.Point

data class Probe(
  var xVelocity: Int,
  var yVelocity: Int,
  var x: Int = 0,
  var y: Int = 0,
  val positions: MutableList<Point<Unit>> = mutableListOf(Point(x, y)),
) {
  val initialXVelocity = xVelocity
  val initialYVelocity = yVelocity
  val maxX get() = positions.maxOf { it.x }
  val minX get() = positions.minOf { it.x }
  val maxY get() = positions.maxOf { it.y }
  val minY get() = positions.minOf { it.y }

  fun step() {
    // The probe's x and y position increases by its x and y velocity respectively.
    x += xVelocity
    y += yVelocity

    // Due to drag, the probe's x velocity changes by 1 toward the value 0.
    if (xVelocity > 0) xVelocity--

    //Due to gravity, the probe's y velocity decreases by 1.
    yVelocity--

    // Log new position
    positions.add(Point(x, y))
  }
}
