package xyz.terminalnode.aoc2021.util.elfcode

import xyz.terminalnode.aoc2021.util.SubIterator

private typealias CharIt = Iterator<Char>

class Biterator(
  private val charIterator: Iterator<Char>,
) : Iterator<Char> {
  private var position = 0

  constructor(seq: Sequence<Char>) : this(seq.iterator())

  override fun hasNext() = charIterator.hasNext()
  override fun next(): Char {
    position++
    return charIterator.next()
  }

  // Short helpers
  fun nextBoolean() = next() == '1'
  fun readBitsAsString(n: Int) = (1..n).joinToString("") { next().toString() }
  fun readBitsAsLong(n: Int) = readBitsAsString(n).toLong(2)
  fun readBitsAsInt(n: Int) = readBitsAsString(n).toInt(2)

  fun parseNextPacket(parent: ElfPacket?) : ElfPacket {
    val version = readBitsAsInt(3)
    val type = readBitsAsInt(3)
    val newPacket = ElfPacket(version, type, parent)
    if (newPacket.type == 4) {
      newPacket.literal = parseLiteral()
    } else {
      parseOperator(newPacket)
    }

    return newPacket
  }

  fun parseOperator(packet: ElfPacket) {
    when (val lengthTypeId = readBitsAsInt(1)) {
      0 -> {
        // The next 15 bits are a number that represents the total
        // length in bits of the sub-packets contained by this packet.
        val numberOfBits = readBitsAsLong(15)
        val newBiterator = Biterator(SubIterator(numberOfBits, this))
        while (newBiterator.hasNext()) {
          packet.children.add(newBiterator.parseNextPacket(packet))
        }
      }
      1 -> {
        // The next 11 bits are a number that represents the number
        // of sub-packets immediately contained by this packet.
        val packets = readBitsAsLong(11)
        packet.children = (1..packets)
          .map {
            parseNextPacket(packet)
          }.toMutableList()
      }
      else -> throw IllegalStateException("Expected lengthTypeId to be 1 or 0, but was $lengthTypeId")
    }
  }

  fun parseLiteral() : Long {
    val strings = mutableListOf<String>()
    do {
      val readNext = next() == '1'
      val string = readBitsAsString(4)
      strings.add(string)
    } while (readNext)

    return strings.joinToString("").toLong(2)
  }
}