package xyz.terminalnode.aoc2021.solution.day19

data class Beacon(
  var x: Long,
  var y: Long,
  var z: Long,
) {
  fun copyOffset(offset: XYZOffset) =
    copy(
      x = x + offset.x,
      y = y + offset.y,
      z = z + offset.z)

  fun copyRotateX(reverse: Boolean = false) = if (!reverse) {
    copy(y = z, z = -y)
  } else {
    copy(y = -z, z = y)
  }

  fun copyRotateY(reverse: Boolean = false) = if (!reverse) {
    copy(x = -z, z = x)
  } else {
    copy(x = z, z = -x)
  }

  fun copyRotateZ(reverse: Boolean = false) = if (!reverse) {
    copy(y = x, x = -y)
  } else {
    copy(y = -x, x = y)
  }

  // Not the most effective way to rotate, but very simple... and usually we'll want the copy anyway.
  fun rotateX(reverse: Boolean = false) = valuesFrom(copyRotateX(reverse))
  fun rotateY(reverse: Boolean = false) = valuesFrom(copyRotateY(reverse))
  fun rotateZ(reverse: Boolean = false) = valuesFrom(copyRotateZ(reverse))

  private fun valuesFrom(other: Beacon) {
    x = other.x
    y = other.y
    z = other.z
  }

  fun mimicInput() = "$x,$y,$z"
}