package xyz.terminalnode.aoc2021.solution.day16

import xyz.terminalnode.aoc2021.solution.Solution
import xyz.terminalnode.aoc2021.util.elfcode.Biterator

fun List<Boolean>.toOneZeroString() = joinToString("") { if (it) "1" else "0" }

object Day16 : Solution(16, "Packet Decoder") {
  private fun parse(fileName: String) =
    readFile(fileName)
      .asSequence()
      .flatMap { hexDigit -> hexDigit
        .digitToInt(16)
        .toString(2)
        .padStart(4, '0')
        .toList()
      }

  override fun partOne(): String {
    val biterator = Biterator(parse("day16.txt"))
    val rootPacket = biterator.parseNextPacket(null)
    return rootPacket.sumVersion().toString()
  }

  override fun partTwo(): String {
    TODO("Not yet implemented")
  }
}