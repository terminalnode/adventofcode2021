package xyz.terminalnode.aoc2021.solution.day05

data class Point(
  val x: Int,
  val y: Int,
) {
  override fun toString(): String = "$x,$y"
}