package com.example.solution.day6

data class LanternFish(
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
}