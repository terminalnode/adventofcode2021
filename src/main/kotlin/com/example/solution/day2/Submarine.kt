package com.example.solution.day2

data class Submarine(
  var horizontalPos: Int = 0,
  var verticalPos: Int = 0,
) {
  fun readInstruction(line: String) {
    val (direction, quantity) = line.split(" ")
    when (direction) {
      "forward" -> horizontalPos += quantity.toInt()
      "down" -> verticalPos += quantity.toInt()
      "up" -> verticalPos -= quantity.toInt()
    }
  }

  fun calculateResult() = horizontalPos * verticalPos
}