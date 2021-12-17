package xyz.terminalnode.aoc2021.solution.day17

import java.lang.Integer.max
import java.lang.Integer.min

data class TargetArea(
  val xRange: IntRange,
  val yRange: IntRange,
) {
  val maxX = max(xRange.last, xRange.first)
  val minX = min(xRange.last, xRange.first)

  val maxY = max(yRange.last, yRange.first)
  val minY = min(yRange.last, yRange.first)

  fun probeIsIn(probe: Probe) = probe.x in xRange && probe.y in yRange

  // Target area is always right and below origin
  fun probeIsDead(probe: Probe) = probe.x > maxX || probe.y < minY

  fun probeNeedsToIncreaseX(probe: Probe) = probe.x < minX
  fun probeNeedsToIncreaseY(probe: Probe) = probe.y > maxY

  override fun toString() = "TargetArea{xRange=${minX}..${maxX}, yRange=${minY}..${maxY}}"
}
