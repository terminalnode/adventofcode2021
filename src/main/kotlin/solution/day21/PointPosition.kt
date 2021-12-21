package xyz.terminalnode.aoc2021.solution.day21

data class PointPosition(
  var points: Long,
  var position: Int,
) {
  private fun incrementPosition(n: Int) {
    position = (n - 1 + position) % 10 + 1
    points += position
  }

  fun copyIncrementPosition(n: Int) = copy().also { it.incrementPosition(n) }
}