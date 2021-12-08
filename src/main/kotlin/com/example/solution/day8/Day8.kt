package com.example.solution.day8

import com.example.solution.Solution
import java.lang.IllegalStateException

/**
 * Structure of the seven segment display:
 *               aaaa
 *              b    c
 *              b    c
 *               dddd
 *              e    f
 *              e    f
 *               gggg
 */

object Day8 : Solution(8) {
  val digitMap = mapOf(
    1 to listOf('c', 'f'),
    2 to listOf('a', 'c', 'd', 'e', 'g'),
    3 to listOf('a', 'c', 'd', 'f', 'g'),
    4 to listOf('b', 'c', 'd', 'f'),
    5 to listOf('a', 'b', 'd', 'f', 'g'),
    6 to listOf('a', 'b', 'd', 'e', 'f', 'g'),
    7 to listOf('a', 'c', 'f'),
    8 to listOf('a', 'b', 'c', 'd', 'e', 'f', 'g'),
    9 to listOf('a', 'b', 'c', 'd', 'f', 'g'),
    0 to listOf('a', 'b', 'c', 'e', 'f', 'g'),
  )

  fun printSegment(digit: Int) {
    val list = digitMap[digit] ?: throw IllegalStateException("Not a valid digit")
    val a = list.getDisplayChar('a')
    val b = list.getDisplayChar('b')
    val c = list.getDisplayChar('c')
    val d = list.getDisplayChar('d')
    val e = list.getDisplayChar('e')
    val f = list.getDisplayChar('f')
    val g = list.getDisplayChar('g')
    println(" $a$a$a$a")
    println("$b    $c")
    println("$b    $c")
    println(" $d$d$d$d")
    println("$e    $f")
    println("$e    $f")
    println(" $g$g$g$g")
  }

  fun List<Char>.getDisplayChar(char: Char) : Char {
    return if (contains(char)) 'x' else ' '
  }

  override fun run(part: Int): Any {
    return when (part) {
      1 -> partOne()
      else -> TODO("Not yet implemented")
    }
  }

  fun partOne() : Any {
    val input = readLines("day8.txt")
      .map { line -> line.split(" | ").map { side -> side.split(" ") } }
      .map { it[1] }
    val ones = input.sumOf { line -> line.count { code -> code.length == digitMap[1]!!.size } }
    val fours = input.sumOf { line -> line.count { code -> code.length == digitMap[4]!!.size } }
    val sevens = input.sumOf { line -> line.count { code -> code.length == digitMap[7]!!.size } }
    val eights = input.sumOf { line -> line.count { code -> code.length == digitMap[8]!!.size } }
    return ones + fours + sevens + eights
  }
}