package xyz.terminalnode.aoc2021.solution.day23

import java.util.*

data class AmphiRoom(
  val type: Char,
  private val amphis: LinkedList<Amphi>,
  private var popped: Int = 0,
) {
  fun canPop() = !amphis.isEmpty()
  fun peek() = amphis.peek()!!

  fun insert(pos: Int, newAmphis: Collection<Amphi>) {
    newAmphis.reversed().forEach {
      amphis.add(pos, it)
    }
  }

  /**
   * Return the next amphi to move and the cost for it to reach the hallway.
   * If all remaining amphis in the queue are the correct type, clear the queue. They shouldn't move.
   */
  fun pop() : Pair<Amphi, Long> {
    val amphi = amphis.pop()
    val cost = ++popped * amphi.weight
    if (amphis.all { it.type == type }) amphis.clear()
    return amphi to cost
  }

  /**
   * Return true if the suggested amphi is allowed to move in to this room at this time.
   */
  fun canTake(amphi: Amphi) = amphis.isEmpty() && amphi.type == type

  fun deepCopy() : AmphiRoom {
    val newAmphis = LinkedList(amphis.map { it.copy() })
    return AmphiRoom(type, newAmphis, popped)
  }

  fun size() = amphis.size

  /**
   * Increment internal consume counter and return the cost of moving this amphi in.
   */
  fun consume(amphi: Amphi): Long {
    if (amphi.type != type) throw IllegalStateException("$type room tried to consume ${amphi.type}")
    return (popped-- * amphi.weight)
  }

  override fun toString(): String {
    return if (amphis.isEmpty()) "√"
    else amphis.joinToString("") { it.type.toString() }
  }
}