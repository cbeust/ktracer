package com.beust.ktracer

class Vector3D {
    var x: Double = 0.toDouble()
    var y: Double = 0.toDouble()
    var z: Double = 0.toDouble()

    // constructors
    constructor() {}

    constructor(x: Double, y: Double, z: Double) {
        this.x = x
        this.y = y
        this.z = z
    }

    constructor(v: Vector3D) {
        x = v.x
        y = v.y
        z = v.z
    }

    // methods
    fun dot(B: Vector3D): Double {
        return x * B.x + y * B.y + z * B.z
    }

    fun dot(Bx: Double, By: Double, Bz: Double): Double {
        return x * Bx + y * By + z * Bz
    }

    fun cross(B: Vector3D): Vector3D {
        return Vector3D(y * B.z - z * B.y, z * B.x - x * B.z, x * B.y - y * B.x)
    }

    fun cross(Bx: Double, By: Double, Bz: Double): Vector3D {
        return Vector3D(y * Bz - z * By, z * Bx - x * Bz, x * By - y * Bx)
    }

    fun length(): Double {
        return Math.sqrt(x * x + y * y + z * z)
    }

    fun normalize() {
        var t = x * x + y * y + z * z
        if (t != 0.0 && t != 1.0) t = 1 / Math.sqrt(t)
        x *= t
        y *= t
        z *= t
    }

    override fun toString() = "[$x, $y, $z]"

    companion object {

        fun dot(A: Vector3D, B: Vector3D): Double {
            return A.x * B.x + A.y * B.y + A.z * B.z
        }

        fun cross(A: Vector3D, B: Vector3D): Vector3D {
            return Vector3D(A.y * B.z - A.z * B.y, A.z * B.x - A.x * B.z, A.x * B.y - A.y * B.x)
        }

        fun length(A: Vector3D): Double {
            return Math.sqrt(A.x * A.x + A.y * A.y + A.z * A.z)
        }

        fun normalize(A: Vector3D): Vector3D {
            var t = A.x * A.x + A.y * A.y + A.z * A.z
            if (t != 0.0 && t != 1.0) t = 1 / Math.sqrt(t)
            return Vector3D(A.x * t, A.y * t, A.z * t)
        }
    }
}