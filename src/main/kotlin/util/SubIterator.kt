package xyz.terminalnode.aoc2021.util

class SubIterator<T>(
  private val limit: Long,
  val iterator: Iterator<T>
) : Iterator<T> {
  private var iterations = 0

  override fun hasNext() = iterator.hasNext() && iterations <= limit

  override fun next(): T {
    if (iterations > limit) {
      println("Eek")
      throw NoSuchElementException("This limited iterator is finished!")
    }
    return iterator.next()
  }
}