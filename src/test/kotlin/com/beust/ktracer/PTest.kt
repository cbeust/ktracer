package com.beust.ktracer

import org.testng.Assert
import org.testng.annotations.Test

@Test
class PlaneTest {
    fun main() {
        run {
            val plane = Plane("Plane", Vector3(Point(0, 0, 0), Point(0, 1, 0)),
                    Point(0, 0, 0))
            val p0 = Point(0, 1, 0)
            val p1 = Point(1, 0, 0)
            val ii = plane.intersects3(p0, p1)
            val reflection = ii.reflection
            Assert.assertEquals(reflection!!.start, p1)
            Assert.assertEquals(reflection.end, Point(2, 1, 0))
            println("Reflection: $reflection")
        }
        run {
            val plane = Plane("Plane", Vector3(Point(0, 1, 0)), Point(0, 0, 0))
            val ii = plane.intersects(Point(0, 1, 0), Point(0, -1, 0))
            Assert.assertEquals(ii.distance, 1.0)
            Assert.assertEquals(ii.points[0], Point.ORIGIN)
        }
        run {
            val plane = Plane("Plane", Vector3(Point(0, 1, 0)), Point(0, 0, 0))
            val ii = plane.intersects(Point(0, 1, 0), Point(0, 0, 0))
            Assert.assertEquals(ii.distance, 1.0)
            Assert.assertEquals(ii.points[0], Point.ORIGIN)
        }
        run {
            val plane = Plane("Plane", Vector3(Point(0, -1, 0)), Point(0, 0, 0))
            val ii = plane.intersects(Point(0, 1, 0), Point(0, 0, 0))
            Assert.assertEquals(ii.distance, 1.0)
            Assert.assertEquals(ii.points[0], Point.ORIGIN)
        }
        run {
            val plane = Plane("Plane", Vector3(Point(0, -1, 0)), Point(0, 0, 0))
            val ii = plane.intersects(Point(1, 1, 1), Point(0, 0, 0))
            Assert.assertEquals(ii.distance, 1.73, 0.01)
            Assert.assertEquals(ii.points[0], Point.ORIGIN)
        }
        run {
            val plane = Plane("Plane", Vector3(Point(0, 0, 0), Point(0, 0, -1)),
                    Point(0, 0, 1))
            val p0 = Point(50, 50, -1)
            val p1 = Point(50, 50, -2)
            val ii = plane.intersects3(p0, p1)
            Assert.assertEquals(ii.distance, -2.0, 0.01)
        }

        //        {
        //            Plane plane = new Plane("Plane", new Vector3(new Point(0, 0, 0), new Point(0, 0, 1)),
        //                    new Point(0,0,1));
        //            Point p0 = new Point(50, 50, -2);
        //            Point p1 = new Point(50, 50, -1);
        //            //        System.out.println("Intersect: " + plane.intersects(p1, p0));
        //            System.out.println("Intersect: " + plane.intersects3(p0, p1)); // 2
        //        }
        //        {
        //            Plane plane = new Plane("Plane", new Vector3(new Point(0, 0, 0), new Point(0, 0, 1)),
        //                    new Point(0,0,1000));
        //            Point p0 = new Point(50, 50, -2);
        //            Point p1 = new Point(50, 50, -1);
        //            //        System.out.println("Intersect: " + plane.intersects(p1, p0));
        //            System.out.println("Intersect: " + plane.intersects3(p0, p1)); // 1002
        //        }
        //        {
        //            Plane plane = new Plane("Plane", new Vector3(new Point(0, 0, 0), new Point(0, 0, 1)),
        //                    new Point(0,0,-1000));
        //            Point p0 = new Point(50, 50, -2);
        //            Point p1 = new Point(50, 50, -1);
        //            //        System.out.println("Intersect: " + plane.intersects(p1, p0));
        //            System.out.println("Intersect: " + plane.intersects3(p0, p1)); // -998
        //        }
    }
}
