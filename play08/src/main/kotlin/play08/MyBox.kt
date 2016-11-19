package play08

import javafx.scene.canvas.GraphicsContext
import javafx.scene.paint.Color

class MyBox : GameObject, Renderable {
    override fun update(now: Long) {
        // atualize os dados do objeto
    }

    override fun render(graphics: GraphicsContext) {
        graphics.fill = Color.CYAN
        graphics.fillRect(100.0, 100.0, 200.0, 50.0)
    }
}