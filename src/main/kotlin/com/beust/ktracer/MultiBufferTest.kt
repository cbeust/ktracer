package com.beust.ktracer

import java.awt.*

class MultiBufferTest(numBuffers: Int, device: GraphicsDevice) {

    lateinit internal var mainFrame: Frame

    init {
        try {
            val gc = device.defaultConfiguration
            mainFrame = Frame(gc)
            mainFrame.isUndecorated = true
            mainFrame.ignoreRepaint = true
            device.fullScreenWindow = mainFrame
            if (device.isDisplayChangeSupported) {
                chooseBestDisplayMode(device)
            }
            val bounds = mainFrame.bounds
            mainFrame.createBufferStrategy(numBuffers)
            val bufferStrategy = mainFrame.bufferStrategy
            var lag = 2000.0f
            while (lag > 0.00000006f) {
                for (i in 0 until numBuffers) {
                    val g = bufferStrategy.drawGraphics
                    if (!bufferStrategy.contentsLost()) {
                        g.color = COLORS[i]
                        g.fillRect(0, 0, bounds.width, bounds.height)
                        bufferStrategy.show()
                        g.dispose()
                    }
                    try {
                        Thread.sleep(lag.toInt().toLong())
                    } catch (e: InterruptedException) {
                    }

                }
                lag = lag / 1.33f
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            device.fullScreenWindow = null
        }
    }

    companion object {

        private val COLORS = arrayOf(Color.red, Color.blue, Color.green, Color.white, Color.black, Color.yellow, Color.gray, Color.cyan, Color.pink, Color.lightGray, Color.magenta, Color.orange, Color.darkGray)
        private val BEST_DISPLAY_MODES = arrayOf(DisplayMode(640, 480, 32, 0), DisplayMode(640, 480, 16, 0), DisplayMode(640, 480, 8, 0))

        private fun getBestDisplayMode(device: GraphicsDevice): DisplayMode? {
            for (x in BEST_DISPLAY_MODES.indices) {
                val modes = device.displayModes
                for (i in modes.indices) {
                    if (modes[i].width == BEST_DISPLAY_MODES[x].width
                            && modes[i].height == BEST_DISPLAY_MODES[x].height
                            && modes[i].bitDepth == BEST_DISPLAY_MODES[x].bitDepth) {
                        return BEST_DISPLAY_MODES[x]
                    }
                }
            }
            return null
        }

        fun chooseBestDisplayMode(device: GraphicsDevice) {
            val best = getBestDisplayMode(device)
            if (best != null) {
                device.displayMode = best
            }
        }

        @JvmStatic
        fun main(args: Array<String>) {
            try {
                var numBuffers = 2
                if (args != null && args.size > 0) {
                    numBuffers = Integer.parseInt(args[0])
                    if (numBuffers < 2 || numBuffers > COLORS.size) {
                        System.err.println("Must specify between 2 and "
                                + COLORS.size + " buffers")
                        System.exit(1)
                    }
                }
                val env = GraphicsEnvironment.getLocalGraphicsEnvironment()
                val device = env.defaultScreenDevice
                val test = MultiBufferTest(numBuffers, device)
            } catch (e: Exception) {
                e.printStackTrace()
            }

            System.exit(0)
        }
    }
}
