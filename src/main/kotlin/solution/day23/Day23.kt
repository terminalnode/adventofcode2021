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

  @Suppress("SameParameterValue")
  private fun parseHallway(fileName: String) =
    parse(fileName).zip('A'..'D')
      .associate { (amphis, type) ->
        val queue = LinkedList(amphis.toList())
        type to AmphiRoom(type, queue)
      }.let { Hallway(it) }

  private fun solve(hallway: Hallway) : String {
    var hallways = listOf(hallway)
    val finished = mutableSetOf<Hallway>()

    while (hallways.isNotEmpty()) {
      hallways = hallways
        .flatMap { it.branch() }
        .filter {
          if (it.isFinished()) finished.add(it)
          !it.isFinished()
        }
    }

    return finished.minOf { it.score }.toString()
  }

  override fun partOne() = solve(parseHallway("day23.txt"))

  override fun partTwo(): String {
    val hallway = parseHallway("day23.txt")
    hallway.rooms['A']!!.insert(1, listOf(Amphi("D"), Amphi("D")))
    hallway.rooms['B']!!.insert(1, listOf(Amphi("C"), Amphi("B")))
    hallway.rooms['C']!!.insert(1, listOf(Amphi("B"), Amphi("A")))
    hallway.rooms['D']!!.insert(1, listOf(Amphi("A"), Amphi("C")))
    return solve(hallway)
  }
}