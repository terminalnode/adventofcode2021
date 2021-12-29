package xyz.terminalnode.aoc2021.solution.day25

import xyz.terminalnode.aoc2021.util.MutableMatrix

typealias SeaBed = MutableMatrix<SeaCucumber?>

fun parseSeaBed(lines: List<String>): SeaBed =
  lines.mapIndexed { y, line ->
    line.mapIndexed { x, char ->
      when (char) {
        'v' -> SeaCucumber(x, y, SeaCucumberDirection.DOWN)
        '>' -> SeaCucumber(x, y, SeaCucumberDirection.RIGHT)
        '.' -> null
        else -> throw IllegalStateException("Encountered illegal char when parsing sea bed: $char")
      }
    }.toMutableList()
  }.toMutableList()

fun SeaBed.mimicInput(): String {
  return joinToString("\n") { line ->
    line.joinToString("") {
      when (it?.value) {
        SeaCucumberDirection.RIGHT -> ">"
        SeaCucumberDirection.DOWN -> "v"
        null -> "."
      }
    }
  }
}