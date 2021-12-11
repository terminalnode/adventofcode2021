package xyz.terminalnode.aoc2021.type

typealias IntMatrix = List<List<Int>>
fun IntMatrix.get(x: Int, y: Int) = this[y][x]
