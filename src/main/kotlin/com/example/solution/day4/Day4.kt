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
    val lines = readLines("day4.txt").iterator()
    val numbers = lines.next().split(",").map { it.toInt() }.iterator()
    var boards = BingoBoard.generateBoards(lines)

    var number = -1
    while (numbers.hasNext() && !boards.first().checkBoard()) {
      number = numbers.next()
      boards.forEach { it.mark(number) }

      if (boards.size > 1) {
        // We need to play the game until the last board has won, but we don't want to filter
        // out the last board. So we only trigger this if we have more than one board.
        boards = boards.filter { !it.checkBoard() }
      }
    }

    val winner = boards.first()
    return winner.score(number)
  }
}