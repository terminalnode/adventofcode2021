package com.example.solution

import com.example.model.AocException
import com.example.model.ResponseMessage
import com.example.model.SolutionResponse
import com.example.plugins.DayPart
import com.example.solution.day01.Day01
import com.example.solution.day10.Day10
import com.example.solution.day02.Day02
import com.example.solution.day03.Day03
import com.example.solution.day04.Day04
import com.example.solution.day05.Day05
import com.example.solution.day06.Day06
import com.example.solution.day07.Day07
import com.example.solution.day08.Day08
import com.example.solution.day09.Day09
import io.ktor.http.*
import java.lang.Exception

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