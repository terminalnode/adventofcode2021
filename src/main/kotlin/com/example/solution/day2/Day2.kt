package com.example.solution.day2

import com.example.solution.Solution

object Day2 : Solution(2) {
  override fun run(part: Int): Any {
    return when (part) {
      1 -> partOne()
      else -> TODO("Not yet implemented")
    }
  }

  fun partOne() : Any {
    val submarine = Submarine()
    readLines("day2part1.txt").forEach { submarine.readInstruction(it) }
    return submarine.calculateResult()
  }
}