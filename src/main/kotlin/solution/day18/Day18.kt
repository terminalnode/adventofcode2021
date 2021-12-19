package xyz.terminalnode.aoc2021.solution.day18

import xyz.terminalnode.aoc2021.solution.Solution
import java.util.*

object Day18 : Solution(18, "Snailfish") {
  private fun parseLine(input: String): SFNumber {
    val iterator = input.iterator()
    iterator.next() // drop initial '['

    return SFNumber().also { it.parseValues(iterator) }
  }

  private fun reduce(number: SFNumber): SFNumber {
    do {
      val explodingChild = number.findExplodingChild()
      val splittingChild = number.findSplittingChild()

      if (explodingChild != null) explodingChild.explode()
      else splittingChild?.split()
    } while (explodingChild != null || splittingChild != null)
    return number
  }

  override fun partOne(): String {
    val lines = readLines("day18.txt").map { parseLine(it) }
    val result = lines.reduce { first, second -> reduce(first + second) }
    return result.magnitude.toString()
  }

  override fun partTwo(): String {
    // Because the SFNumber logic relies heavily on mutation we can't parse the input lines to
    // SFNumbers here, we will have to parse it every time we want to use it.
    val lines = LinkedList(readLines("day18.txt"))
    val magnitudes = mutableListOf<Long>()

    while (lines.size > 1) {
      val first = lines.pop()!!
      lines.forEach { second ->
        val one = reduce(parseLine(first) + parseLine(second)).magnitude
        val two = reduce(parseLine(second) + parseLine(first)).magnitude
        magnitudes.add(one)
        magnitudes.add(two)
      }
    }

    return magnitudes.maxOrNull().toString()
  }
}