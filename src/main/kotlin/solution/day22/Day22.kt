package xyz.terminalnode.aoc2021.solution.day22

import xyz.terminalnode.aoc2021.solution.Solution
import xyz.terminalnode.aoc2021.util.Point3D
import java.util.*

object Day22 : Solution(22, "Reactor Reboot") {
  @Suppress("SameParameterValue")
  private fun parse(fileName: String) = readLines(fileName).map { parseBlock(it) }

  override fun partOne(): String {
    val on = mutableSetOf<Point3D>()
    parse("day22.txt")
      .forEach {
        val points = it.getPointsInRange(-50..50).toSet()
        if (it.isOn) on.addAll(points)
        else on.removeAll(points)
      }
    return on.size.toString()
  }

  override fun partTwo(): String {
    // 1187694947147483 too low
    // 1187742789777792 too low
    val input = parse("day22-test-p2.txt")
    var onBlocks = mutableSetOf(input.first())
    input.drop(1).forEach {
      onBlocks = onBlocks.flatMap { onBlock -> onBlock.remove(it) }.toMutableSet()
      if (it.isOn) onBlocks.add(it)
    }

    return onBlocks.sumOf { it.getSize() }.toString()
  }
}