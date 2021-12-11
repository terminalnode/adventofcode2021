package xyz.terminalnode.aoc2021.solution.day08

import xyz.terminalnode.aoc2021.solution.Solution

object Day08 : Solution(8, "Seven Segment Search") {
  override fun partOne() : String {
    val input = readLines("day08.txt")
      .map { line -> line.split(" | ").map { side -> side.split(" ") } }
      .map { it[1] }
    val ones = input.sumOf { line -> line.count { code -> code.length == 2 } }
    val fours = input.sumOf { line -> line.count { code -> code.length == 4 } }
    val sevens = input.sumOf { line -> line.count { code -> code.length == 3 } }
    val eights = input.sumOf { line -> line.count { code -> code.length == 7 } }
    return "${ones + fours + sevens + eights}"
  }

  override fun partTwo() : String {
    /**
     * Structure of the seven segment display:
     *               aaaa
     *              b    c
     *              b    c
     *               dddd
     *              e    f
     *              e    f
     *               gggg
     */

    return readLines("day08.txt")
      .map { line ->
        val sides = line.split(" | ").map { side -> side.split(" ") }
        Pair(sides[0], sides[1])
      }.sumOf { pair ->
        val control = pair.first.map { it.toList().sorted() }
        val result = pair.second.map { it.toList().sorted() }

        // Simple digits
        val one = control.first { code -> code.size == 2 }.toList().sorted()
        val four = control.first { code -> code.size == 4 }.toList().sorted()
        val seven = control.first { code -> code.size == 3 }.toList().sorted()
        val eight = control.first { code -> code.size == 7 }.toList().sorted()

        // Sets of digits
        val twoThreeFive = control.filter { it.size == 5 }
        val sixNineZero = control.filter { it.size == 6 }

        // Deduce zero, b and d
        val bd = four.filter { !one.contains(it) }
        val zero = sixNineZero.first {
          val containsOne = it.contains(bd[0])
          val containsTwo = it.contains(bd[1])
          containsOne.xor(containsTwo)
        }
        val b = bd.first { zero.contains(it) }
        val d = bd.first { it != b }

        // Deduce the rest from this
        val six = sixNineZero.first { it.contains(one[0]).xor(it.contains(one[1])) }
        val three = twoThreeFive.first { it.containsAll(one) }
        val nine = sixNineZero.first { it.contains(d) && it.containsAll(one) }
        val five = twoThreeFive.first { it.contains(b) }
        val two = twoThreeFive.first { !it.containsAll(three) && !it.containsAll(five) }

        val newMap = mapOf(
          one to 1, two to 2, three to 3,
          four to 4, five to 5, six to 6,
          seven to 7, eight to 8, nine to 9,
          zero to 0)

        result
          .map { newMap[it]!! }
          .joinToString("")
          .toInt()
      }.toString()
  }
}