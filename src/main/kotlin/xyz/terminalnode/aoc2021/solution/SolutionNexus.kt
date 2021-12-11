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

object SolutionNexus {
  private val solutionsByDay = listOf(
    Day01, Day02, Day03, Day04, Day05, Day06,
    Day07, Day08, Day09, Day10,
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