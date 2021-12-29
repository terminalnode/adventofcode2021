package xyz.terminalnode.aoc2021.solution.day20

import xyz.terminalnode.aoc2021.util.Matrix

private typealias InternalMatrix = Matrix<Char>

class PixelMatrixV2(
  private val enhancer: PixelEnhancer,
  private var matrix: InternalMatrix,
  private var currentBackground: Pixel = darkPixel,
) {
  @Suppress("FunctionName")
  fun `ENHANCE!!!`() {
    val maxX = matrix.first().size
    val maxY = matrix.size

    matrix = (-1  .. maxY).map { y ->
      (-1 .. maxX).map { x ->
        enhancer[evaluatePixel(x, y)]
      }
    }

    evaluateNewBackground()
  }

  fun countLit(): Int = matrix.sumOf { line -> line.count { it == lightPixel } }

  private fun evaluatePixel(x: Int, y: Int) : Int =
    (-1..1).flatMap { yOffset ->
      (-1..1).map { xOffset ->
        getAsDigit(x + xOffset, y + yOffset)
      }
    }.joinToString("").toInt(2)

  private fun getAsDigit(x: Int, y: Int) : Char {
    val pixel = matrix.getOrNull(y)?.getOrNull(x) ?: currentBackground
    return if (pixel == lightPixel) '1' else '0'
  }

  private fun evaluateNewBackground() {
    currentBackground = enhancer[evaluatePixel(-10, -10)]
  }

  override fun toString() = matrix.joinToString("\n") { it.joinToString("") } + "\n"
}