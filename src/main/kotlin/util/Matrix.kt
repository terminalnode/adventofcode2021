package xyz.terminalnode.aoc2021.util

typealias MutableMatrix<T> = MutableList<MutableList<T>>
typealias Matrix<T> = List<List<T>>
fun <T> Matrix<T>.get(x: Int, y: Int) = this[y][x]
fun <T> MutableMatrix<T>.set(x: Int, y: Int, value: T) { this[y][x] = value }
fun <T> Matrix<T>.getOrNull(x: Int, y: Int) = getOrNull(y)?.getOrNull(x)
fun <T> Matrix<T>.getOrNull(point: Point<*>) = getOrNull(point.y)?.getOrNull(point.x)
fun Matrix<*>.countPoints() = sumOf { it.size }

fun Matrix<Point<*>>.valuesToString() =
  joinToString("\n") { row ->
    row.joinToString("") { it.value.toString() }
  }

fun <T> Matrix<T>.forEachPoint(block: (element: T) -> Unit) = flatten().forEach { block(it) }
fun <T, R> Matrix<T>.mapEachPoint(block: (element: T) -> R) : List<R> = flatten().map { block(it) }