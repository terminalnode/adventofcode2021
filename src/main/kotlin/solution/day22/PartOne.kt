package xyz.terminalnode.aoc2021.solution.day22

import xyz.terminalnode.aoc2021.util.Point3D

// This is slow EVEN for part 1, but it does work
fun ReactorBlock.getPointsInRange(range: IntRange) : List<Point3D> =
  x.filter { it in range }.flatMap { x ->
    y.filter { it in range }.flatMap { y ->
      z.filter { it in range }.map { z ->
        Point3D(x, y, z)
      }
    }
  }
