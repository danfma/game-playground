package play09.engine

import javafx.scene.Node

/**
 * Created by danfma on 19/11/16.
 */

fun List<Node>.updateOnlyUpdatables(time: TimeElapsed) {
    filterIsInstance<Updatable>()
            .forEach { it.update(time) }
}
