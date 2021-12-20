package xyz.terminalnode.aoc2021.solution.day19

import xyz.terminalnode.aoc2021.solution.Solution
import java.util.*

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
    println("---")
    println(all.size)
    val main = all.first()
    val rest = all.drop(1)
    return main to LinkedList(rest)
  }

  override fun partOne(): String {
    // 302, 303 < x < 382
    val (main, scanners) = parseFile("day19.txt")
    main.position = XYZOffset(0,0,0)
    val aligned = mutableListOf(main)

    println("Scanners in list: ${scanners.size}")
    while (scanners.isNotEmpty()) {
      val scanner = scanners.pop()
      aligned.any { it.findOverlap(scanner) }

      if (scanner.position == null) {
        // Better luck next time
        scanners.add(scanner)
      } else {
        println(scanners.size)
        aligned.add(scanner)
      }
    }
    println(aligned.joinToString("\n") { "scanner ${it.number}: ${it.position}" })

    return aligned.flatMap { it.mainRotation }.distinct().size.toString()
  }

  override fun partTwo(): String {
    TODO("Not yet implemented")
  }
}