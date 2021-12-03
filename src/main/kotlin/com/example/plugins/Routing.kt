@file:OptIn(KtorExperimentalLocationsAPI::class)

package com.example.plugins

import com.example.model.ResponseMessage
import com.example.model.setResponse
import com.example.solution.SolutionNexus
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.routing.*

fun Application.configureRouting() {
  install(Locations)

  routing {
    get("/") {
      ResponseMessage(
        data = "You forgot to specify a challenge and a part, try /challenge/1/part/1",
        status = HttpStatusCode.NotFound
      ).let { setResponse(it) }
    }

    get<DayPart> {
      setResponse(SolutionNexus.run(it))
    }
  }
}

@Location("/day/{day}/part/{part}")
class DayPart(val day: Int, val part: Int)