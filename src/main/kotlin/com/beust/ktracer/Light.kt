package com.beust.ktracer

class Light(val position: Point, val intensity: Double = 1.0) {
    constructor(x: Int, y: Int, z: Int): this(Point(x, y, z))
}
