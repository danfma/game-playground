package play10

import javafx.animation.AnimationTimer
import javafx.geometry.Point2D
import javafx.scene.Group
import play10.engine.TimeElapsed
import play10.engine.updateOnlyUpdatables
import play10.objects.fireSource
import tornadofx.View

class MainView : View() {
    override val root = Group()

    init {
        primaryStage.width = WINDOW_SIZE
        primaryStage.height = WINDOW_SIZE
        primaryStage.isResizable = false

        with(root) {
            fireSource {
                position = Point2D(WINDOW_SIZE / 2.0, WINDOW_SIZE - 40)
            }
        }

        start()
    }

    private fun start() {
        val nanoToMillisecondRatio = 1000000L
        val startTime = System.nanoTime()
        var lastTime = 0L

        val timer = object : AnimationTimer() {
            override fun handle(now: Long) {
                val timeSinceStart = (now - startTime) / nanoToMillisecondRatio
                val timeSinceLastFrame = timeSinceStart - lastTime
                val elapsedTime = TimeElapsed(timeSinceLastFrame, timeSinceStart)

                update(elapsedTime)
                lastTime = timeSinceStart
            }
        }

        timer.start()
    }

    private fun update(time: TimeElapsed) {
        root.childrenUnmodifiable.updateOnlyUpdatables(time)
    }

    companion object {
        const val WINDOW_SIZE = 500.0
    }
}

