package xyz.terminalnode.aoc2021.solution.day23

import xyz.terminalnode.aoc2021.solution.Solution
import java.util.*

object Day23 : Solution(23, "Amphipod") {
  @Suppress("SameParameterValue")
  private fun parse(fileName: String) : List<Pair<Amphi, Amphi>> {
    val (firstRow, secondRow) = readLines(fileName)
      .drop(2).take(2) // corridor ascii art
      .map { it.drop(3).take(7).split("#") } // take only A#B#C#D and split
    val (a1, b1, c1, d1) = firstRow.map { Amphi(it) }
    val (a2, b2, c2, d2) = secondRow.map { Amphi(it) }
    return listOf(a1 to a2, b1 to b2, c1 to c2, d1 to d2)
  }

  override fun partOne(): String {
    // test should be: 12521
    val rooms = parse("day23-test.txt")
      .zip('A'..'D')
      .associate { (amphis, type) ->
        val queue = LinkedList(amphis.toList())
        type to AmphiRoom(type, queue)
      }

    // Starting score is the movement points wasted going in to the correct rooms
    var hallways = listOf(Hallway(rooms))
    val finished = mutableSetOf<Hallway>()

    while (hallways.isNotEmpty()) {
      val newHallways = hallways.flatMap { it.branch() }
      newHallways.forEach {
        do { val moved = it.fromHallwayToRoom() || it.fromRoomToRoom() }
        while (moved)
      }

      hallways = newHallways.filter {
        if (it.isFinished()) finished.add(it)
        !it.isFinished()
      }
    }

    return finished.minOf { it.score }.toString()
  }

  override fun partTwo(): String {
    TODO("Not yet implemented")
  }
}