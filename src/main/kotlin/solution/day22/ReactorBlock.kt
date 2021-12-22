package xyz.terminalnode.aoc2021.solution.day22

data class ReactorBlock(
  var x: IntRange,
  var y: IntRange,
  var z: IntRange,
  var isOn: Boolean = true,
) {
  private fun firstX() = x.first
  private fun lastX() = x.last

  private fun firstY() = y.first
  private fun lastY() = y.last

  private fun firstZ() = z.first
  private fun lastZ() = z.last

  override fun toString() = "${if (isOn) "on" else "off"} x=($x), y=($y), z=($z)"

  fun hasOverlap(other: ReactorBlock) : Boolean {
    // At least some part of all axes need to overlap for there to be an overlap in 3D space.
    return x.hasOverlap(other.x)
        && y.hasOverlap(other.y)
        && z.hasOverlap(other.z)
  }

  fun getSize() = x.count().toLong() * y.count() * z.count()

  fun getKillBlock(other: ReactorBlock) : ReactorBlock {
    val xr = x.getOverlap(other.x)
    val yr = y.getOverlap(other.y)
    val zr = z.getOverlap(other.z)
    return ReactorBlock(xr, yr, zr)
  }

  fun remove(other: ReactorBlock) : List<ReactorBlock> {
    if (!hasOverlap(other)) return listOf(this)
    val killBlock = getKillBlock(other)

    val blocks = mutableListOf<ReactorBlock>()
    val overX = x.getSurroundingRanges(other.x)
    val overY = y.getSurroundingRanges(other.y)
    val overZ = z.getSurroundingRanges(other.z)

    overX.forEach { xRange ->
      overY.forEach { yRange ->
        overZ.forEach { zRange ->
          val newBlock = ReactorBlock(xRange, yRange, zRange)
          if (newBlock != killBlock) blocks.add(newBlock)
        }
      }
    }

    return blocks
  }
}