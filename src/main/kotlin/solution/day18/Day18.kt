package xyz.terminalnode.aoc2021.solution.day18

import xyz.terminalnode.aoc2021.solution.Solution

object Day18 : Solution(18, "Snailfish") {
  private fun parseLine(input: String): SFNumber {
    val iterator = input.iterator()
    iterator.next() // drop initial '['

    return SFNumber().also { it.parseValues(iterator) }
  }

  private fun reduce(number: SFNumber): SFNumber {
    println("Reducing: $number")
    do {
      val explodingChild = number.findExplodingChild()
      val splittingChild = number.findSplittingChild()

      if (explodingChild != null) {
        print("$explodingChild exploded: ")
        explodingChild.explode()
      } else if (splittingChild != null) {
        print("$splittingChild split: ")
        splittingChild.split()
      } else {
        print("result: ")
      }
      println(number)
    } while (explodingChild != null || splittingChild != null)
    println()
    return number
  }

  override fun partOne(): String {
    val lines = readLines("day18.txt").map { parseLine(it) }
    val result = lines.reduce { first, second -> reduce(first + second) }
    return result.magnitude.toString()
  }

  override fun partTwo(): String {
    TODO("Not yet implemented")
  }
}