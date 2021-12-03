package com.example.solution

import com.example.model.ResponseMessage
import com.example.plugins.DayPart
import com.example.solution.day1.Day1
import io.ktor.http.*
import java.lang.Exception

object SolutionNexus {
  private val solutionsByDay = listOf<Solution>(
    Day1,
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
        status = HttpStatusCode.NotFound)
    } catch (e: Exception) {
      ResponseMessage(
        data = "No solution available for day ${request.day}",
        status = HttpStatusCode.NotFound)
    }
  }
}