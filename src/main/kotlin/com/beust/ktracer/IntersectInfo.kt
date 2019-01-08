package com.beust.ktracer

data class IntersectInfo(val points: ArrayList<Point> = ArrayList()) {
    var color: Int = 0
    var hexColor: String? = "Uninitialized"
        get() = Integer.toHexString(color)
    var normal: Vector3? = null
    var distance: Double = 0.toDouble()
    var reflection: Vector3? = null
    var `object`: SceneObject? = null
}
