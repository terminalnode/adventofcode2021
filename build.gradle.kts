val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

plugins {
  application
  kotlin("jvm") version "1.6.0"
}

group = "xyz.terminalnode.aoc2021"
version = "0.0.1"
application {
  mainClass.set("xyz.terminalnode.aoc2021.ApplicationKt")
}

repositories {
  mavenCentral()
}

dependencies {
  implementation("io.ktor:ktor-server-core:$ktor_version")
  implementation("io.ktor:ktor-locations:$ktor_version")
  implementation("io.ktor:ktor-gson:$ktor_version")
  implementation("io.ktor:ktor-server-netty:$ktor_version")
  implementation("ch.qos.logback:logback-classic:$logback_version")
}