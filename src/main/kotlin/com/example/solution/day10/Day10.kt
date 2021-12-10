package com.example.solution.day10

import com.example.solution.Solution
import java.util.*

object Day10 : Solution(10, "Syntax Scoring") {
  override fun run(part: Int): String {
    return when (part) {
      1 -> partOne()
      else -> TODO("Not yet implemented")
    }
  }

  private val matchingBrackets = mapOf(
    '(' to ')',
    '[' to ']',
    '{' to '}',
    '<' to '>')

  private val openingBrackets = listOf('(', '[', '{', '<')

  private val pointMap = mapOf(
    ')' to 3,
    ']' to 57,
    '}' to 1197,
    '>' to 25137)

  private fun partOne() : String {
    val lines = readLines("day10.txt").map { it.iterator() }
    val expectedValues = LinkedList<Char>()
    var points = 0

    lines.forEach { line ->
      while (line.hasNext()) {
        val char = line.nextChar()
        if (openingBrackets.contains(char)) {
          expectedValues.addFirst(matchingBrackets[char])
        } else {
          val expected = expectedValues.pop()
          if (char != expected) {
            points += pointMap[char] ?: throw IllegalStateException("Missing points for char $char")
          }
        }
      }
    }

    return points.toString()
  }
}