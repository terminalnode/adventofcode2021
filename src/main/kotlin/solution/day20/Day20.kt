package xyz.terminalnode.aoc2021.solution.day20

import xyz.terminalnode.aoc2021.solution.Solution

object Day20 : Solution(20, "Trench Map") {
  fun parse(fileName: String) = parse(readLines(fileName))

  fun solve(fileName: String, enhancements: Int) : String {
    val pixelMatrix = parse(fileName)
    repeat(enhancements) { pixelMatrix.`ENHANCE!!!`() }
    return pixelMatrix.countLit().toString()
  }

  override fun partOne() = solve("day20.txt", 2)
  override fun partTwo() = solve("day20.txt", 50)
}