package xyz.terminalnode.aoc2021.solution.day05

import xyz.terminalnode.aoc2021.solution.Solution
import xyz.terminalnode.aoc2021.util.Vector
import xyz.terminalnode.aoc2021.util.Point

object Day05 : Solution(5, "Hydrothermal Venture") {
  override fun partOne() = solve(orthogonalOnly = true)
  override fun partTwo() = solve(orthogonalOnly = false)

  private fun solve(orthogonalOnly: Boolean) : String {
    val lines = readLines("day05.txt").map { parseInput(it) }

    val seenSet = mutableSetOf<Point<Unit>>()
    val duplicateSet = mutableSetOf<Point<Unit>>()
    lines.flatMap { it.getCoordinates(orthogonalOnly) }.forEach { point ->
      if (seenSet.contains(point)) {
        duplicateSet.add(point)
      } else {
        seenSet.add(point)
      }
    }

    return duplicateSet.size.toString()
  }

  private fun parseInput(input: String) =
    input.split(" -> ")
      .map { coordinate -> coordinate
        .split(",")
        .map { num -> num.toInt() }
      }.map { Point<Unit>(it[0], it[1]) }
      .let { Vector(it[0], it[1]) }
}