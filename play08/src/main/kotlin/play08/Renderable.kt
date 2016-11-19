package play08

import javafx.scene.canvas.GraphicsContext

interface Renderable {
    fun render(graphics: GraphicsContext)
}