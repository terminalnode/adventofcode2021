package xyz.terminalnode.aoc2021.solution.day25

import xyz.terminalnode.aoc2021.solution.Solution

object Day25 : Solution(25, "Sea Cucumber") {
  override fun partOne(): String {
    val seaBed = parseSeaBed(readLines("day25.txt"))
    var steps = 0

    do {
      steps++

      val rightMoves = seaBed.flatten().filter { it?.value == SeaCucumberDirection.RIGHT && it.canMove(seaBed) }
      rightMoves.forEach { it?.move(seaBed) }
      val downMoves = seaBed.flatten().filter { it?.value == SeaCucumberDirection.DOWN && it.canMove(seaBed) }
      downMoves.forEach { it?.move(seaBed) }
    } while (rightMoves.isNotEmpty() || downMoves.isNotEmpty())

    return steps.toString()
  }

  override fun partTwo(): String {
    TODO("Not yet implemented")
  }
}