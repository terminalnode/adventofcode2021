package xyz.terminalnode.aoc2021.solution.day24

import xyz.terminalnode.aoc2021.solution.day24.ALUInstruction.*

class ALU(
  private val statements: List<ALUStatement>,
) {
  fun run(
    state: ALUState = ALUState(),
    inputs: List<Long> = listOf(),
  ) : ALUState {
    val inputIterator = inputs.iterator()
    statements.forEach { runAll(state, it, inputIterator) }
    return state
  }

  private fun runAll(state: ALUState, statement: ALUStatement, inputs: Iterator<Long>) {
    when (statement.instruction) {
      INP -> state.readInputAndWriteTo(statement.first, inputs)
      ADD -> state.readAndWrite(statement.first, statement.second!!) { v1, v2 -> v1 + v2 }
      MUL -> state.readAndWrite(statement.first, statement.second!!) { v1, v2 -> v1 * v2 }
      DIV -> state.readAndWrite(statement.first, statement.second!!) { v1, v2 -> v1 / v2 }
      MOD -> state.readAndWrite(statement.first, statement.second!!) { v1, v2 -> v1 % v2 }
      EQL -> state.readAndWrite(statement.first, statement.second!!) { v1, v2 -> if (v1 == v2) 1 else 0 }
    }
  }

  private fun ALUState.readInputAndWriteTo(register: String, inputs: Iterator<Long>) = writeTo(register, inputs.next())
}