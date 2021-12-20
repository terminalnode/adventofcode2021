package xyz.terminalnode.aoc2021.solution.day19

import xyz.terminalnode.aoc2021.solution.Solution
import java.util.*

object Day19 : Solution(19, "Beacon Scanner") {
  private fun parse(lines: List<String>) : List<Scanner> {
    val scanners = mutableListOf<Scanner>()
    val iterator = lines.iterator()
    iterator.next() // skip first header line

    var beacons = mutableListOf<Beacon>()
    while (iterator.hasNext()) {
      val nextLine = iterator.next()
      if (nextLine.isBlank()) {
        iterator.next() // skip header line
        Scanner(beacons).also { scanners.add(it) }
        beacons = mutableListOf()
        continue
      }

      val (x,y,z) = nextLine.split(",").map { it.toLong() }
      beacons.add(Beacon(x, y, z))
    }

    return scanners
  }

  private fun parseFile(fileName: String) : Pair<Scanner, LinkedList<Scanner>> {
    val all = parse(readLines(fileName))
    val main = all.first()
    val rest = all.drop(1)
    return main to LinkedList(rest)
  }

  override fun partOne(): String {
    // 302 too low
    // 382 too high
    val (main, scanners) = parseFile("day19.txt")
    println("Scanners at start: ${scanners.size}")
    while (scanners.isNotEmpty()) {
      val scanner = scanners.pop()
      if (!main.findOverlap(scanner)) {
        scanners.add(scanner)
      } else {
        println("Main now has ${main.mainRotation.size} beacons :o")
        println(main.allRotations.map { it.size }.distinct())
      }
    }

    return main.mainRotation.size.toString()
  }

  override fun partTwo(): String {
    TODO("Not yet implemented")
  }
}