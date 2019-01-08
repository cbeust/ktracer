package com.beust.ktracer

data class Sphere(override val name: String, val center: Point, val radius: Double) : SceneObject(name) {

    override fun intersects(p0: Point, p1: Point): IntersectInfo {
        return intersects0(p0, p1)
    }

    fun intersects0(p0: Point, p1: Point): IntersectInfo {
        val result = IntersectInfo()
        result.`object` = this
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
            if (p1.y == 100.0 && (p1.x == 150.0 || p1.x == 250.0)) {
                println("DELETE ME point for " + p1.x + ": " + p)
            }
            result.points.add(p)
        }

        return result
    }

    override fun getPointInfo(p: Point, light: Point): IntersectInfo {
        val x = p.x.toInt()
        val (x1, y, z) = p.subtract(center)
        val r = Math.sqrt(x1 * x1 + y * y
                + z * z)

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


        //        if (p.getY() == 100 && (x == 147 || x == 253)) {
        //            double shade2 = M.dot(lightVector, normalVector);
        //            System.out.println(p + " shade:" + shade2);
        //        }
        val objectColor = 255.0
        val c = java.lang.Math.round(objectColor * (Main.AMBIENT_COEFFICIENT + Main.DIFFUSE_COEFFICIENT * shade)).toInt()
        val result = IntersectInfo()
        result.color = -0x1000000 or
                (c shl 16) or
                (c shl 8) or
                c
        return result
    }
}
