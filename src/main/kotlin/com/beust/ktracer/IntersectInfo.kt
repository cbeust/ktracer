package com.beust.ktracer

class IntersectInfo(var intersect: Boolean = false, val color: Color = Color(0x20, 0x20, 0x20)) {
    var distance: Double = 0.0

    var obj: SceneObject? = null
    val points: ArrayList<Point> = ArrayList()
    var t0: Double = 0.0
    var t1: Double = 0.0
    var normal: Vector3? = null
}

//data class IntersectInfo(val points: ArrayList<Point> = ArrayList()) {
//    var color: Int = 0
//    var hexColor: String? = "Uninitialized"
//        get() = Integer.toHexString(color)
//    var normal: Vector3? = null
//    var distance: Double = 0.toDouble()
//    var reflection: Vector3? = null
//    var obj: SceneObject? = null
//}
