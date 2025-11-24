package org.example.examenfx;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.examenfx.Utils.JavaFXUtil;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        JavaFXUtil.initStage(stage);
        stage.setTitle("ExamenFX - Gestión de Usuarios");
        JavaFXUtil.setScene("/org/example/examenfx/main_view.fxml");
    }
}