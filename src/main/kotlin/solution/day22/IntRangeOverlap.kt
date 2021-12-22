package xyz.terminalnode.aoc2021.solution.day22

import kotlin.math.max
import kotlin.math.min

fun IntRange.hasOverlap(other: IntRange) : Boolean {
  val otherInThis = other.first in this || other.last in this
  val thisInOther = first in other || last in other
  return otherInThis || thisInOther
}

fun IntRange.getOverlap(other: IntRange) : IntRange {
  val first = max(first, other.first)
  val last = min(last, other.last)
  return first..last
}

fun IntRange.getSurroundingRanges(other: IntRange) : List<IntRange> {
  // Rule of thumb:
  // Any number in the other range should be in a single range.
  // That way it will match the other block.

  if (first in other && last in other) {
    // This set is completely enclosed in the other set
    return listOf(this)
  }

  if (other.first in this && other.last in this) {
    // The other set is enclosed in this set
    val otherStartInside = other.first > first
    val otherEndInside = other.last < last
    return when {
      otherStartInside && otherEndInside -> listOf(
        first until other.first,
        other,
        other.last + 1..last)
      otherStartInside -> listOf(
        first until other.first - 1,
        other.first..last)
      otherEndInside -> listOf(
        first..other.last,
        other.last + 1..last)
      else -> listOf(this) // shouldn't happen, this would mean this set is completely enclosed
    }
  }

  if (other.first in this) {
    return listOf(
      first until other.first,
      other.first..last)
  }

  if (other.last in this) {
    return listOf(
      first..other.last,
      other.last + 1..last
    )
  }

  return listOf()
}