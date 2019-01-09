package com.beust.ktracer


class Display(val width: Int, val height: Int, val backgroundColor: Int) {

    fun allPoints(): List<Point> {
        val result = arrayListOf<Point>()
        for (x in 0 until width) {
            for (y in 0 until height) {
                result.add(Point(x.toDouble(), y.toDouble(), 0.0))
            }
        }
        return result
    }
}
