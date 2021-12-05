package com.example.solution.day5

import com.example.solution.Solution

object Day5 : Solution(5) {
  override fun run(part: Int): Any {
    return when (part) {
      1 -> solve(orthoOnly = true)
      2 -> solve(orthoOnly = false)
      else -> TODO("Not yet implemented")
    }
  }

  fun solve(orthoOnly: Boolean) : Any {
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

    return duplicateSet.size
  }
}