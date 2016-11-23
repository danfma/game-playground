package play10.objects

import javafx.geometry.Point2D
import javafx.scene.Group
import play10.engine.GameObject
import play10.engine.TimeElapsed
import play10.engine.updateOnlyUpdatables

abstract class GameNode : Group(), GameObject {

    /**
     * Posição no SRU
     */
    override var position = Point2D(0.0, 0.0)

    override fun update(time: TimeElapsed) {
        childrenUnmodifiable.updateOnlyUpdatables(time)
    }
}
