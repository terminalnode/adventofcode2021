package xyz.terminalnode.aoc2021.solution.day23

data class Hallway(
  val rooms: Map<Char, AmphiRoom>,
  val nodes: List<AmphiNode> = (0..10).map { AmphiNode() },
  var score: Long = 0,
) {
  fun isFinished() : Boolean {
    val nonePopable = rooms.values.none { it.canPop() }
    val allEmpty = nodes.all { it.amphi == null }
    return nonePopable && allEmpty
  }

  fun branch(): List<Hallway> {
    val hallways = rooms.values
      .filter { it.canPop() }
      .flatMap { branchFromRoom(it, true) }

    return hallways
  }

  private fun branchFromRoom(originRoom: AmphiRoom, runAuto: Boolean = true): List<Hallway> {
    val origin = charToNodeIndex(originRoom.type)
    val lefties = pathFromTo(origin, 0).takeWhile { nodes[it].amphi == null }
    val righties = pathFromTo(origin, 10).takeWhile { nodes[it].amphi == null }
    val all = lefties.union(righties).filter { it !in listOf(2, 4, 6, 8) }

    return all.map { target ->
      deepCopy().also {
        val amphi = it.popRoomAndIncrementScore(originRoom.type)
        it.putInPosition(target, amphi)
        it.incrementScore(distance(origin, target) * amphi.weight)

        if (runAuto) it.runAuto()
      }
    }
  }

  fun runAuto() {
    do {
      val didHallwayToRoom = fromHallwayToRoom()
    } while (didHallwayToRoom)

    do {
      val didRoomToRoom = fromRoomToRoom()
    } while (didRoomToRoom)
  }

  private fun fromHallwayToRoom(): Boolean {
    return nodes.mapIndexedNotNull { i, node ->
      if (node.amphi == null) null
      else i to node.amphi!!
    }.any { (origin, amphi) ->
      // Check if current amphi can be consumed
      val target = charToNodeIndex(amphi.type)
      val targetRoom = rooms[amphi.type] ?: throw IllegalStateException("Could not find room of type ${amphi.type}")
      val canGo = pathFromTo(origin, target).all { nodes[it].amphi == null }
      val canDie = targetRoom.canTake(amphi)
      if (!canGo || !canDie) return@any false

      // Consume the amphi
      incrementScore(distance(origin, target) * amphi.weight)
      incrementScore(targetRoom.consume(amphi))
      nodes[origin].amphi = null
      return@any true
    }
  }

  private fun fromRoomToRoom(): Boolean {
    rooms.values.filter { it.canPop() }
      .forEach { originRoom ->
        val amphi = originRoom.peek()
        val origin = charToNodeIndex(originRoom.type)
        val target = charToNodeIndex(amphi.type)

        // Check if amphi can go (and be consumed, which was checked in the filter)
        val canGo = pathFromTo(origin, target).all { nodes[it].amphi == null }
        val canDie = rooms[amphi.type]!!.canTake(amphi)
        if (!canGo || !canDie) return@forEach

        // Consume
        popRoomAndIncrementScore(originRoom.type)
        incrementScore(distance(origin, target) * amphi.weight)
        val targetRoom = rooms[amphi.type]!!
        incrementScore(targetRoom.consume(amphi))

        return true
      }

    return false
  }

  private fun deepCopy() = Hallway(
    rooms = rooms.mapValues { it.value.deepCopy() },
    nodes = nodes.map { AmphiNode(it.amphi?.copy()) },
    score = score)

  private fun popRoomAndIncrementScore(c: Char) : Amphi {
    val (amphi, exitPrice) = rooms[c]!!.pop()
    score += exitPrice
    return amphi
  }

  private fun putInPosition(position: Int, amphi: Amphi) {
    nodes[position].amphi = amphi
  }

  private fun incrementScore(n: Long) {
    score += n
  }
}