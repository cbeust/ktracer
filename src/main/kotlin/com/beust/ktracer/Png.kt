package com.beust.ktracer

import java.awt.FlowLayout
import java.awt.image.BufferedImage

import javax.swing.ImageIcon
import javax.swing.JFrame
import javax.swing.JLabel

class Png(private val width: Int, private val height: Int) {

    private val image: BufferedImage

    init {
        this.image = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
    }

    fun setPoint(x: Double, y: Double, color: Int) {
        setPoint(x.toInt(), y.toInt(), color)
    }

    fun setPoint(x: Int, y: Int, color: Int) {
        image.setRGB(x, height - 1 - y, color)
        //        image.setRGB(x, y, color);
    }

    fun display() {
        val frame = JFrame()
        frame.setSize(width, height + 50)
        frame.contentPane.layout = FlowLayout()
        val label = JLabel(ImageIcon(image))
        //        label.setPreferredSize(new Dimension(width, height));
        frame.contentPane.add(label)
        //        frame.pack();
        frame.isVisible = true
    }
}
