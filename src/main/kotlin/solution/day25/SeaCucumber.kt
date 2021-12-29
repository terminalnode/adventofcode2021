package xyz.terminalnode.aoc2021.solution.day25

import xyz.terminalnode.aoc2021.util.Point
import xyz.terminalnode.aoc2021.util.get
import xyz.terminalnode.aoc2021.util.set

enum class SeaCucumberDirection {
  RIGHT,
  DOWN,
}

typealias SeaCucumber = Point<SeaCucumberDirection>

fun SeaCucumber.move(seaBed: SeaBed) {
  val nextPos = getNextPos(seaBed).also { it.value = value }
  seaBed.set(x, y, null)
  seaBed.set(nextPos.x, nextPos.y, nextPos)
}

fun SeaCucumber.canMove(seaBed: SeaBed): Boolean =
  getNextPos(seaBed).let { seaBed.get(it.x, it.y) } == null

fun SeaCucumber.getNextPos(seaBed: SeaBed): SeaCucumber =
  when (value) {
    SeaCucumberDirection.RIGHT -> getRightPos(seaBed)
    SeaCucumberDirection.DOWN -> getDownPos(seaBed)
    null -> throw IllegalStateException("Sea Cucumber is missing direction! $this")
  }

fun SeaCucumber.getRightPos(seaBed: SeaBed): SeaCucumber {
  val maxX = seaBed[0].size - 1
  return if (maxX < x + 1) SeaCucumber(0, y)
  else SeaCucumber(x + 1, y)
}

fun SeaCucumber.getDownPos(seaBed: SeaBed): SeaCucumber {
  val maxY = seaBed.size - 1
  return if (maxY < y + 1) SeaCucumber(x, 0)
  else SeaCucumber(x, y + 1)
}
