package com.example.solution.day7

import com.example.solution.Solution
import kotlin.math.abs

object Day7 : Solution(7, "The Treachery of Whales") {
  override fun run(part: Int): String {
    return when (part) {
      1 -> partOne()
      2 -> partTwo()
      else -> TODO("Not yet implemented")
    }
  }

  private fun partOne() : String {
    val input = readFile("day7.txt").split(",").map { it.toInt() }

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

  private fun partTwo() : String {
    val input = readFile("day7.txt").split(",").map { it.toInt() }

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