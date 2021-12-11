package xyz.terminalnode.aoc2021.solution.day01

import xyz.terminalnode.aoc2021.solution.Solution
import java.util.*

object Day01 : Solution(1, "Sonar Sweep") {
  override fun partOne(): String {
    val depthIterator = readLines("/day01.txt").map { it.toInt() }.iterator()
    var previousValue = depthIterator.next()
    var count = 0

    depthIterator.forEachRemaining {
      if (it > previousValue) count++
      previousValue = it
    }

    return "$count"
  }

  override fun partTwo(): String {
    val depthIterator = readLines("/day01.txt").map { it.toInt() }.iterator()
    val window = LinkedList(listOf(depthIterator.next(), depthIterator.next(), depthIterator.next()))
    var previousValue = window.sum()
    var count = 0

    depthIterator.forEachRemaining {
      window.removeFirst()
      window.addLast(it)
      val newValue = window.sum()

      if (newValue > previousValue) count++
      previousValue = newValue
    }

    return "$count"
  }
}