package play07

import javafx.application.Platform
import javafx.geometry.Rectangle2D
import javafx.scene.Group
import javafx.scene.image.ImageView
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import tornadofx.View
import tornadofx.imageview

class MainView : View() {
    override val root = Group()
    val WINDOW_SIZE = 500.0
    val CHAR_WIDTH = 113.0
    val CHAR_HEIGHT = 174.0
    val CHAR_VELOCITY = 4.0
    private var direction = Direction.Stopped

    init {
        var running = true

        primaryStage.width = WINDOW_SIZE
        primaryStage.height = WINDOW_SIZE
        primaryStage.isResizable = false

        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED) {
            onKeyPressed(it)
        }

        primaryStage.addEventHandler(KeyEvent.KEY_RELEASED) {
            onKeyReleased(it)
        }

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
                x = 250.0
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

                    if (direction == Direction.Stopped) {
                        continue
                    }

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

        val directionModifier =
                when (direction) {
                    Direction.Forward -> 1
                    Direction.Backward -> -1
                    else -> 0
                }

        character.viewport = Rectangle2D(x, y, width, height)
        character.x += CHAR_VELOCITY * directionModifier

        character.scaleX =
                when (direction) {
                    Direction.Forward -> 1.0
                    Direction.Backward -> -1.0
                    else -> character.scaleX
                }
    }

    private fun onKeyPressed(e: KeyEvent) {
        when (e.code) {
            KeyCode.RIGHT -> direction = Direction.Forward
            KeyCode.LEFT -> direction = Direction.Backward
            else -> Unit
        }
    }

    private fun onKeyReleased(e: KeyEvent) {
        when (e.code) {
            KeyCode.RIGHT, KeyCode.LEFT -> direction = Direction.Stopped
            else -> Unit
        }
    }

    enum class Direction {
        Stopped,
        Forward,
        Backward
    }
}