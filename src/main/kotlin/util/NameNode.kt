package xyz.terminalnode.aoc2021.util

class NameNode(
  val name: String = "",
  val connections: MutableSet<NameNode> = mutableSetOf(),
) {
  val isLowerCase = name == name.lowercase()
  val isStartOrEnd = listOf("start", "end").contains(name)

  fun addBidirectional(node: NameNode) {
    connections.add(node)
    node.addUnidirectional(this)
  }

  fun addUnidirectional(node: NameNode) {
    connections.add(node)
  }

  override fun toString(): String {
    val conns = connections.joinToString(", ") { it.name }
    return "NameNode{name='$name', connections=[$conns]}"
  }

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false
    other as NameNode
    return name == other.name
  }

  override fun hashCode() = name.hashCode()
}