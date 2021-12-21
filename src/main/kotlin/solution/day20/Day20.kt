package xyz.terminalnode.aoc2021.solution.day20

import xyz.terminalnode.aoc2021.solution.Solution

private data class Player(
  val number: Int,
  var position: Int = 1,
  var points: Long = 0,
) {
  fun incrementPosition(die: Die) = incrementPosition(die.rollThreeTimes())
  fun incrementPosition(n: Int) {
    val oldPos = position
    val oldPoints = points
    position = (n - 1 + position) % 10 + 1
    points += position
  }
}

private class Die(wrapOn: Int = 100, var rolls: Int = 0) {
  val seq = generateSequence(1) { (it % 100) + 1 }.iterator()

  fun rollThreeTimes(): Int {
    rolls += 3
    return seq.next() + seq.next() + seq.next()
  }

  fun roll(): Int {
    rolls++
    return seq.next()
  }
}

object Day20 : Solution(20, "Dirac Dice") {
  private fun parse(fileName: String) = readLines(fileName)
    .mapIndexed { i, line -> line
      .last()
      .digitToInt()
      .let { Player(number = i + 1, position = it) }
    }.let { players ->
      generateSequence(players) { it }.flatten().iterator()
    }

  override fun partOne(): String {
    val players = parse("day20.txt")
    val die = Die()

    do {
      val player = players.next()
      player.incrementPosition(die)
    } while (player.points < 1000)
    val loser = players.next()

    val result = loser.points * die.rolls
    return "${loser.points} * ${die.rolls} = $result"
  }

  override fun partTwo(): String {
    TODO("Not yet implemented")
  }
}