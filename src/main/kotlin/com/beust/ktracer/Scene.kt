package com.beust.ktracer

import javax.swing.JFrame
import javax.swing.SwingUtilities



class Scene(private val camera: Point,
        private val objects: List<SceneObject>,
        private val lights: List<Point>,
        private val display: Display = DEFAULT_DISPLAY) {
    companion object {
        val DEFAULT_DISPLAY = Display(500, 500, 0xFFFFE0)
    }

    fun run() {

        SwingUtilities.invokeLater {
            val rp = RayPanel(display.width, display.height)
            drawScene2(rp)
            with(JFrame("Cedric's ray tracer")) {
                contentPane = rp
                defaultCloseOperation = JFrame.EXIT_ON_CLOSE
                pack()             // "this" JFrame packs its components
                setLocationRelativeTo(null) // center the application window
                isVisible = true            // show it
            }
        }

        //        RayPanel png = new RayPanel();
        //        Png png = new Png(DISPLAY.getWidth(), DISPLAY.getHeight());
        //        png.display();
    }

    private fun drawScene2(rayPanel: RayPanel) {
        val fov = Math.PI/2.0
        val tanFov = Math.tan(fov / 2.0)
        val height = display.height
        val width = display.width

        rayPanel.start()
        for (j in 0..height) {
            for (i in 0..width) {
                var distance = Double.MAX_VALUE
                var color = 0x808080
                for (obj in objects) {
                    val x: Double = (2 * (i + 0.5) / width - 1) * tanFov * width / height
                    val y: Double = -(2 * (j + 0.5) / height - 1) * tanFov
                    val dir: Vector3 = Vector3(Point(x, y, -1.0)).normalize()
                    val sphere = obj as Sphere
                    val r = sphere.intersect2(camera, dir.end)
                    if (r.intersect && r.distance < distance) {
                        distance = r.distance
                        color = sphere.color
                    }
                }
                rayPanel.drawPoint(i, height - j, color)
            }
        }
        rayPanel.done()
    }

    private fun drawScene(rayPanel: RayPanel) {
        val drawShadows = true
        for (x in 0 until display.width) {
            for (y in 0 until display.height) {
                val p = Point(x.toDouble(), y.toDouble(), 0.0)
                val closest = findIntersection(camera, p, objects)

                var color = display.backgroundColor
                var colorIsShadow = false
                if (closest != null) {
                    val point = closest.points[0]
                    var shadowCount = 0
                    for (light in lights) {
                        var inShadow = false
                        if (drawShadows) {
                            for (obj in objects) {
                                if (obj === closest.obj) {
                                    continue
                                }
                                val deltaPoint = point.add(0.01)
                                val shadowIntersection = obj.intersects(deltaPoint, light)
                                val distanceToLight = Vector3(point, light).norm()
                                if (shadowIntersection.distance >= 0 &&
                                        shadowIntersection.distance < distanceToLight &&
                                        shadowIntersection.points.size > 0) {
                                    shadowCount++

                                    break
                                }
                            }
                        }
                    }

                    if (! colorIsShadow) {
                        if (shadowCount > 0) {
                            color =
                                    if (shadowCount == 2) 0x202020
                                    else 0x404040
                            colorIsShadow = true
                        } else {
                            val info = closest.obj!!.getPointInfo(point, lights)
                            color = info.color
                        }
                    }

                }
                rayPanel.drawPoint(x, y, color)
            }
        }
    }

    private fun findIntersection(p0: Point, p1: Point, objects: List<SceneObject>): IntersectInfo? {
        var result: IntersectInfo? = null
        var smallestDistance = java.lang.Double.MAX_VALUE
        for (obj in objects) {
            val ii = obj.intersects(p0, p1)
            if (ii.points.size > 0) {
                if (result == null || ii.distance < smallestDistance) {
                    result = ii
                    smallestDistance = ii.distance
                }
            }
        }
        return result
    }
}