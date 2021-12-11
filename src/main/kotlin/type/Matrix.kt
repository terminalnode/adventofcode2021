package xyz.terminalnode.aoc2021.type

typealias Matrix<T> = List<List<T>>
fun <T> Matrix<T>.get(x: Int, y: Int) = this[y][x]