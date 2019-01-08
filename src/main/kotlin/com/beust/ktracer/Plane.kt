package com.beust.ktracer

/**
 * @author Cedric Beust <cedric></cedric>@refresh.io>
 * @since 06 07, 2014
 */
data class Plane @JvmOverloads constructor(override val name: String, private val passedNormal: Vector3,
        private val point: Point,
        private val color: Int = -0x7fffff01) : SceneObject(name) {
    private val normal: Vector3 = passedNormal.normalize()

    override fun intersects(p0: Point, p1: Point): IntersectInfo {
        return intersects3(p0, p1)
    }

    //    public IntersectInfo intersects0(Point p0, Point p1) {
    //        double a = normal.getEnd().getX();
    //        double b = normal.getEnd().getY();
    //        double c = normal.getEnd().getZ();
    //        double x0 = p0.getX();
    //        double y0 = p0.getY();
    //        double z0 = p0.getZ();
    //        double x1 = p1.getX();
    //        double y1 = p1.getY();
    //        double z1 = p1.getZ();
    //        double d = a * (x1 - x0) + b * (y1 - y0) + c * (z1 - z0) / Math.sqrt(a * a + b
    //                * b + c * c);
    //        return null;
    //     }

    fun intersects3(a: Point, b: Point): IntersectInfo {
        // http://en.wikipedia.org/wiki/Line%E2%80%93plane_intersection
        // d = ((p0 - l0) dot normal) / (l dot normal)
        // l = vector in the direction of the line
        // l0 = point on the line
        // p0 = point on the plane
        // normal = plane normal
        val l = Vector3(a, b).normalize()
        val p0 = point
        val denominator = l.dot(normal)
        val result = IntersectInfo()
        result.`object` = this
        if (denominator >= 0 && denominator < 0.01f) {
            // parallel, no intersection
        } else {
            val p0l0 = Vector3(p0.subtract(a))
            val numerator = normal.dot(p0l0)
            result.distance = numerator / denominator
            if (result.distance > 0) {
                val p = a.add(l.mult(result.distance))
                result.points.add(p)
            }
        }

        result.reflection = calculateReflection(Vector3(a, b))

        return result
    }

    private fun calculateReflection(v: Vector3): Vector3 {
        // c1 = -dot_product( N, V )
        // Rl = V + (2 * N * c1 )
        val c1 = -v.dot(normal)
        val c2 = Vector3(normal.mult(2 * c1))
        val result = v.add(c2).toOrigin()
        return Vector3(v.end, v.end.add(result))
    }

    override fun getPointInfo(displayPoint: Point, light: Point): IntersectInfo {
        val result = IntersectInfo()
        result.color = color
        return result
    }
}
