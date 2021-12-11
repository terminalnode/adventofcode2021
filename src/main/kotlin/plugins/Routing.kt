@file:OptIn(KtorExperimentalLocationsAPI::class)

package xyz.terminalnode.aoc2021.plugins

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.routing.*
import xyz.terminalnode.aoc2021.model.ResponseMessage
import xyz.terminalnode.aoc2021.model.setResponse
import xyz.terminalnode.aoc2021.solution.SolutionNexus

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
data class DayPart(val day: Int, val part: Int)