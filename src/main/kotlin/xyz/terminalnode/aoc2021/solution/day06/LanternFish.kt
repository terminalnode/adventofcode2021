package xyz.terminalnode.aoc2021.solution.day06

data class LanternFish(
  var currentDay: Int = 0,
  var daysLeft: Int = 8,
  val cycleLength: Int = 6,
) {
  fun tickReproduction() : Boolean {
    daysLeft--
    return if (daysLeft < 0) {
      daysLeft = cycleLength
      true
    } else {
      false
    }
  }

  fun newFishDaysUntil(day: Int) : Sequence<LanternFish> {
    return generateSequence(currentDay + daysLeft) { it + cycleLength }.map { LanternFish(currentDay = it) }
  }
}