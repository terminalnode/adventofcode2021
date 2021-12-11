package xyz.terminalnode.aoc2021.solution

import xyz.terminalnode.aoc2021.model.AocException

abstract class Solution(val day: Int, val name: String) {
  fun run(part: Int) =
    when (part) {
      1 -> partOne()
      2 -> partTwo()
      else -> TODO("Not yet implemented")
    }

  abstract fun partOne() : String
  abstract fun partTwo() : String

  fun readFile(fileName: String) : String {
    val path = fileName.takeIf { it.startsWith('/') } ?: "/$fileName"
    return javaClass.getResource(path)?.readText() ?: throw AocException("Failed to read file '$path'!")
  }

  fun readLines(fileName: String) = readFile(fileName).split("\n")
}