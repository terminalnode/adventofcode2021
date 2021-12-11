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
  val ktorVersion = "1.6.6"
  val logbackVersion = "1.2.7"

  implementation("io.ktor:ktor-server-core:$ktorVersion")
  implementation("io.ktor:ktor-locations:$ktorVersion")
  implementation("io.ktor:ktor-gson:$ktorVersion")
  implementation("io.ktor:ktor-server-netty:$ktorVersion")
  implementation("ch.qos.logback:logback-classic:$logbackVersion")
}