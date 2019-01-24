package com.beust.ktracer

class Color(val red: Double, val green: Double, val blue: Double) {
    constructor(red: Int, green: Int, blue: Int): this(red.toDouble() / 255,
            green.toDouble() / 255, blue.toDouble() / 255)
    constructor(rgb: Long) : this(
            ((rgb and 0xff0000) shr 16).toInt(),
            ((rgb and 0xff00) shr 8).toInt(),
            (rgb and 0xff).toInt()
    )


    val value: Int
        get() = (blue * 255).toInt() + ((green * 255).toInt() shl 8) + ((red * 255).toInt() shl 16)

    override fun toString(): String = java.lang.Integer.toHexString(value)

    fun toAwtColor(): java.awt.Color {
        val result = java.awt.Color(value)
        return result
    }

    operator fun times(n: Double) = Color(red * n, green * n, blue * n)

    companion object {
        val BLACK = Color(0, 0, 0)
        val WHITE = Color(0xff, 0xff, 0xff)
        val RED = Color(0xff, 0x00, 0x00)
        val BLUE = Color(0x00, 0x00, 0xff)
    }
}
