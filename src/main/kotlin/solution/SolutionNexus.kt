package xyz.terminalnode.aoc2021.solution

import io.ktor.http.*
import xyz.terminalnode.aoc2021.model.AocException
import xyz.terminalnode.aoc2021.model.ResponseMessage
import xyz.terminalnode.aoc2021.model.SolutionResponse
import xyz.terminalnode.aoc2021.plugins.DayPart
import xyz.terminalnode.aoc2021.solution.day01.Day01
import xyz.terminalnode.aoc2021.solution.day02.Day02
import xyz.terminalnode.aoc2021.solution.day03.Day03
import xyz.terminalnode.aoc2021.solution.day04.Day04
import xyz.terminalnode.aoc2021.solution.day05.Day05
import xyz.terminalnode.aoc2021.solution.day06.Day06
import xyz.terminalnode.aoc2021.solution.day07.Day07
import xyz.terminalnode.aoc2021.solution.day08.Day08
import xyz.terminalnode.aoc2021.solution.day09.Day09
import xyz.terminalnode.aoc2021.solution.day10.Day10
import xyz.terminalnode.aoc2021.solution.day11.Day11
import xyz.terminalnode.aoc2021.solution.day12.Day12
import xyz.terminalnode.aoc2021.solution.day13.Day13
import xyz.terminalnode.aoc2021.solution.day14.Day14
import xyz.terminalnode.aoc2021.solution.day15.Day15
import xyz.terminalnode.aoc2021.solution.day16.Day16
import xyz.terminalnode.aoc2021.solution.day17.Day17
import xyz.terminalnode.aoc2021.solution.day18.Day18
import xyz.terminalnode.aoc2021.solution.day19.Day19
import xyz.terminalnode.aoc2021.solution.day20.Day20
import xyz.terminalnode.aoc2021.solution.day21.Day21
import xyz.terminalnode.aoc2021.solution.day22.Day22
import xyz.terminalnode.aoc2021.solution.day23.Day23
import xyz.terminalnode.aoc2021.solution.day24.Day24
import xyz.terminalnode.aoc2021.solution.day25.Day25

object SolutionNexus {
  private val solutionsByDay = listOf(
    Day01, Day02, Day03, Day04, Day05, Day06,
    Day07, Day08, Day09, Day10, Day11, Day12,
    Day13, Day14, Day15, Day16, Day17, Day18,
    Day19, Day20, Day21, Day22, Day23, Day24,
    Day25,
  ).associateBy { it.day }

  fun run(request: DayPart) : ResponseMessage {
    val solution = solutionsByDay[request.day]
      ?: return ResponseMessage(
        data = "No solutions available for day ${request.day}",
        status = HttpStatusCode.NotFound)

    return try {
      ResponseMessage(
        data = SolutionResponse(
          day = request.day,
          part = request.part,
          name = solution.name,
          result = solution.run(request.part),
        ), status = HttpStatusCode.OK)
    } catch (e: NotImplementedError) {
      ResponseMessage(
        data = "No solution available for part ${request.part} of day ${request.day}",
        status = HttpStatusCode.NotFound
      )
    } catch (e: AocException) {
      // AocExceptions are used when we know something can go wrong and want to give a helpful error
      ResponseMessage(
        data = "Exception on part ${request.part} of day ${request.day}: ${e.message}",
        status = HttpStatusCode.NotFound)
    } catch (e: Exception) {
      e.printStackTrace()
      ResponseMessage(
        data = "Exception on part ${request.part} of day ${request.day}: $e",
        status = HttpStatusCode.NotFound)
    }
  }
}