package com.beust.ktracer

data class Sphere(override val name: String, val center: Point, val radius: Double, val color: Int = 0xff0000)
    : SceneObject(name) {

    override fun intersects(p0: Point, p1: Point): IntersectInfo {
        val result = IntersectInfo()
        result.obj = this
        //        Vector3 d = new Vector3(new Point(0,0,1));
        val d = Vector3(p1.subtract(p0)).normalize()
        val o = Vector3(p0)
        val p0pc = Vector3(p0.subtract(center))
        val r = radius
        // Set a, b, c
        val a = d.dot(d) // d . d
        val b = 2 * p0pc.dot(d) //  2 * d . (p0 - pc)
        val c = p0pc.dot(p0pc) - r * r // o . o - r^2

        val disc = b * b - 4.0 * a * c
        //        System.out.println("Disc: " + disc);
        if (disc >= 0) {
            val discSqrt = Math.sqrt(disc)
            val t0 = (-b - discSqrt) / (2 * a)
            if (t0 >= 0) {
                result.distance = t0
            } else {
                val t1 = (-b + discSqrt) / (2 * a)
                result.distance = Math.min(t0, t1)
            }

            val p = p0.add(Point(d.end.x * result.distance,
                    d.end.y * result.distance,
                    d.end.z * result.distance))
            result.points.add(p)
        }

        return result
    }

    data class Result(val intersect: Boolean, val distance: Double = 0.0, val t0: Double = 0.0, val t1: Double = 0.0)
    fun intersect2(orig: Point, dir: Point): Result {
        val L = center.subtract(orig)
        val tca: Double = L.dot(dir)
        val d2 = L.dot(L) - tca*tca
        if (d2 > radius*radius) return Result(false)

        val thc = Math.sqrt(radius*radius - d2)
        val t0 = tca - thc
        val t1 = tca + thc

        val t = if (t0 < 0) t1 else t0
        val result = if (t < 0) Result(false)
            else Result(true, t, t0, t1)

        return result
//        if (t0 < 0) t0 = t1;
//        if (t0 < 0) return false;
//        return true;
    }

    override fun getPointInfo(p: Point, lights: List<Point>): IntersectInfo {
        val x = p.x.toInt()
        val (x1, y, z) = p.subtract(center)
        val r = Math.sqrt(x1 * x1 + y * y
                + z * z)

        val light = lights[0]
        val lightVector = Vector3(p, light).normalize()
        val normalVector = Vector3(center, p).normalize()
        var shade = lightVector.dot(normalVector)
        if (p.y == 100.0 && (x == 147 || x == 252)) {
            println("===")
            println("Center: $center Point: $p")
            println("Light: $light")
            println("lightVector: $lightVector")
            println("  normalVector: $normalVector")
            println("    Shade for $p: $shade")
        }
        if (shade < 0) {
            shade = 0.0
        }

        val objectColor = 255.0
        val c = java.lang.Math.round(objectColor
                * (Constants.AMBIENT_COEFFICIENT + Constants.DIFFUSE_COEFFICIENT * shade))
            .toInt()
        val result = IntersectInfo()
        result.color = -0x1000000 or
                (c shl 16) or
                (c shl 8) or
                c
        return result
    }
}
