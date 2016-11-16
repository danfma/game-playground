package play04

import javafx.application.Platform
import javafx.geometry.Rectangle2D
import javafx.scene.Group
import javafx.scene.image.ImageView
import tornadofx.View
import tornadofx.imageview

class MainView : View() {
    override val root = Group()
    val WINDOW_SIZE = 500.0
    val CHAR_WIDTH = 113.0
    val CHAR_HEIGHT = 174.0
    val CHAR_VELOCITY = 4.0

    init {
        var running = true

        primaryStage.width = WINDOW_SIZE
        primaryStage.height = WINDOW_SIZE
        primaryStage.isResizable = false

        primaryStage.setOnCloseRequest {
            running = false
        }

        with(root) {
            val background = imageview("scenario.jpg") {
                x = 0.0
                y = 0.0
                fitWidth = WINDOW_SIZE
                fitHeight = WINDOW_SIZE
            }

            val character = imageview("sak-walk.png") {
                x = -120.0
                y = 240.0
                maxWidth(100.0)
                maxHeight(170.0)
                viewport = Rectangle2D(0.0, 0.0, CHAR_WIDTH, CHAR_HEIGHT)
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