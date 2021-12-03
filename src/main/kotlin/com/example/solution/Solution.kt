package com.example.solution

abstract class Solution(val day: Int) {
  abstract fun run(part: Int) : Any

  fun readFile(fileName: String) =
    javaClass.getResource(fileName)!!.readText()

  fun readLines(fileName: String) =
    readFile(fileName).split("\n")
}