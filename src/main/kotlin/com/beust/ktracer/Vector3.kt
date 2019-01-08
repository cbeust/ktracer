package com.beust.ktracer

data class Vector3(val start: Point, val end: Point) {

    constructor(point: Point) : this(Point.ORIGIN, point) {}

    fun norm(): Double {
        val x = end.x - start.x
        val y = end.y - start.y
        val z = end.z - start.z
        return java.lang.Math.sqrt(x * x + y * y + z * z)
    }

    fun normalize(): Vector3 {
        val x = end.x - start.x
        val y = end.y - start.y
        val z = end.z - start.z
        val length = norm()
        return Vector3(Point(0.0, 0.0, 0.0),
                Point(x / length, y / length, z / length))
    }

    fun add(other: Vector3): Vector3 {
        return Vector3(start.add(other.start), end.add(other.end))
    }

    fun mult(n: Double): Point {
        val p = toOrigin()
        return Point(p.x * n, p.y * n, p.z * n)
    }

    fun reverse(): Vector3 {
        return Vector3(end, start)
    }

    fun subtract(other: Vector3): Vector3 {
        return add(other.reverse())
    }

    fun toOrigin(): Point {
        return Point(end.x - start.x, end.y - start.y,
                end.z - start.z)
    }

    fun dot(v2: Vector3): Double {
        val (x, y, z) = toOrigin()
        val (x1, y1, z1) = v2.toOrigin()
        return x * x1 + y * y1 + z * z1
    }

}
