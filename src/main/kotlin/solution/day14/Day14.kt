package xyz.terminalnode.aoc2021.solution.day14

import xyz.terminalnode.aoc2021.solution.Solution
import java.util.*

typealias Index = Int
typealias Polymer = LinkedList<Char>
typealias Insertion = Pair<Index, Char>
typealias InsertionMap = Map<String, Char>

private fun InsertionMap.get(first: Char, second: Char) = this["$first$second"]

private fun MutableMap<String, Long>.increment(s: String, n: Long) {
  this[s] = getOrDefault(s, 0) + n
}

private fun MutableMap<Char, Long>.increment(c: Char, n: Long) {
  this[c] = getOrDefault(c, 0) + n
}

object Day14 : Solution(14, "Extended Polymerization") {
  @Suppress("SameParameterValue")
  private fun parse(fileName: String) : Pair<Polymer, InsertionMap> {
    val lines = readLines(fileName)
    val polymer = Polymer(lines[0].toList())

    val instructions = lines.drop(2)
      .associate {
        val (pair, char) = it.split(" -> ")
        pair to char.first()
      }

    return polymer to instructions
  }

  private fun polymerizeAndCount(
    polymer: Polymer,
    insertMap: InsertionMap,
    times: Int,
  ) : Map<Char, Long> {
    // Initialize count map
    val countMap = mutableMapOf<Char, Long>()
    polymer.forEach { countMap.increment(it, 1) }

    // Initialize pair list
    var pairMap = mutableMapOf<String, Long>()
    polymer.reduce { previous, current ->
      pairMap.increment("$previous$current", 1)
      return@reduce current
    }

    // Polymerize
    repeat(times) {
      val newMap = mutableMapOf<String, Long>()

      pairMap.forEach { (pair, count) ->
        val insert = insertMap[pair] ?: return@forEach
        countMap.increment(insert, count)

        val (first, second) = pair.toList()
        newMap.increment("$first$insert", count)
        newMap.increment("$insert$second", count)
      }

      pairMap = newMap
    }

    return countMap
  }

  private fun renderString(countMap: Map<Char, Long>) : String {
    val mostCommon = countMap.maxByOrNull { it.value }!!.key
    val leastCommon = countMap.minByOrNull { it.value }!!.key
    val mostCommonValue = countMap[mostCommon]!!
    val leastCommonValue = countMap[leastCommon]!!
    val diff = mostCommonValue - leastCommonValue
    return "Most common $mostCommon ($mostCommonValue) - least common $leastCommon ($leastCommonValue) = $diff"
  }

  override fun partOne() : String {
    val (polymer, instructions) = parse("day14.txt")
    val countMap = polymerizeAndCount(polymer, instructions, 10)
    return renderString(countMap)
  }

  override fun partTwo(): String {
    val (polymer, instructions) = parse("day14.txt")
    val countMap = polymerizeAndCount(polymer, instructions, 40)
    return renderString(countMap)
  }
}