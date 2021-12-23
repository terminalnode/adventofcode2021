package xyz.terminalnode.aoc2021.solution.day23

import kotlin.math.pow

fun charToWeight(c: Char): Int {
  if (c !in 'A'..'D') throw IllegalStateException("Invalid amphi type: $c")
  return 10.0.pow(c.code - 65).toInt()
}

fun charToNodeIndex(c: Char) = when (c) {
  'A' -> 2
  'B' -> 4
  'C' -> 6
  'D' -> 8
  else -> throw IllegalStateException("Invalid amphi type $c")
}

fun printHallway(hallway: Hallway) {
  print("Hallway: ")
  hallway.nodes.forEach { print(it.amphi?.type ?: ".") }
  println()

  val rooms = hallway.rooms.map { (char, room) -> "Room $char: $room" }.joinToString(" | ")
  println(rooms)
}