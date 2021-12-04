package com.example.solution.day4

import com.example.solution.Solution

object Day4 : Solution(4) {
  override fun run(part: Int): Any {
    return when (part) {
      1 -> partOne()
      2 -> partTwo()
      else -> TODO("Not yet implemented")
    }
  }

  fun partOne() : Any {
    val lines = readLines("day4.txt").iterator()
    val numbers = lines.next().split(",").map { it.toInt() }.iterator()
    val boards = BingoBoard.generateBoards(lines)

    var number = -1
    while (numbers.hasNext() && boards.none { it.checkBoard() }) {
      number = numbers.next()
      boards.forEach { it.mark(number) }
    }

    val winner = boards.first { it.checkBoard() }
    return winner.score(number)
  }

  fun partTwo() : Any {
    return ""
  }
}