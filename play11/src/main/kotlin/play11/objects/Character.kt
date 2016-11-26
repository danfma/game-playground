package play11.objects

import javafx.geometry.Rectangle2D
import javafx.scene.Parent
import javafx.scene.image.ImageView
import javafx.scene.input.KeyCode
import play11.Keyboard
import play11.engine.TimeElapsed
import tornadofx.imageview
import tornadofx.plusAssign
import java.security.Key

/**
 * Created by danfma on 26/11/16.
 */
class Character : GameNode() {
    val CHAR_WIDTH = 113.0
    val CHAR_HEIGHT = 174.0
    val CHAR_VELOCITY = 4.0
    private val character: ImageView
    private var frameX = 0
    private var frameY = 0
    private var direction = Direction.Stopped

    init {
        character = imageview("sak-walk.png") {
            x = 250.0
            y = 240.0
            maxWidth(100.0)
            maxHeight(170.0)
            viewport = Rectangle2D(0.0, 0.0, CHAR_WIDTH, CHAR_HEIGHT)
        }
    }

    override fun update(time: TimeElapsed) {
        super.update(time)

        if (Keyboard.isPressed(KeyCode.RIGHT)) {
            direction = Direction.Forward
        } else if (Keyboard.isPressed(KeyCode.LEFT)) {
            direction = Direction.Backward
        } else {
            direction = Direction.Stopped
        }

        updateSprite(time)
        updateMovement(time)
    }

    private fun updateSprite(time: TimeElapsed) {
        // TODO ajustar ao tempo

        if (direction == Direction.Stopped) {
            return
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

    private fun updateMovement(time: TimeElapsed) {
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

    enum class Direction {
        Stopped,
        Forward,
        Backward
    }
}

fun Parent.character(modifier: Character.() -> Unit) {
    val character = Character()
    character.modifier()
    this += character
}
