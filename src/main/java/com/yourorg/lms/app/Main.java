package main.java.com.yourorg.lms.app;

import main.java.com.yourorg.lms.ui.util.ViewFactory;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Application entry point.
 */


public class Main extends Application {

    @Override
    public void start(Stage stage) {
        ViewFactory.setPrimaryStage(stage);
        ViewFactory.showLoginWindow();
    }

    public static void main(String[] args) {
        launch(args);
    }
        
}
