package xyz.terminalnode.aoc2021.solution.day02

import xyz.terminalnode.aoc2021.solution.Solution

object Day02 : Solution(2, "Dive!") {
  override fun partOne() : String {
    val submarine = Submarine()
    readLines("day02.txt").forEach { submarine.readInstruction(it) }
    return "${submarine.calculateResult()}"
  }

  override fun partTwo() : String {
    val submarine = SubmarineMK2()
    readLines("day02.txt").forEach { submarine.readInstruction(it) }
    return "${submarine.calculateResult()}"
  }
}