package play10.engine

import javafx.geometry.Point2D

interface GameObject : Updatable {
    var position: Point2D
}