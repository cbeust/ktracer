package com.beust.ktracer

data class Point(val x: Double, val y: Double, val z: Double) {
    constructor(x: Int, y: Int, z: Int) : this(x.toDouble(), y.toDouble(), z.toDouble())

    companion object {
        val ORIGIN = Point(0.0, 0.0, 0.0)
    }

    fun add(d: Double): Point {
        return Point(x + d, y + d, z + d)
    }

    fun add(other: Point): Point {
        return Point(x + other.x, y + other.y, z + other.z)
    }

    fun subtract(p0: Point): Point {
        return Point(x - p0.x, y - p0.y, z - p0.z)
    }

    fun norm(): Double {
        return Math.sqrt(x * x + y * y + z * z)
    }

//    fun `is`(x: Int, y: Int): Boolean {
//        return x == x && y == y
//    }

    operator fun minus(end: Point): Point {
        return Point(end.x - x, end.y - y, end.z - z)
    }
}

