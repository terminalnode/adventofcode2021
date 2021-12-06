package com.example.solution.day6

class Shoal(initial: List<Int>) {
  val fish = (1..9).map { 0L }.toMutableList()

  init {
    initial.forEach { fish[it]++ }
  }

  fun tick() {
    val newFish = fish[0]
    (1 until fish.size).forEach {
      fish[it - 1] = fish[it]
    }
    fish[6] += newFish
    fish[8] = newFish
  }

  fun sum() : Long = fish.sum()

  override fun toString(): String = "Shoal{${fish.joinToString(", ")}}"
}