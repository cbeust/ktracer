package com.beust.ktracer

class Main0 {

    private val DISPLAY = Display(WIDTH, HEIGHT)

    private fun run() {
        val png = Png(DISPLAY.width, DISPLAY.height)
        //        int x = 50;
        //        int y = 50;
        for (x in 0 until DISPLAY.width) {
            for (y in 0 until DISPLAY.height) {
                val p = Point(x.toDouble(), y.toDouble(), 0.0)
                val color: Int
                if (d(CAMERA, p) > 0) {
                    val lightVector = Vector3(p, LIGHT).normalize()
                    val normalVector = Vector3(SPHERE.center, p).normalize()
                    var shade = lightVector.dot(normalVector)
                    if (shade < 0) {
                        shade = 0.0
                    }
                    val objectColor = 255.0
                    val c = java.lang.Math.round(objectColor * (AMBIENT_COEFFICIENT + DIFFUSE_COEFFICIENT * shade)).toInt()
                    color = -0x1000000 or
                            (c shl 16) or
                            (c shl 8) or
                            c
                    val hexColor = Integer.toHexString(color)
                    println("Shade: $shade hex: $hexColor")
                    //                    System.out.println("");
                } else {
                    color = -0xffff01
                }
                png.setPoint(x, y, color)
            }
        }
        png.display()
    }

    private fun d(viewer: Point, displayPoint: Point): Double {
        val x0 = viewer.x
        val y0 = viewer.y
        val z0 = viewer.z
        val x1 = displayPoint.x
        val y1 = displayPoint.y
        val z1 = 0.0

        val dx = x1 - x0
        val dy = y1 - y0
        val dz = z1 - z0

        val cx = SPHERE.center.x
        val cy = SPHERE.center.y
        val cz = SPHERE.center.z

        val r = SPHERE.radius
        val a = dx * dx + dy * dy + dz * dz
        val b = 2.0 * dx * (x0 - cx) + 2.0 * dy * (y0 - cy) + 2.0 * dz * (z0 - cz)
        val c = cx * cx + cy * cy + cz * cz + x0 * x0 + y0 * y0 + z0 * z0 +
                -2 * (cx * x0 + cy * y0 + cz * z0) - r * r

        return b * b - 4.0 * a * c
    }

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            //        new Math().test();
            //        Png png = new Png(100, 100);
            //        png.setPoint(10, 10, 0xffff0000);
            //        png.display();
            Main0().run()
        }

        private val WIDTH = 500
        private val HEIGHT = 500
        private val W = WIDTH / 2

        private val SPHERE = Sphere("Sphere", Point(200.0, 200.0, 200.0), 100.0)
        private val CAMERA = Point(W.toDouble(), W.toDouble(), (-W).toDouble())
        private val LIGHT = Point(0.0, W.toDouble(), (-W / 5).toDouble())
        private val AMBIENT_COEFFICIENT = 0.2
        private val DIFFUSE_COEFFICIENT = 1 - AMBIENT_COEFFICIENT
    }

}
