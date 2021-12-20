package xyz.terminalnode.aoc2021.solution.day19

class Scanner(beacons: List<Beacon>) {
  var allRotations: Set<List<Beacon>>
  var mainRotation: Set<Beacon>

  init {
    val rotations = rebuildAllRotations(beacons)
    allRotations = rotations
    mainRotation = rotations.first().toSet()
  }

  private fun rebuildAllRotations(beacons: Collection<Beacon>) : Set<List<Beacon>> {
    // In total all rotations should be a list of 24
    // This will generate 64 different lists of beacon rotations, but this is only done during initialization.
    // Should be fine, and I can't for the life of me figure out the proper mat right now.
    val rotations = mutableSetOf<List<Beacon>>()

    // X rotations
    val x1 = beacons.toList().also { rotations.add(it) }
    val x2 = x1.map { it.copyRotateX() }.also { rotations.add(it) }
    val x3 = x2.map { it.copyRotateX() }.also { rotations.add(it) }
    x3.map { it.copyRotateX() }.also { rotations.add(it) }

    // Y rotations
    val yRot = rotations.map { list ->
      val y1 = list.map { it.copyRotateY() }
      val y2 = y1.map { it.copyRotateY() }
      val y3 = y2.map { it.copyRotateY() }
      listOf(y1, y2, y3)
    }.flatten()
    rotations.addAll(yRot)

    // Z rotations
    val zRot = rotations.map { list ->
      val z1 = list.map { it.copyRotateZ() }
      val z2 = z1.map { it.copyRotateZ() }
      val z3 = z2.map { it.copyRotateZ() }
      listOf(z1, z2, z3)
    }.flatten()
    rotations.addAll(zRot)

    return rotations
  }

  fun findOverlap(other: Scanner): Boolean {
    val overlappingSet = other.allRotations.firstNotNullOfOrNull { findOverlapWithBeaconList(it) }
    if (overlappingSet != null) {
      val rotations = rebuildAllRotations(mainRotation.union(overlappingSet))
      allRotations = rotations
      mainRotation = rotations.first().toSet()
      return true
    }
    return false
  }

  private fun findOverlapWithBeaconList(beaconList: List<Beacon>): List<Beacon>? {
    val offsets = mainRotation.flatMap { main ->
      beaconList.map { XYZOffset(main.x - it.x, main.y - it.y, main.z - it.z) }
    }.toSet()

    offsets.forEach { offset ->
      val offsetList = beaconList.map { it.copyOffset(offset) }
      val matching = offsetList.count { mainRotation.contains(it) }
      if (matching >= 12) return offsetList
    }
    return null
  }

  fun mimicInput(n: Int = 0) : String {
    val header = "--- scanner $n ---"
    val beacons = mainRotation.joinToString("\n") { it.mimicInput() }
    return "$header\n$beacons"
  }
}