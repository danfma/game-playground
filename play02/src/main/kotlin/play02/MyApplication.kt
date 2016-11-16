package play02

import javafx.application.Application
import javafx.stage.Stage

class MyApplication : Application() {
    override fun start(primaryStage: Stage) {
        primaryStage.width = 500.0
        primaryStage.height = 500.0
        primaryStage.show()
    }
}
