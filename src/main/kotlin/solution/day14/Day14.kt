package xyz.terminalnode.aoc2021.solution.day14

import xyz.terminalnode.aoc2021.solution.Solution
import java.util.*

typealias Index = Int
typealias Polymer = LinkedList<Char>
typealias Insertion = Pair<Index, Char>
typealias InsertionMap = Map<Char, Map<Char, Char>>

private fun InsertionMap.get(first: Char, second: Char) = get(first)?.get(second)

private fun MutableMap<Char, Long>.increment(c: Char) {
  this[c] = getOrDefault(c, 0) + 1
}

object Day14 : Solution(14, "Extended Polymerization") {
  @Suppress("SameParameterValue")
  private fun parse(fileName: String) : Pair<Polymer, InsertionMap> {
    val lines = readLines(fileName)
    val polymer = Polymer(lines[0].toList())

    val instructions = lines.drop(2)
      .groupBy { it.first() }
      .mapValues { (_, secondPairArrowInsertion) ->
        secondPairArrowInsertion.map {
          val (secondPair, insertion) = it.drop(1).split(" -> ")
          secondPair.first() to insertion.first()
        }.associate { it }
      }

    return polymer to instructions
  }

  private fun polymerize(polymer: Polymer, map: InsertionMap) {
    val insertions = mutableListOf<Insertion>()

    polymer.reduceIndexed { index, first, second ->
      val insert = map.get(first, second)
      if (insert != null) {
        insertions.add(index to insert)
      }

      return@reduceIndexed second
    }

    var indexOffset = 0
    insertions.forEach {
      polymer.add(indexOffset + it.first, it.second)
      indexOffset++
    }
  }

  private fun polymerizeAndCount(
    polymer: Polymer,
    map: InsertionMap,
    times: Int,
  ) : Map<Char, Long> {
    // Initialize count map
    val countMap = mutableMapOf<Char, Long>()
    polymer.forEach { countMap.increment(it) }

    // Initialize pair list
    val todo = LinkedList<Pair<Char, Char>>()
    polymer.reduce { previous, current ->
      todo.add(Pair(previous, current))
      return@reduce current
    }

    // Polymerize
    repeat(times) {
      println(it)
      val clone = todo.toList()
      todo.clear()

      clone.forEach { (first, second) ->
        val insert = map.get(first, second)
        if (insert != null) {
          countMap.increment(insert)
          todo.add(first to insert)
          todo.add(insert to second)
        }
      }
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
    //TODO("Solution still too slow...")
    val (polymer, instructions) = parse("day14.txt")
    val countMap = polymerizeAndCount(polymer, instructions, 40)
    return renderString(countMap)
  }
}