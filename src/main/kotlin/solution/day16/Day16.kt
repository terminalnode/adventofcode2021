package xyz.terminalnode.aoc2021.solution.day16

import xyz.terminalnode.aoc2021.solution.Solution
import xyz.terminalnode.aoc2021.util.elfcode.Biterator

fun List<Boolean>.toOneZeroString() = joinToString("") { if (it) "1" else "0" }

object Day16 : Solution(16, "Packet Decoder") {
  private const val type4Packet = "day16-test1.txt"
  private const val operatorPacket = "day16-test2.txt"

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
    val biterator = Biterator(parse(operatorPacket))
    val rootPacket = biterator.parseNextPacket(null)
    println("---")
    println(rootPacket)
    println(rootPacket.children.size)
    println("---")

    TODO("Not yet implemented")
  }

  override fun partTwo(): String {
    TODO("Not yet implemented")
  }
}