package com.example.solution.day2

data class SubmarineMK2(
  var horizontalPos: Int = 0,
  var verticalPos: Int = 0,
  var aim: Int = 0,
) {
  private fun parseLine(line: String): Pair<String, Int> {
    val (direction, quantity) = line.split(" ")
    return Pair(direction, quantity.toInt())
  }

  fun readInstruction(line: String) {
    val (direction, quantity) = parseLine(line)
    when (direction) {
      "forward" -> {
        horizontalPos += quantity
        verticalPos += aim * quantity
      }
      "down" -> aim += quantity
      "up" -> aim -= quantity
    }
  }

  fun calculateResult() = horizontalPos * verticalPos
}