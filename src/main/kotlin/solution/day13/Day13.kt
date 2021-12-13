package xyz.terminalnode.aoc2021.solution.day13

import xyz.terminalnode.aoc2021.solution.Solution
import xyz.terminalnode.aoc2021.util.*

typealias Dot = Point<Char>
fun newDot(x: Int, y: Int, value: Char = '#') = Dot(x, y, value)

fun Iterable<Dot>.applyInstruction(instruction: FoldInstruction) =
  map { dot ->
    when (instruction.axis) {
      XYAxis.X -> {
        if (dot.x < instruction.value) {
          dot
        } else {
          val newValue = instruction.value - (dot.x - instruction.value)
          newDot(x = newValue, y = dot.y)
        }
      }
      XYAxis.Y -> {
        if (dot.y < instruction.value) {
          dot
        } else {
          val newValue = instruction.value - (dot.y - instruction.value)
          newDot(x = dot.x, y = newValue)
        }
      }
    }
  }.toSet()

fun Iterable<Dot>.gridString() : String {
  val maxX = maxOf { it.x }
  val maxY = maxOf { it.y }
  val matrix: MutableMatrix<Dot> = (0..maxY).map { y ->
    (0..maxX).map { x -> newDot(x, y, '.') }.toMutableList()
  }.toMutableList()

  forEach { matrix.set(it.x, it.y, it) }
  return matrix.valuesToString()
}

object Day13 : Solution(13, "Transparent Origami") {
  @Suppress("SameParameterValue")
  private fun parse(fileName: String) : Pair<List<Dot>, List<FoldInstruction>> {
    val input = readLines(fileName)

    val points = input
      .takeWhile { it.isNotBlank() }
      .map { line ->
        val (x, y) = line.split(",").map { it.toInt() }
        newDot(x, y)
      }

    val instructions = input
      .dropWhile { it.isNotBlank() }
      .drop(1)
      .map { FoldInstruction.parseLine(it) }

    return points to instructions
  }

  override fun partOne(): String {
    val (dots, instructions) = parse("day13.txt")
    val firstInstruction = instructions.first()

    val folded = dots.applyInstruction(firstInstruction)
    return "Number of dots after first fold: ${folded.size}"
  }

  override fun partTwo(): String {
    val (dots, instructions) = parse("day13.txt")
    val folded = instructions.fold(dots.toSet()) { latestDots, instruction ->
      latestDots.applyInstruction(instruction)
    }

    val gridString = folded.gridString()
    println(gridString)
    return gridString
  }
}