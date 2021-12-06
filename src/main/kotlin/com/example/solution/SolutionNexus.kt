package com.example.solution

import com.example.model.AocException
import com.example.model.ResponseMessage
import com.example.plugins.DayPart
import com.example.solution.day1.Day1
import com.example.solution.day2.Day2
import com.example.solution.day3.Day3
import com.example.solution.day4.Day4
import com.example.solution.day5.Day5
import com.example.solution.day6.Day6
import io.ktor.http.*
import java.lang.Exception

object SolutionNexus {
  private val solutionsByDay = listOf(
    Day1, Day2, Day3, Day4, Day5, Day6,
  ).associateBy { it.day }

  fun run(request: DayPart) : ResponseMessage {
    val solution = solutionsByDay[request.day]
      ?: return ResponseMessage(
        data = "No solutions available for day ${request.day}",
        status = HttpStatusCode.NotFound)

    return try {
      ResponseMessage(
        data = solution.run(request.part),
        status = HttpStatusCode.OK)
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