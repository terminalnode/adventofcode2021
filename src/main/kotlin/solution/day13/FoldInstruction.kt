package xyz.terminalnode.aoc2021.solution.day13

import xyz.terminalnode.aoc2021.util.XYAxis

data class FoldInstruction(
  val axis: XYAxis,
  val value: Int,
) {
  companion object {
    fun parseLine(line: String): FoldInstruction {
      val (letter, number) = line.removePrefix("fold along ").split("=")
      val axis = XYAxis.valueOf(letter.uppercase())
      val value = number.toInt()
      return FoldInstruction(axis, value)
    }
  }

  override fun toString() = "fold along ${axis.toString().lowercase()}=$value"
}