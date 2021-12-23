package xyz.terminalnode.aoc2021.solution.day23

import kotlin.math.abs

data class Hallway(
  val rooms: Map<Char, AmphiRoom>,
  val nodes: List<AmphiNode> = (0..10).map { AmphiNode() },
  var score: Long = 0,
) {
  private fun pathFromTo(origin: Int, target: Int) =
    if (origin < target) (origin..target).drop(1)
    else (origin..target).drop(1).reversed()

  fun isFinished() = rooms.values.none { it.canPop() } && nodes.all { it.amphi == null }

  fun branch() : List<Hallway> {
    return rooms.values.filter {
      val canPop = it.canPop()
      val canGo = nodes[charToNodeIndex(it.type)].amphi == null
      canPop && canGo
    }.flatMap { branchFromRoom(it) }
  }

  private fun branchFromRoom(originRoom: AmphiRoom): List<Hallway> {
    val origin = charToNodeIndex(originRoom.type)
    val lefties = pathFromTo(origin, 0).takeWhile { nodes[it].amphi == null }
    val righties = pathFromTo(origin, 10).takeWhile { nodes[it].amphi == null }
    val all = lefties.union(righties).filter { it !in listOf(2, 4, 6, 8) }

    return all.map { target ->
      val copy = deepCopy().also {
        val amphi = it.popRoomAndIncrementScore(originRoom.type)
        it.putInPosition(target, amphi)
        it.incrementScore(abs(origin - target) * amphi.weight)
      }
      return@map copy
    }
  }

  fun fromHallwayToRoom(): Boolean {
    return nodes.mapIndexedNotNull { i, node ->
      if (node.amphi == null) null
      else i to node.amphi!!
    }.any { (origin, amphi) ->
      // Check if current amphi can be consumed
      val target = charToNodeIndex(amphi.type)
      val room = rooms[amphi.type] ?: throw IllegalStateException("Could not find room of type ${amphi.type}")
      val canGo = pathFromTo(origin, target).all { nodes[it].amphi == null }
      val canDie = room.canTake(amphi)
      if (!canGo || !canDie) return false

      // Consume the amphi
      incrementScore(abs(origin - target) * amphi.weight)
      incrementScore(room.consume(amphi))
      nodes[origin].amphi = null
      return true
    }
  }

  fun fromRoomToRoom(): Boolean {
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
        incrementScore(abs(origin - target) * amphi.weight)
        incrementScore(originRoom.consume(amphi))
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