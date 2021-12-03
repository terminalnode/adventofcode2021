package com.example.solution.day1

import com.example.solution.Solution
import java.util.*

object Day1 : Solution(1) {
  override fun run(part: Int) =
    when (part) {
      1 -> partOne()
      2 -> partTwo()
      else -> TODO("Not yet implemented")
    }

  private fun partOne(): Any {
    val depthIterator = readLines("/day1part1.txt").map { it.toInt() }.iterator()
    var previousValue = depthIterator.next()
    var count = 0

    depthIterator.forEachRemaining {
      if (it > previousValue) count++
      previousValue = it
    }

    return count
  }

  private fun partTwo(): Any {
    val depthIterator = readLines("/day1part1.txt").map { it.toInt() }.iterator()
    val window = LinkedList(listOf(depthIterator.next(), depthIterator.next(), depthIterator.next()))
    var previousValue = window.sum()
    var count = 0

    depthIterator.forEachRemaining {
      window.removeFirst()
      window.addLast(it)
      val newValue = window.sum()

      if (newValue > previousValue) count++
      previousValue = newValue
    }

    return count
  }
}