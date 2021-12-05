package com.example.solution.day5

data class Point(
  val x: Int,
  val y: Int,
) {
  override fun toString(): String = "$x,$y"
}