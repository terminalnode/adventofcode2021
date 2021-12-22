package xyz.terminalnode.aoc2021.solution.day22

fun parseBlock(s: String): ReactorBlock {
  val (onOff, rangeInputs) = s.split(" ")
  val isOn = onOff == "on"
  return rangeInputs
    .split(",")
    .map { rangeInput -> rangeInput  // string such as "x=-1..2"
      .drop(2) // drop x= / y= / z=
      .split("..")
      .map { it.toInt() }
      .sorted() // ensure that num1 > num2 so ranges make sense
      .let { (num1, num2) -> IntRange(num1, num2) }
    }.let { (x, y, z) -> ReactorBlock(x, y, z, isOn) }
}
