package xyz.terminalnode.aoc2021.solution.day24

data class ALUState(
  var x: Long = 0,
  var y: Long = 0,
  var z: Long = 0,
  var w: Long = 0,
) {
  private fun readFrom(register: String) = when (register) {
    "x" -> x
    "y" -> y
    "z" -> z
    "w" -> w
    else -> register.toLongOrNull() ?: throw IllegalStateException("unresolved reference: $register")
  }

  fun writeTo(register: String, value: Long) {
    when (register) {
      "x" -> { x = value }
      "y" -> { y = value }
      "z" -> { z = value }
      "w" -> { w = value }
      else -> throw IllegalStateException("unresolved reference: $register")
    }
  }

  fun readAndWrite(r1: String, r2: String, block: (v1: Long, v2: Long) -> Long) {
    val v1 = readFrom(r1)
    val v2 = readFrom(r2)
    writeTo(r1, block(v1, v2))
  }
}