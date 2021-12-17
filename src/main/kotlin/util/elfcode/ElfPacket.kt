package xyz.terminalnode.aoc2021.util.elfcode

class ElfPacket(
  val version: Int,
  val type: Int,
  var parent: ElfPacket? = null,
  var children: MutableList<ElfPacket> = mutableListOf(),
  var literal: Long? = null,
) {
  fun sumVersion() : Int = version + children.sumOf { it.sumVersion() }

  override fun toString() =
    "ElfPacket{" +
        "version=$version," +
        "type=$type," +
        "parent=${if (parent != null) "yes" else "no"}," +
        "children=${children.size}," +
        "literal=$literal" +
        "}"
}