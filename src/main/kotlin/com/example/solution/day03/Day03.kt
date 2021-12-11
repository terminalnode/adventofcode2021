package com.example.solution.day03

import com.example.solution.Solution
import kotlin.math.floor

object Day03 : Solution(3, "Binary Diagnostic") {
  override fun run(part: Int): String {
    return when (part) {
      1 -> partOne()
      2 -> partTwo()
      else -> TODO("Not yet implemented")
    }
  }

  private fun buildMatrix() = readLines("day03.txt")
    .map { line ->
      line.map { it.digitToInt() }
    }

  private fun partOne() : String {
    val matrix = buildMatrix()

    // Assuming each row in the matrix is equally long,
    // initialize a counter of that length with all zeroes
    val count = matrix.first().map { 0 }.toMutableList()

    matrix.forEach { row ->
      row.forEachIndexed { index, i ->
        count[index] += i
      }
    }

    // number.inv() is supposed to be binary NOT in kotlin, but it doesn't seem to work for some reason
    // So we have to invert the epsilon manually
    val cutOff = floor(matrix.size / 2.0)
    val gammaRate = count.map { if (it > cutOff) 1 else 0 }.joinToString("")
    val epsilonRate = gammaRate.map { if (it == '0') '1' else '0' }.joinToString("")

    return "${gammaRate.toInt(2) * epsilonRate.toInt(2)}"
  }

  private fun partTwo() : String {
    val matrix = buildMatrix()
    val a = matrix.filterByBitCriteria(true)
    val b = matrix.filterByBitCriteria(false)
    return "${a * b}"
  }

  private fun List<List<Int>>.filterByBitCriteria(keepMostCommon: Boolean) : Int {
    val rowLength = first().size
    var listCopy = map { it }

    while (listCopy.size > 1) {
      for (i in 0 until rowLength) {
        if (listCopy.size == 1) break

        val sum = listCopy.sumOf { it[i] }
        val cutOff = listCopy.size / 2.0
        val mostCommon = if (sum >= cutOff) 1 else 0

        listCopy = listCopy.filter {
          if (keepMostCommon) it[i] == mostCommon
          else it[i] != mostCommon
        }
      }
    }

    return listCopy.first().joinToString("").toInt(2)
  }
}