package xyz.terminalnode.aoc2021.util

typealias Matrix<T> = List<List<T>>
fun <T> Matrix<T>.get(x: Int, y: Int) = this[y][x]
fun <T> Matrix<T>.getOrNull(x: Int, y: Int) = getOrNull(y)?.getOrNull(x)
fun <T> Matrix<T>.getOrNull(point: Point<*>) = getOrNull(point.y)?.getOrNull(point.x)

fun <T> Matrix<T>.forEachPoint(block: (element: T) -> Unit) = flatten().forEach { block(it) }
fun <T, R> Matrix<T>.mapEachPoint(block: (element: T) -> R) : List<R> = flatten().map { block(it) }