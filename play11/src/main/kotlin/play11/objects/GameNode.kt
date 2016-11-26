package play11.objects

import javafx.geometry.Point2D
import javafx.scene.Group
import play11.engine.GameObject
import play11.engine.TimeElapsed
import play11.engine.updateOnlyUpdatables

abstract class GameNode : Group(), GameObject {

    /**
     * Posição no SRU
     */
    override var position = Point2D(0.0, 0.0)

    override fun update(time: TimeElapsed) {
        childrenUnmodifiable.updateOnlyUpdatables(time)
    }
}
