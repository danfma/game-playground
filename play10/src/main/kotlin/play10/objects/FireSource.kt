package play10.objects

import javafx.geometry.Point2D
import javafx.scene.Parent
import javafx.scene.shape.Rectangle
import play10.engine.TimeElapsed
import tornadofx.circle
import tornadofx.imageview
import tornadofx.plusAssign
import tornadofx.rectangle

/**
 * Created by danfma on 23/11/16.
 */
class FireSource : GameNode() {
    private lateinit var rect: Rectangle
    private var time: Long = 0

    init {
        with(this) {
            rect = rectangle(0.0, 0.0, 10.0, 10.0)
        }
    }

    override fun update(time: TimeElapsed) {
        translateX = position.x
        translateY = position.y
        this.time += time.sinceLastFrame

        if (this.time >= 1000L) {
            val particle = Particle(direction = Point2D(Math.cos(time.sinceStart.toDouble()), -5.0))
            this += particle
            this.time = 0
            println("NEW PARTICLE")
        }

        super.update(time)
    }
}

class Particle(val direction: Point2D) : GameNode() {
    private var time: Long = 0

    init {
        with (this) {
            imageview("bomb.png") {
                scaleX = 0.2
                scaleY = 0.2
            }
        }
    }

    override fun update(time: TimeElapsed) {
        translateX = position.x
        translateY = position.y
        this.time += time.sinceLastFrame

        if (this.time > 30.0) {
            position = Point2D(
                    position.x + direction.x,
                    position.y + direction.y)
            this.time = 0
        }
    }
}

fun Parent.fireSource(configurer: FireSource.() -> Unit): FireSource {
    return FireSource().apply {
        this.configurer()
        this@fireSource += this
    }
}
