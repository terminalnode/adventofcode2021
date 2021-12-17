package xyz.terminalnode.aoc2021.solution.day16

import xyz.terminalnode.aoc2021.solution.Solution
import xyz.terminalnode.aoc2021.util.elfcode.Biterator

object Day16 : Solution(16, "Packet Decoder") {
  @Suppress("SameParameterValue")
  private fun parse(fileName: String) =
    readFile(fileName)
      .asSequence()
      .flatMap { hexDigit -> hexDigit
        .digitToInt(16)
        .toString(2)
        .padStart(4, '0')
        .toList()
      }.let { Biterator(it).parseNextPacket(null) }

  override fun partOne(): String = parse("day16.txt").sumVersion().toString()
  override fun partTwo(): String = parse("day16.txt").calculate().toString()
}