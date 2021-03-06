package xyz.terminalnode.aoc2021.solution.day10

import xyz.terminalnode.aoc2021.solution.Solution
import java.util.*

object Day10 : Solution(10, "Syntax Scoring") {
  private val openingBrackets = listOf('(', '[', '{', '<')

  private val matchingBrackets = mapOf(
    '(' to ')',
    '[' to ']',
    '{' to '}',
    '<' to '>')

  private val partOnePointMap = mapOf(
    ')' to 3,
    ']' to 57,
    '}' to 1197,
    '>' to 25137)

  private val partTwoPointMap = mapOf(
    ')' to 1,
    ']' to 2,
    '}' to 3,
    '>' to 4)

  override fun partOne() : String {
    val lines = readLines("day01-10/day10.txt").map { it.iterator() }
    var points = 0

    lines.forEach { line ->
      val expectedValues = LinkedList<Char>()

      while (line.hasNext()) {
        val char = line.nextChar()
        if (openingBrackets.contains(char)) {
          expectedValues.addFirst(matchingBrackets[char])
        } else {
          val expected = expectedValues.pop()
          if (char != expected) {
            points += partOnePointMap[char] ?: throw IllegalStateException("Missing points for char $char")
          }
        }
      }
    }

    return points.toString()
  }

  override fun partTwo() : String {
    val lines = readLines("day01-10/day10.txt").map { it.iterator() }
    val scores = mutableListOf<Long>()

    lines.forEach { line ->
      var scoreLine = true
      val expectedValues = LinkedList<Char>()

      while (line.hasNext()) {
        val char = line.nextChar()

        if (openingBrackets.contains(char)) {
          expectedValues.addFirst(matchingBrackets[char])
        } else {
          val expected = expectedValues.pop()
          if (char != expected) {
            scoreLine = false
            break
          }
        }
      }

      if (scoreLine) {
        scores.add(scorePartTwo(expectedValues))
      }
    }

    return scores.sorted().drop(scores.size / 2).first().toString()
  }

  private fun scorePartTwo(chars: Iterable<Char>) : Long {
    return chars.fold(0) { acc, char ->
      return@fold acc * 5 + partTwoPointMap[char]!!
    }
  }
}