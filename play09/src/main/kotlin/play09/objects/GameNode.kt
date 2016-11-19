package play09.objects

import javafx.geometry.Point2D
import javafx.scene.Group
import play09.engine.GameObject
import play09.engine.TimeElapsed
import play09.engine.updateOnlyUpdatables

abstract class GameNode : Group(), GameObject {

    /**
     * Posição no SRU
     */
    override var position = Point2D(0.0, 0.0)

    override fun update(time: TimeElapsed) {
        childrenUnmodifiable.updateOnlyUpdatables(time)
    }
}
