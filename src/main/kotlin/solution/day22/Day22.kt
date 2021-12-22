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
    val inputz = listOf(
      ReactorBlock(1..3,1..3,1..3, isOn = true),
      ReactorBlock(2..2,2..2,2..2, isOn = false),
      ReactorBlock(3..4,3..4,3..4, isOn = true),
    )
    val inputa = listOf(
      ReactorBlock(1..1,1..1,1..1),
      ReactorBlock(1..2,1..1,1..1),
      ReactorBlock(1..1,1..2,1..1),
    )

    val input = parse("day22-test-p2.txt")
    var onBlocks = mutableSetOf(input.first())
    input.drop(1).forEach {
      if (it.isOn) {
        onBlocks.add(it)
        return@forEach
      }

      onBlocks = onBlocks.flatMap { onBlock -> onBlock.remove(it) }.toMutableSet()
    }

    var overlappingBlocks = LinkedList(onBlocks)
    val finished = mutableSetOf<ReactorBlock>()

    while (overlappingBlocks.isNotEmpty()) {
      val newOverlapping = mutableSetOf<ReactorBlock>()
      val current = overlappingBlocks.pop()

      while (overlappingBlocks.isNotEmpty()) {
        val next = overlappingBlocks.pop()
        if (next.hasOverlap(current)) newOverlapping.addAll(next.remove(current))
        else newOverlapping.add(next)
      }
      finished.add(current)

      overlappingBlocks = LinkedList(newOverlapping)
    }

    return finished.sumOf { it.getSize() }.toString()
  }
}