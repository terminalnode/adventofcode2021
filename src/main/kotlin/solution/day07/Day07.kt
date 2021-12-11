package xyz.terminalnode.aoc2021.solution.day07

import xyz.terminalnode.aoc2021.solution.Solution
import kotlin.math.abs

object Day07 : Solution(7, "The Treachery of Whales") {
  override fun partOne() : String {
    val input = readFile("day01-10/day07.txt").split(",").map { it.toInt() }

    var minValue = Int.MAX_VALUE
    var minTarget = 0
    input.forEach { target ->
      val value = input.sumOf { abs(target - it) }
      if (minValue > value) {
        minValue = value
        minTarget = target
      }
    }

    return "Target $minTarget, value $minValue"
  }

  override fun partTwo() : String {
    val input = readFile("day01-10/day07.txt").split(",").map { it.toInt() }

    var minValue = Int.MAX_VALUE
    var minTarget = 0
    input.forEach { target ->
      // Not the best way, but it works lol
      val value = input.sumOf { (1..abs(target - it)).sum() }
      if (minValue > value) {
        minValue = value
        minTarget = target
      }
    }

    return "Target $minTarget, value $minValue"
  }
}