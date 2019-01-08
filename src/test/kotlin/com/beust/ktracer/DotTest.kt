package com.beust.ktracer

import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.DataProvider
import org.testng.annotations.Test

@Test
class DotTest {
    private fun runTest(v1: Vector3, v2: Vector3, expected: Double) = assertThat(v1.dot(v2)).isEqualTo(expected)

    @DataProvider
    fun vectors() = arrayOf(
            arrayOf(Vector3(Point(100.0, 100.0, 100.0), Point(300.0, 100.0, 100.0)).normalize(),
                    Vector3(Point(100.0, 100.0, 100.0), Point(300.0, 100.0, 100.0)).normalize(),
                    1.0),
            arrayOf(Vector3(Point.ORIGIN, Point(1.0, 2.0, 3.0)),
                    Vector3(Point.ORIGIN, Point(6.0, 7.0, 8.0)),
                    44.0),
            arrayOf(Vector3(Point(10.0, 10.0, 10.0), Point(11.0, 12.0, 13.0)),
                    Vector3(Point(100.0, 100.0, 100.0), Point(106.0, 107.0, 108.0)),
                    44.0)
    )

    @Test(dataProvider = "vectors")
    fun dt(v1: Vector3, v2: Vector3, expected: Double) = runTest(v1, v2, expected)
}