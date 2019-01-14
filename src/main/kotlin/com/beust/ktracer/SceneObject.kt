package com.beust.ktracer

/**
 * @author Cedric Beust <cedric></cedric>@refresh.io>
 * @since 06 07, 2014
 */
abstract class SceneObject(open val name: String) {

    abstract fun intersects(p0: Point, p1: Point): IntersectInfo
    abstract fun getPointInfo(displayPoint: Point, lights: List<Point>): IntersectInfo
}
