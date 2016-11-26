package play11

import javafx.scene.input.KeyCode

object Keyboard {
    val keys = mutableMapOf<KeyCode, Boolean>()

    init {
        for (key in KeyCode.values()) {
            keys[key] = false
        }
    }

    fun isPressed(keyCode: KeyCode): Boolean {
        return keys[keyCode]!!
    }

    fun setPressed(keyCode: KeyCode, pressed: Boolean) {
        keys[keyCode] = pressed
    }
}