package com.beust.ktracer

import org.testng.annotations.Test

@Test
class SphereTest {
    fun test1() {
        run {
            val sphere = Sphere("Sphere", Point(10, 10, 10), 10.0)
//            println(sphere.intersects(Point(10, 10, 10), Point(0, 0, -1)))
            println(sphere.intersects(Point(10, 10, -20), Point(0, 0, 1)))
            println("")
        }
//        run {
//            val sphere = Sphere("Sphere", Point(100, 100, 100), 50.0)
//            val ii = sphere.intersects(Point(100, 100, -100), Point(100, 100, -90))
//            println("distance = " + ii.distance) // 150
//        }
//        run {
//            val plane = Plane("Plane", Vector3(Point(0, 1, 0)), Point(0, 0, 0))
//            val ii = plane.intersects(Point(0, 1, 0), Point(0, 0, 0))
//            Assert.assertEquals(ii.distance, 1.0)
//            Assert.assertEquals(ii.points.get(0), Point.ORIGIN)
//        }
//        run {
//
//            val sphere = Sphere("Sphere", Point(5, 5, 5), 1.0)
//            val ii = sphere.intersects(Point(5, 5, 0), Point(5, 5, 1))
//            println("distance = " + ii.distance) // 4
//        }
//        run {
//
//            val sphere = Sphere("Sphere", Point(100, 100, 100), 50.0)
//            val ii = sphere.intersects(Point(100, 100, -5), Point(100, 100, -4))
//            println("distance = " + ii.distance) // 55
//        }

    }
}
