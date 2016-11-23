package play10.objects

import javafx.scene.Parent
import javafx.scene.shape.Rectangle
import play10.engine.TimeElapsed
import tornadofx.plusAssign
import tornadofx.rectangle

class SlidingBox : GameNode() {
    private lateinit var rect: Rectangle
    private var accumulatedTime: Double = 0.0

    init {
        with(this) {
            rect = rectangle(0.0, 0.0, 100.0, 50.0) {
                id = "rect"
            }
        }
    }

    override fun update(time: TimeElapsed) {
        accumulatedTime += time.sinceLastFrame

        val balanceTime = 0.001 * Math.PI * accumulatedTime

        rect.x = position.x + Math.cos(balanceTime) * 50.0
        rect.y = position.y
    }
}

fun Parent.myBox(configurer: SlidingBox.() -> Unit): SlidingBox {
    return SlidingBox().apply {
        this.configurer()
        this@myBox += this
    }
}
