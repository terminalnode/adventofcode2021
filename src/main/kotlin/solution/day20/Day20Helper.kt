package xyz.terminalnode.aoc2021.solution.day20

typealias Pixel = Char
typealias PixelEnhancer = List<Char>

const val lightPixel = '█'
const val darkPixel = '░'

fun charToPixel(c: Char): Pixel = when (c) {
  '#' -> lightPixel
  '.' -> darkPixel
  else -> throw IllegalStateException("Illegal input char: $c")
}

fun parse(lines: List<String>): PixelMatrixV2 {
  val enhancer = lines.first().map { charToPixel(it) }
  val matrix = lines.drop(2).map { line -> line.map { charToPixel(it) } }
  return PixelMatrixV2(enhancer, matrix)
}