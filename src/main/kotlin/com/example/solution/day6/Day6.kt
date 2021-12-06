package com.example.solution.day6

import com.example.solution.Solution
import java.util.*

object Day6 : Solution(6) {
  override fun run(part: Int): Any {
    return when (part) {
      1 -> partOne()
      else -> TODO("Not yet implemented")
    }
  }

  fun partOne() : Any {
    val initialState = readFile("day6.txt")
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

    return fish.size
  }
}