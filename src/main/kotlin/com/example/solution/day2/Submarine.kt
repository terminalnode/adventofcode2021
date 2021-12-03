package com.example.solution.day2

data class Submarine(
  var horizontalPos: Int = 0,
  var verticalPos: Int = 0,
) {
  private fun parseLine(line: String): Pair<String, Int> {
    val (direction, quantity) = line.split(" ")
    return Pair(direction, quantity.toInt())
  }

  fun readInstruction(line: String) {
    val (direction, quantity) = parseLine(line)
    when (direction) {
      "forward" -> horizontalPos += quantity
      "down" -> verticalPos += quantity
      "up" -> verticalPos -= quantity
    }
  }

  fun calculateResult() = horizontalPos * verticalPos
}