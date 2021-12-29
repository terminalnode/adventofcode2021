package xyz.terminalnode.aoc2021.solution.day21

import xyz.terminalnode.aoc2021.solution.Solution
import kotlin.math.max

private data class Universe(
  val p1: Player,
  val p2: Player,
)

private data class Player(
  var position: Int = 1,
  var points: Long = 0,
) {
  fun incrementPosition(die: Die) = incrementPosition(die.rollThreeTimes())
  fun incrementPosition(n: Int) {
    position = (n - 1 + position) % 10 + 1
    points += position
  }

  fun copyIncrementPosition(n: Int) = copy().also { it.incrementPosition(n) }

  fun toPointPosition() = PointPosition(points, position)
}

private class Die(var rolls: Int = 0) {
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

object Day21 : Solution(21, "Dirac Dice") {
  @Suppress("SameParameterValue")
  private fun parse(fileName: String) = readLines(fileName)
    .map { line -> line
      .last()
      .digitToInt()
      .let { Player(position = it) }
    }.let { players ->
      generateSequence(players) { it }.flatten().iterator()
    }

  override fun partOne(): String {
    val players = parse("day21.txt")
    val die = Die()

    do {
      val player = players.next()
      player.incrementPosition(die)
    } while (player.points < 1000)
    val loser = players.next()

    val result = loser.points * die.rolls
    return "${loser.points} * ${die.rolls} = $result"
  }

  private val possibleDice = (1..3).flatMap { firstDie ->
    (1..3).flatMap { secondDie ->
      (1..3).map { thirdDie ->
        firstDie + secondDie + thirdDie
      }
    }
  }.groupBy { it }.mapValues { it.value.size }

  private fun MutableMap<Universe, Long>.increment(universe: Universe, n: Long) {
    this[universe] = getOrDefault(universe, 0) + n
  }

  override fun partTwo(): String {
    // I barely understand how this shit works lol, my head is spinning.
    val playerIterator = parse("day21.txt")
    val initUniverse = Universe(playerIterator.next(), playerIterator.next())
    var universeMap = mutableMapOf(initUniverse to 1L)

    var p1Wins = 0L
    var p2Wins = 0L
    while (universeMap.isNotEmpty()) {
      val newUniverseMap = mutableMapOf<Universe, Long>()
      universeMap.forEach { (universe, universeNumber) ->
        val loserUniverses = possibleDice.mapNotNull { (dice, count) ->
          val newP1 = universe.p1.copyIncrementPosition(dice)
          if (newP1.points >= 21) {
            p1Wins += count * universeNumber
            return@mapNotNull null
          }
          return@mapNotNull Universe(newP1, universe.p2) to universeNumber * count
        }

        loserUniverses.forEach { (universe, universeNumber) ->
          possibleDice.forEach { (dice, count) ->
            val newP2 = universe.p2.copyIncrementPosition(dice)
            if (newP2.points >= 21) {
              p2Wins += count * universeNumber
            } else {
              val newUniverse = Universe(universe.p1, newP2)
              newUniverseMap.increment(newUniverse, universeNumber * count)
            }
          }
        }
      }

      universeMap = newUniverseMap
    }

    return "Player 1: $p1Wins | Player 2: $p2Wins | Max: ${max(p1Wins, p2Wins)}"
  }
}