package play09

import javafx.animation.AnimationTimer
import javafx.geometry.Point2D
import javafx.scene.Group
import play09.engine.TimeElapsed
import play09.engine.updateOnlyUpdatables
import play09.objects.myBox
import tornadofx.View

class MainView : View() {
    override val root = Group()

    init {
        primaryStage.width = WINDOW_SIZE
        primaryStage.height = WINDOW_SIZE
        primaryStage.isResizable = false

        with(root) {
            myBox {
                position = Point2D(100.0, 200.0)
            }
        }

        start()
    }

    private fun start() {
        val nanoToMillisecondRatio = 1000000L
        val startTime = System.nanoTime()
        var lastTime = startTime

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

