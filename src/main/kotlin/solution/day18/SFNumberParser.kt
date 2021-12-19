package xyz.terminalnode.aoc2021.solution.day18

fun SFNumber.parseValues(iterator: CharIterator) {
  pair = Pair(
    SFNumber(parent = this).also { it.getPairOrLiteral(iterator) },
    SFNumber(parent = this).also { it.getPairOrLiteral(iterator) },
  )
}

fun SFNumber.getPairOrLiteral(iterator: CharIterator) {
  when (val firstChar = iterator.nextChar()) {
    '[' -> parseValues(iterator)
    else -> literal = firstChar.digitToInt().toLong()
  }
  iterator.next() // drop finishing char (']' or ',')
}