package com.beust.ktracer

import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.DataProvider
import org.testng.annotations.Test

@Test
class ColorTest {
    @DataProvider
    fun colors() = arrayOf(
            arrayOf(1.0, 1.0, 1.0, 0xffffff),
            arrayOf(1.0, 0.0, 0.0, 0xff0000),
            arrayOf(0.0, 1.0, 0.0, 0x00ff00),
            arrayOf(0.0, 0.0, 1.0, 0x0000ff),
            arrayOf(0.5, 0.5, 0.5, 0x7f7f7f)
    )

    @Test(dataProvider = "colors")
    fun colorTest(r: Double, g: Double, b: Double, expected: Int) {
        assertThat(Color(r, g, b).value).isEqualTo(expected)
    }

    fun withLong() {
        val v = Color(0x404060)
        assertThat(java.lang.Integer.toHexString(v.value)).isEqualTo("404060")
    }

    fun times() {
        with(Color(1.0, 1.0, 1.0) * 0.5) {
            assertThat(value).isEqualTo(0x7f7f7f)
        }
    }
}