package xyz.terminalnode.aoc2021.solution.day24

data class ALUStatement(
  val instruction: ALUInstruction,
  val first: String,
  val second: String? = null,
) {
  override fun toString(): String {
    return if (second == null) "$instruction $first"
    else "$instruction $first $second"
  }
}