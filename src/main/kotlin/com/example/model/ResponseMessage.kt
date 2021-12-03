package com.example.model

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.util.pipeline.*

data class ResponseMessage(
  val data: Any,
  val status: HttpStatusCode,
)

suspend fun PipelineContext<Unit, ApplicationCall>.setResponse(message: ResponseMessage) {
  with(call) {
    response.status(message.status)
    this.respond(message)
  }
}