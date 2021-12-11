package xyz.terminalnode.aoc2021.solution.day05

import xyz.terminalnode.aoc2021.solution.Solution

object Day05 : Solution(5, "Hydrothermal Venture") {
  override fun partOne() = solve(orthoOnly = true)
  override fun partTwo() = solve(orthoOnly = false)

  private fun solve(orthoOnly: Boolean) : String {
    val lines = readLines("day05.txt").map { Line.parseInput(it) }

    val seenSet = mutableSetOf<Point>()
    val duplicateSet = mutableSetOf<Point>()
    lines.flatMap { it.getCoordinates(orthoOnly) }.forEach { point ->
      if (seenSet.contains(point)) {
        duplicateSet.add(point)
      } else {
        seenSet.add(point)
      }
    }

    return duplicateSet.size.toString()
  }
}