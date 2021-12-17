package xyz.terminalnode.aoc2021.solution.day17

import xyz.terminalnode.aoc2021.solution.Solution
import kotlin.math.abs


object Day17 : Solution(17, "Trick Shot") {
  private const val liveData = "target area: x=207..263, y=-115..-63"
  private const val testData = "target area: x=20..30, y=-10..-5"

  @Suppress("SameParameterValue")
  private fun parse(input: String) = input
    .removePrefix("target area: ")
    .split(", ")
    .map { it
      .drop(2) /* remove x= / y= */
      .split("..") /* split range start/end */
      .let { (start, end) -> start.toInt()..end.toInt() }
    }.let { (xRange, yRange) -> TargetArea(xRange, yRange) }

  override fun partOne(): String {
    val targetArea = parse(liveData)
    val goodProbes = mutableListOf<Probe>()

    val xRange = (1..targetArea.maxX)
    val yRange = (1..abs(targetArea.minY)) // probably excessive

    xRange.forEach { xVelocity ->
      yRange.forEach { yVelocity ->
        val probe = Probe(xVelocity, yVelocity)
        while (!targetArea.probeIsDead(probe) && !targetArea.probeIsIn(probe)) {
          probe.step()
        }
        if (targetArea.probeIsIn(probe)) goodProbes.add(probe)
      }
    }

    return goodProbes.maxOf { it.maxY }.toString()
  }

  override fun partTwo(): String {
    TODO("Not yet implemented")
  }
}