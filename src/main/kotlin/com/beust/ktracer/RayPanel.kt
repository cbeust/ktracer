package com.beust.ktracer

import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.SwingUtilities

class RayPanel(width: Int, private val thisHeight: Int) : JPanel() {

    private val points = ArrayList<P>()
    private val rectangles = arrayListOf<List<Int>>()

    internal inner class P(var x: Int, var y: Int, var color: Int)

    init {
        preferredSize = Dimension(width, thisHeight)
    }

    public override fun paintComponent(g: Graphics) {
        super.paintComponent(g)  // paint background
        //       setBackground(Color.LIGHT_GRAY);

        for (p in points) {
            g.color = Color(p.color)
            g.drawRect(p.x, thisHeight - 1 - p.y.toInt(), 1, 1)
        }
        points.clear()

        for (r in rectangles) {
            g.color = Color.RED
            g.fillRect(r[0], r[1], r[2], r[3])
        }
        rectangles.clear()
    }

    fun drawPoint(x: Int, y: Int, color: Int) {
        SwingUtilities.invokeLater {
            points.add(P(x, y, color))
        }
    }

    fun drawRect(x: Int, y: Int, w: Int, h: Int) {
        SwingUtilities.invokeLater {
            rectangles.add(listOf(x, y, w, h))
        }
    }

    private var start: Long = 0

    fun start() {
        start = System.currentTimeMillis()
    }

    fun done() {
        SwingUtilities.invokeLater {
            repaint()
        }
        println("Time to draw: " + (System.currentTimeMillis() - start) + " ms")
    }

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            // Run GUI codes in the Event-Dispatching thread for thread safety
            SwingUtilities.invokeLater {
                val frame = JFrame("Cedric's ktracer tracer")
                val rp = RayPanel(500, 500)
                rp.drawPoint(100, 100, 0x0000ff)
                frame.contentPane = rp
                frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
                frame.pack()             // "this" JFrame packs its components
                frame.setLocationRelativeTo(null) // center the application window
                frame.isVisible = true            // show it
            }
        }
    }
}
