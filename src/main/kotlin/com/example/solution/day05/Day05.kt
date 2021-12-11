package com.example.solution.day05

import com.example.solution.Solution

object Day05 : Solution(5, "Hydrothermal Venture") {
  override fun run(part: Int): String {
    return when (part) {
      1 -> solve(orthoOnly = true)
      2 -> solve(orthoOnly = false)
      else -> TODO("Not yet implemented")
    }
  }

  fun solve(orthoOnly: Boolean) : String {
    val lines = readLines("day5.txt").map { Line.parseInput(it) }

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