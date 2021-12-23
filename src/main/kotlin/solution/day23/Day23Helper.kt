package xyz.terminalnode.aoc2021.solution.day23

import kotlin.math.abs
import kotlin.math.pow

fun charToWeight(c: Char): Long {
  if (c !in 'A'..'D') throw IllegalStateException("Invalid amphi type: $c")
  return 10.0.pow(c.code - 65).toLong()
}

fun charToNodeIndex(c: Char) = when (c) {
  'A' -> 2
  'B' -> 4
  'C' -> 6
  'D' -> 8
  else -> throw IllegalStateException("Invalid amphi type $c")
}

fun printHallway(hallway: Hallway) {
  println("Hallway: ")
  hallway.nodes.forEachIndexed { i, _ -> print(i.toString().last()) }
  println()
  hallway.nodes.forEach { print(it.amphi?.type ?: ".") }
  println("\n  U U U U")
  println()

  val rooms = hallway.rooms.map { (char, room) -> "Room $char: $room" }.joinToString(" | ")
  println(rooms)
}

fun pathFromTo(origin: Int, target: Int) =
  if (origin < target) (origin..target).drop(1)
  else (target..origin).reversed().drop(1)

fun distance(origin: Int, target: Int) = abs(origin - target)