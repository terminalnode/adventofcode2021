package xyz.terminalnode.aoc2021.solution.day23

data class Amphi(
  val type: Char,
  val weight: Long,
) {
  constructor(s: String) : this(s.first())
  constructor(c: Char) : this(c, charToWeight(c))
}