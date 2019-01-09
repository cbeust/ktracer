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
            drawScene(rp)
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

    private fun drawScene(rayPanel: RayPanel) {
        val drawShadows = true
        for (x in 0 until display.width) {
            for (y in 0 until display.height) {
                val p = Point(x.toDouble(), y.toDouble(), 0.0)
                val closest = findIntersection(camera, p, objects)

                var color = display.backgroundColor
                if (closest != null) {
                    val point = closest.points[0]
                    for (light in lights) {
                        var inShadow = false
                        if (drawShadows) {
                            for (`object` in objects) {
                                if (`object` === closest.obj) {
                                    continue
                                }
                                val deltaPoint = point.add(0.01)
                                val shadowIntersection = `object`.intersects(deltaPoint, light)
                                val distanceToLight = Vector3(point, light).norm()
                                if (shadowIntersection.distance >= 0 &&
                                        shadowIntersection.distance < distanceToLight &&
                                        shadowIntersection.points.size > 0) {
                                    inShadow = true

                                    break
                                }
                            }
                        }

                        if (inShadow) {
                            color = -0x1000000
                        } else {
                            val info = closest.obj!!.getPointInfo(point, light)
                            color = info.color
                        }
                    }
                }
                rayPanel.drawPoint(x.toDouble(), y.toDouble(), color)
            }
        }
    }

    private fun findIntersection(p0: Point, p1: Point, objects: List<SceneObject>): IntersectInfo? {
        var result: IntersectInfo? = null
        var smallestDistance = java.lang.Double.MAX_VALUE
        for (`object` in objects) {
            val ii = `object`.intersects(p0, p1)
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