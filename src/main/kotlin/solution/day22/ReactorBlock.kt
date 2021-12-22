package xyz.terminalnode.aoc2021.solution.day22

class ReactorBlock(
  val isOn: Boolean,
  var x: IntRange,
  var y: IntRange,
  var z: IntRange,
) {
  override fun toString() = "${if (isOn) "on" else "off"} x=$x,y=$y,z=$z"
}