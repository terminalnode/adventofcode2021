package xyz.terminalnode.aoc2021.util

typealias Matrix<T> = List<List<T>>
fun <T> Matrix<T>.get(x: Int, y: Int) = this[y][x]