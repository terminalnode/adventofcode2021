package com.example.solution.day2

import com.example.solution.Solution

object Day2 : Solution(2) {
  override fun run(part: Int): Any {
    return when (part) {
      1 -> partOne()
      2 -> partTwo()
      else -> TODO("Not yet implemented")
    }
  }

  fun partOne() : Any {
    val submarine = Submarine()
    readLines("day2part1.txt").forEach { submarine.readInstruction(it) }
    return submarine.calculateResult()
  }

  fun partTwo() : Any {
    val submarine = SubmarineMK2()
    readLines("day2part1.txt").forEach { submarine.readInstruction(it) }
    return submarine.calculateResult()
  }
}