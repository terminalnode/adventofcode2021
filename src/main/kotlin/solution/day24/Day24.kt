package xyz.terminalnode.aoc2021.solution.day24

import xyz.terminalnode.aoc2021.solution.Solution

typealias Combination = List<Long>

object Day24 : Solution(24, "Arithmetic Logic Unit") {
  @Suppress("SameParameterValue")
  private fun parseFile(fileName: String) = readLines(fileName).map { parseLine(it) }

  private fun parseLine(line: String): ALUStatement {
    val split = line.split(" ")
    val instruction = ALUInstruction.valueOf(split[0].uppercase())
    return when (split.size) {
      2 -> ALUStatement(instruction, split[1])
      3 -> ALUStatement(instruction, split[1], split[2])
      else -> throw IllegalStateException("Line '$line' has too many values to store in an ALUStatement")
    }
  }

  private fun splitStatementsByInput(inStatements: List<ALUStatement>) : List<List<ALUStatement>> {
    val statements = inStatements.iterator()
    var currentSet = mutableListOf(statements.next())
    val statementSets = mutableListOf<MutableList<ALUStatement>>()

    while (statements.hasNext()) {
      val next = statements.next()
      if (next.instruction == ALUInstruction.INP) {
        statementSets.add(currentSet)
        currentSet = mutableListOf(next)
      } else {
        currentSet.add(next)
      }
    }

    statementSets.add(currentSet)
    return statementSets
  }

  private fun getNegateALU() = ALU(parseFile("day24-negator.txt"))
  private fun getBinaryALU() = ALU(parseFile("day24-binary.txt"))
  private fun getLargerALU() = ALU(parseFile("day24-3x-larger.txt"))

  // This is by far the slowest solution so far in this year's AoC.
  // On my machine part 2 takes about 4 minutes. Part 1 had almost the same runtime (understandably).
  private fun solve(bestSelector: (String, String) -> String): String {
    val alus = splitStatementsByInput(parseFile("day24.txt")).map { ALU(it) }
    var statesToNumber = mutableMapOf(ALUState() to "")

    alus.forEachIndexed { i, alu ->
      println("ALU #${i + 1}")  // a mild comfort in these trying times
      val nextStatesToDigits = mutableMapOf<ALUState, String>()

      statesToNumber
        .forEach { (state, oldNumber) ->
        (1L..9).forEach { newDigit ->
          val newNumber = oldNumber + newDigit
          val newState = alu.run(state.copy(), listOf(newDigit))

          val updatedNumber = nextStatesToDigits.getOrDefault(newState, newNumber)
          nextStatesToDigits[newState] = bestSelector(updatedNumber, newNumber)
        }
      }
      statesToNumber = nextStatesToDigits
    }

    return statesToNumber
      .filter { (state, _) -> state.z == 0L }
      .map { (_, digits) -> digits }
      .reduce { a, b -> bestSelector(a, b) }
  }

  override fun partOne() = solve { a, b -> maxOf(a, b) }
  override fun partTwo() = solve { a, b -> minOf(a, b) }
}