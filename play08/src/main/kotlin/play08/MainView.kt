package play08

import javafx.animation.AnimationTimer
import javafx.scene.Group
import javafx.scene.canvas.Canvas
import javafx.scene.canvas.GraphicsContext
import javafx.scene.paint.Color
import tornadofx.View
import tornadofx.plusAssign

class MainView : View() {
    override val root = Group()
    private val box = MyBox()

    init {
        primaryStage.width = WINDOW_SIZE
        primaryStage.height = WINDOW_SIZE
        primaryStage.isResizable = false

        var graphics: GraphicsContext? = null

        with(root) {
            val canvas = Canvas()
            canvas.width = WINDOW_SIZE
            canvas.height = WINDOW_SIZE
            graphics = canvas.graphicsContext2D!!

            this += canvas
        }

        start(graphics!!)
    }

    private fun start(graphics: GraphicsContext) {
        val startTime = System.nanoTime()

        val timer = object : AnimationTimer() {
            override fun handle(now: Long) {
                update(now - startTime)
                render(graphics)
            }
        }

        timer.start()
    }

    private fun update(now: Long) {
        box.update(now)
    }

    private fun render(graphics: GraphicsContext) {
        graphics.clearRect(0.0, 0.0, WINDOW_SIZE, WINDOW_SIZE)
        graphics.fill = Color.BLACK
        graphics.fillRect(0.0, 0.0, WINDOW_SIZE, WINDOW_SIZE)

        // meus objetos da cena
        box.render(graphics)
    }

    companion object {
        const val WINDOW_SIZE = 500.0
    }
}

