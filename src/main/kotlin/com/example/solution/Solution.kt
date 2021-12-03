package com.example.solution

import com.example.model.AocException

abstract class Solution(val day: Int) {
  abstract fun run(part: Int) : Any

  fun readFile(fileName: String) : String {
    val path = fileName.takeIf { it.startsWith('/') } ?: "/$fileName"
    return javaClass.getResource(path)?.readText() ?: throw AocException("Failed to read file '$path'!")
  }

  fun readLines(fileName: String) = readFile(fileName).split("\n")
}