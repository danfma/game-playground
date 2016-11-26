package play11

import javafx.animation.AnimationTimer
import javafx.scene.Group
import javafx.scene.input.KeyEvent
import play11.engine.TimeElapsed
import play11.engine.updateOnlyUpdatables
import play11.objects.character
import play11.objects.scenario
import tornadofx.View

class MainView : View() {
    override val root = Group()

    init {
        primaryStage.width = WINDOW_SIZE
        primaryStage.height = WINDOW_SIZE
        primaryStage.isResizable = false

        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED) {
            Keyboard.setPressed(it.code, true)
        }

        primaryStage.addEventHandler(KeyEvent.KEY_RELEASED) {
            Keyboard.setPressed(it.code, false)
        }

        with(root) {
            scenario {

            }

            character {

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

