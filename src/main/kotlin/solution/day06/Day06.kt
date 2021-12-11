package xyz.terminalnode.aoc2021.solution.day06

import xyz.terminalnode.aoc2021.solution.Solution
import java.util.*

object Day06 : Solution(6, "Lanternfish") {
  override fun partOne() : String {
    val initialState = readFile("day06.txt")
      .split(",")
      .map { LanternFish(it.toInt()) }
    val fish = LinkedList(initialState)

    repeat(80) {
      val newFish = mutableListOf<LanternFish>()
      fish.forEach {
        if (it.tickReproduction()) {
          newFish.add(LanternFish())
        }
      }
      fish.addAll(newFish)
    }

    return fish.size.toString()
  }

  override fun partTwo() : String {
    val input = readFile("day06.txt")
      .split(",")
      .map { it.toInt() }
    val shoal = Shoal(input)

    repeat(256) {
      shoal.tick()
    }

    return shoal.sum().toString()
  }
}