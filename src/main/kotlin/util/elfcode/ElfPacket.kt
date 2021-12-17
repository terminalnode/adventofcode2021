package xyz.terminalnode.aoc2021.util.elfcode

class ElfPacket(
  val version: Int,
  val type: Int,
  var parent: ElfPacket? = null,
  var children: MutableList<ElfPacket> = mutableListOf(),
  var literal: Long? = null,
) {
  fun sumVersion() : Int = version + children.sumOf { it.sumVersion() }

  private val childValues : List<Long>
    get() = children.map { it.calculate() }

  override fun toString() =
    "ElfPacket{" +
        "version=$version," +
        "type=$type," +
        "parent=${if (parent != null) "yes" else "no"}," +
        "children=${children.size}," +
        "literal=$literal" +
        "}"

  fun calculate() : Long = when (type) {
    0 -> childValues.sum()
    1 -> childValues.reduce { acc, value -> acc * value  }
    2 -> childValues.minOrNull() ?: throw IllegalStateException("Minimum ElfPacket is missing child values")
    3 -> childValues.maxOrNull() ?: throw IllegalStateException("Maximum ElfPacket is missing child values")
    4 -> literal ?: throw IllegalStateException("Literal ElfPacket is missing literal value")
    5 -> childValues.let { (v1, v2) -> if (v1 > v2) 1 else 0 }
    6 -> childValues.let { (v1, v2) -> if (v1 < v2) 1 else 0 }
    7 -> childValues.let { (v1, v2) -> if (v1 == v2) 1 else 0 }
    else -> throw IllegalStateException("Not sure what action to perform with type $type")
  }
}