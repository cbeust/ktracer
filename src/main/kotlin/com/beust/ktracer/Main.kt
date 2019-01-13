package com.beust.ktracer

fun main(argv: Array<String>) {
    val CAMERA = Point(200.0, 400.0, -500.0)
    val OBJECTS = listOf(
            Plane("Bottom plane", Vector3(Point(0.0, 1.0, 0.0)), Point(0.0, 0.0, 0.0), -0x7f010000),
            Plane("Left plane", Vector3(Point(1.0, 0.0, 0.0)), Point(0.0, 0.0, 0.0), -0x7fff0100),
//            ,new Plane(new Vector3(new Point(0, 0, 1)), new Point(0,0,2000), 0x800000ff)
//            new Sphere(new Point(300, 100, 100), 50),
//            new Sphere(new Point(100, 100, 100), 50),
            Sphere("Sphere 1", Point(200.0, 50.0, 100.0), 50.0),
            Sphere("Sphere 2", Point(400.0, 120.0, 100.0), 30.0),
            Sphere("Sphere 3", Point(300.0, 250.0, 500.0), 50.0)
//            new Sphere("Sphere 2", new Point(300, 80, 50), 40)
//            ,new Sphere(new Point(300, 300, 300), 80)
//            ,new Sphere(new Point(300, 200, 100), 80)
    )
    val LIGHTS = listOf(
            Point(800.0, 250.0, 100.0)
//            new Point(0, 300, 0)
    )


    val scene1 = Scene(CAMERA, OBJECTS, LIGHTS)
    val scene2 = Scene(CAMERA,listOf(
            Plane("Bottom plane", Vector3(Point(0.0, 1.0, 0.0)), Point(0.0, 0.0, 0.0), -0x7f010000),
            Sphere("Sphere 1", Point(250.0, 50.0, 100.0), 50.0)
    ),
            listOf(Point(100, 100, -400), Point(400, 100, -400))
    )
    scene2.run()
}


//class Main {
//
//    private fun drawObject(png: RayPanel) {
//        val (x, y, z) = Point.ORIGIN
//        val dir = Vector3(Point(0.0, 0.0, 1.0))
//        val displayPlane = Plane("Display", Vector3(Point(0.0, 0.0, 1.0)), Point.ORIGIN)
//        var count = 0
//        for (i in 0..999) {
//            val ii = displayPlane.intersects(Point(0.0, 0.0, i.toDouble()), CAMERA)
//            if (ii.points.size > 0 && ii.distance >= 0 && ii.distance < 1000) {
//                val point = ii.points[0]
//                println("#" + count++
//                        + " Display point: " + point + " distance: " + ii.distance)
//                png.drawPoint(point.x, point.y, -0x10000)
//            }
//        }
//    }
//
//    fun run() {
//
//        SwingUtilities.invokeLater {
//            val frame = JFrame("Cedric's ktracer tracer")
//            val rp = RayPanel(WIDTH, HEIGHT)
//            drawScene(rp)
//            //               drawObject(rp);
//            frame.contentPane = rp
//            frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
//            frame.pack()             // "this" JFrame packs its components
//            frame.setLocationRelativeTo(null) // center the application window
//            frame.isVisible = true            // show it
//        }
//
//        //        RayPanel png = new RayPanel();
//        //        Png png = new Png(DISPLAY.getWidth(), DISPLAY.getHeight());
//        //        png.display();
//    }
//
//    private fun drawScene(png: RayPanel) {
//        val objects = OBJECTS
//        val drawShadows = true
//        for (x in 0 until DISPLAY.width) {
//            for (y in 0 until DISPLAY.height) {
//                val p = Point(x.toDouble(), y.toDouble(), 0.0)
//                val closest = findIntersection(CAMERA, p, objects)
//
//                var color = BACKGROUND_COLOR
//                if (closest != null) {
//                    val point = closest.points[0]
//                    for (light in LIGHTS) {
//                        var inShadow = false
//                        if (drawShadows) {
//                            for (`object` in objects) {
//                                if (`object` === closest.obj) {
//                                    continue
//                                }
//                                val deltaPoint = point.add(0.01)
//                                val shadowIntersection = `object`.intersects(deltaPoint, light)
//                                val distanceToLight = Vector3(point, light).norm()
//                                if (shadowIntersection.distance >= 0 &&
//                                        shadowIntersection.distance < distanceToLight &&
//                                        shadowIntersection.points.size > 0) {
//                                    inShadow = true
//
//                                    break
//                                }
//                            }
//                        }
//
//                        if (inShadow) {
//                            color = -0x1000000
//                        } else {
//                            val info = closest.obj!!.getPointInfo(point, light)
//                            color = info.color
//                        }
//                    }
//                }
//                png.drawPoint(x.toDouble(), y.toDouble(), color)
//            }
//        }
//    }
//
//    private fun findIntersection(p0: Point, p1: Point, objects: List<SceneObject>): IntersectInfo? {
//        var result: IntersectInfo? = null
//        var smallestDistance = java.lang.Double.MAX_VALUE
//        for (`object` in objects) {
//            val ii = `object`.intersects(p0, p1)
//            if (ii.points.size > 0) {
//                if (result == null || ii.distance < smallestDistance) {
//                    result = ii
//                    smallestDistance = ii.distance
//                }
//            }
//        }
//        return result
//    }
//
//    companion object {
//
//
//        val BACKGROUND_COLOR = 0xFFFFE0
//        val WIDTH = 500
//        val HEIGHT = 500
//        val DISPLAY = Display(WIDTH, HEIGHT, BACKGROUND_COLOR)
//
//        val OBJECTS = listOf(
//                Plane("Bottom plane", Vector3(Point(0.0, 1.0, 0.0)), Point(0.0, 0.0, 0.0), -0x7f010000),
//                Plane("Left plane", Vector3(Point(1.0, 0.0, 0.0)), Point(0.0, 0.0, 0.0), -0x7fff0100),
//                //            ,new Plane(new Vector3(new Point(0, 0, 1)), new Point(0,0,2000), 0x800000ff)
//                //            new Sphere(new Point(300, 100, 100), 50),
//                //            new Sphere(new Point(100, 100, 100), 50),
//                Sphere("Sphere 1", Point(200.0, 50.0, 100.0), 50.0),
//                Sphere("Sphere 2", Point(400.0, 120.0, 100.0), 30.0),
//                Sphere("Sphere 3", Point(300.0, 250.0, 500.0), 50.0)
//                //            new Sphere("Sphere 2", new Point(300, 80, 50), 40)
//                //            ,new Sphere(new Point(300, 300, 300), 80)
//                //            ,new Sphere(new Point(300, 200, 100), 80)
//        )
//        val CAMERA = Point(200.0, 400.0, -500.0)
//        val LIGHTS = listOf(
//                Point(800.0, 250.0, 100.0)
//                //            new Point(0, 300, 0)
//        )
//        val AMBIENT_COEFFICIENT = 0.05
//        val DIFFUSE_COEFFICIENT = 1 - AMBIENT_COEFFICIENT
//    }
//}
//
