package xyz.terminalnode.aoc2021

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import xyz.terminalnode.aoc2021.plugins.configureRouting
import xyz.terminalnode.aoc2021.plugins.configureSerialization

fun main() {
  val banner = """
        _       _                 _            __    ____          _
       / \   __| |_   _____ _ __ | |_    ___  / _|  / ___|___   __| | ___
      / _ \ / _` \ \ / / _ \ '_ \| __|  / _ \| |_  | |   / _ \ / _` |/ _ \
     / ___ \ (_| |\ V /  __/ | | | |_  | (_) |  _| | |__| (_) | (_| |  __/
    /_/   \_\__,_| \_/ \___|_| |_|\__|  \___/|_|    \____\___/ \__,_|\___|
                             ____   ___ ____  _
                            |___ \ / _ \___ \/ |
                              __) | | | |__) | |
                             / __/| |_| / __/| |
                            |_____|\___/_____|_|

  """.trimIndent()
  println(banner)

  embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
    configureRouting()
    configureSerialization()
  }.start(wait = true)
}