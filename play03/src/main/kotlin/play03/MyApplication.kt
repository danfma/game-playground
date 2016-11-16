package play03

import javafx.application.Application
import javafx.application.Platform
import javafx.geometry.Rectangle2D
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.image.ImageView
import javafx.stage.Stage

class MyApplication : Application() {
    val WINDOW_SIZE = 500.0
    val CHAR_WIDTH = 113.0
    val CHAR_HEIGHT = 174.0
    val CHAR_VELOCITY = 4.0
    private var running = true

    override fun start(primaryStage: Stage) {
        val root = Group()

        val background = ImageView("scenario.jpg").apply {
            x = 0.0
            y = 0.0
            fitWidth = WINDOW_SIZE
            fitHeight = WINDOW_SIZE
            root.children.add(this)
        }

        val character = ImageView("sak-walk.png").apply {
            x = -120.0
            y = 240.0
            maxWidth(100.0)
            maxHeight(170.0)
            viewport = Rectangle2D(0.0, 0.0, CHAR_WIDTH, CHAR_HEIGHT)
            root.children.add(this)
        }

        primaryStage.apply {
            isResizable = false
            width = WINDOW_SIZE
            height = WINDOW_SIZE
            scene = Scene(root)
            show()

            setOnCloseRequest {
                running = false
            }
        }

        val animationThread = Thread {
            var frameX = 0
            var frameY = 0

            while (running) {
                Platform.runLater {
                    nextStep(character, frameX, frameY)
                }

                Thread.sleep(1000 / 30)

                frameX++

                if (frameX >= 6) {
                    frameX = 0
                    frameY++

                    if (frameY >= 4) {
                        frameY = 0
                    }
                }
            }
        }

        animationThread.start()
    }

    private fun nextStep(character: ImageView, frameX: Int, frameY: Int) {
        val x = CHAR_WIDTH * frameX
        val y = CHAR_HEIGHT * frameY
        val width = CHAR_WIDTH
        val height = CHAR_HEIGHT

        character.viewport = Rectangle2D(x, y, width, height)
        character.x += CHAR_VELOCITY

        if (character.x > WINDOW_SIZE + CHAR_WIDTH) {
            character.x = -CHAR_WIDTH
        }
    }
}