package xyz.terminalnode.aoc2021.solution.day22

import xyz.terminalnode.aoc2021.solution.Solution
import xyz.terminalnode.aoc2021.util.Point3D

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
    val input = parse("day22.txt")
    var onBlocks = mutableListOf(input.first())
    input.drop(1).forEach { block ->
      onBlocks = onBlocks.flatMap { it.remove(block) }.toMutableList()
      if (block.isOn) onBlocks.add(block)
    }
    return onBlocks.sumOf { it.getSize() }.toString()
  }
}