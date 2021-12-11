package xyz.terminalnode.aoc2021.solution.day04

data class BingoCell(
  val value: Int,
  var isMarked: Boolean = false,
) {
  fun mark(number: Int) {
    if (value == number) isMarked = true
  }
}

data class BingoBoard(
  val boardNumber: Int,
  val matrix: List<List<BingoCell>>,
) {
  companion object {
    fun generateBoards(input: Iterator<String>) : List<BingoBoard> {
      val boards = mutableListOf<BingoBoard>()

      while (input.hasNext()) {
        input.next() // skip empty line
        val boardData = (1..5).map {
          input
            .next().trim()
            .split(Regex("\\s+"))
            .map { BingoCell(it.toInt()) }
        }
        boards.add(BingoBoard(boardNumber = boards.size + 1, matrix = boardData))
      }

      return boards
    }
  }

  fun score(lastNumber: Int) : Int {
    val boardSum = matrix.sumOf { row ->
      row.filter { !it.isMarked }.sumOf { it.value }
    }

    return boardSum * lastNumber
  }

  fun mark(number: Int) {
    matrix.flatten().forEach { it.mark(number) }
  }

  fun checkBoard() = checkHorizontal() || checkVertical() //|| checkDiagonals()

  private fun checkHorizontal(): Boolean =
    matrix.any { row -> row.all { it.isMarked } }

  private fun checkVertical(): Boolean =
    (0..4).any { index -> matrix.map { it[index] }.all { it.isMarked } }

  private fun checkDiagonals(): Boolean {
    val (diagonal1, diagonal2) = (0..4).map {
      val c1 = 0 + it
      val c2 = 4 - it
      Pair(matrix[c1][c1], matrix[c1][c2])
    }.unzip()

    return diagonal1.all { it.isMarked } || diagonal2.all { it.isMarked }
  }
}