package play11.objects

import javafx.scene.Parent
import javafx.scene.image.ImageView
import play11.MainView.Companion.WINDOW_SIZE
import tornadofx.imageview
import tornadofx.plusAssign

/**
 * Created by danfma on 26/11/16.
 */
class Scenario : GameNode() {
    private var background: ImageView

    init {
        background = imageview("scenario.jpg") {
            x = 0.0
            y = 0.0
            fitWidth = WINDOW_SIZE
            fitHeight = WINDOW_SIZE
        }
    }
}

fun Parent.scenario(modifier: Scenario.() -> Unit) {
    val scenario = Scenario()
    scenario.modifier()
    this += scenario
}
