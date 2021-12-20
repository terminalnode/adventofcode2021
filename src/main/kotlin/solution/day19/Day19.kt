package xyz.terminalnode.aoc2021.solution.day19

import xyz.terminalnode.aoc2021.solution.Solution
import java.util.*
import kotlin.math.abs

object Day19 : Solution(19, "Beacon Scanner") {
  private fun parse(lines: List<String>) : List<Scanner> {
    var num = 0
    val scanners = mutableListOf<Scanner>()
    val iterator = lines.iterator()
    iterator.next() // skip first header line

    var beacons = mutableListOf<Beacon>()
    while (iterator.hasNext()) {
      val nextLine = iterator.next()
      if (nextLine.isBlank()) {
        iterator.next() // skip header line
        Scanner(num++, beacons).also { scanners.add(it) }
        beacons = mutableListOf()
        continue
      }
      val (x,y,z) = nextLine.split(",").map { it.toLong() }
      beacons.add(Beacon(x, y, z))
    }
    Scanner(num, beacons).also { scanners.add(it) }

    return scanners
  }

  private fun parseFile(fileName: String) : Pair<Scanner, LinkedList<Scanner>> {
    val all = parse(readLines(fileName))
    val main = all.first()
    val rest = all.drop(1)
    return main to LinkedList(rest)
  }

  private fun alignSatellites(fileName: String): List<Scanner> {
    val (main, scanners) = parseFile(fileName)
    main.position = XYZOffset(0,0,0)
    val aligned = mutableListOf(main)

    while (scanners.isNotEmpty()) {
      val scanner = scanners.pop()
      aligned.any { it.findOverlap(scanner) }

      if (scanner.position == null) {
        scanners.add(scanner)  // Better luck next time
      } else {
        aligned.add(scanner)
      }
    }
    return aligned
  }

  override fun partOne() = alignSatellites("day19.txt").flatMap { it.mainRotation }.distinct().size.toString()

  override fun partTwo(): String {
    val positions = alignSatellites("day19.txt").map { it.position!! }
    val distances = mutableSetOf<Long>()
    positions.forEach { p1 ->
      positions.forEach { p2 ->
        val xDist = abs(p1.x - p2.x)
        val yDist = abs(p1.y - p2.y)
        val zDist = abs(p1.z - p2.z)
        distances.add(xDist + yDist + zDist)
      }
    }

    return distances.maxOrNull().toString()
  }
}