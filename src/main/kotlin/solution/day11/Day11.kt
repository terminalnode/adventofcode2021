package xyz.terminalnode.aoc2021.solution.day11

import xyz.terminalnode.aoc2021.solution.Solution
import xyz.terminalnode.aoc2021.util.*

private typealias Octopus = Point<Int>
private typealias OctoMatrix = Matrix<Octopus>

fun Octopus.increment() {
  this.value = this.value!! + 1
}

fun Octopus.flash(matrix: OctoMatrix) : Int {
  if (this.value!! <= 9) {
    return 0
  }
  var flashes = 1
  this.value = 0

  getNeighbors().forEach { neighbor ->
    val octopus = matrix.getOrNull(neighbor) ?: return@forEach
    if (octopus.value != 0) {
      octopus.increment()
      flashes += octopus.flash(matrix)
    }
  }
  return flashes
}

fun OctoMatrix.increment() = forEachPoint { octopus -> octopus.increment() }

fun OctoMatrix.flash() : Int {
  var flashes = 0

  do {
    val newFlashes = mapEachPoint {
      it.flash(this)
    }.sum()

    flashes += newFlashes
  } while (newFlashes > 0)

  return flashes
}

fun OctoMatrix.print() = joinToString("\n") { row ->
  row.map { it.value!! }.joinToString("")
}.run { println(this) }

object Day11 : Solution(11, "Dumbo Octopus") {
  private fun getOctoMatrix() = readLines("day11.txt").mapIndexed { y, row ->
    row.mapIndexed { x, char ->
      Octopus(x, y, char.digitToInt())
    }
  }

  override fun partOne(): String {
    val matrix = getOctoMatrix()
    return (1..100).sumOf {
      matrix.increment()
      matrix.flash()
    }.toString()
  }

  override fun partTwo(): String {
    val matrix = getOctoMatrix()
    val target = matrix.countPoints()
    var step = 0

    while (true) {
      step++
      matrix.increment()
      val flashes = matrix.flash()
      if (flashes == target) {
        return step.toString()
      }
    }
  }
}