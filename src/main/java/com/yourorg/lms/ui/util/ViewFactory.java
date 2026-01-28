package main.java.com.yourorg.lms.ui.util;

import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public final class ViewFactory {

    private static Stage primaryStage;
    
    // Using a constant ensures you don't make typos in the path for different methods
    private static final String VIEW_PATH = "/resource/view/";

    private ViewFactory() {}

    public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }

    public static void showLoginWindow() {
        loadScene(VIEW_PATH + "LoginView.fxml", "LMS - Login");
    }

    public static void showStudentDashboard() {
        loadScene(VIEW_PATH + "StudentDashboardView.fxml", "Student Dashboard");
    }

    public static void showRegisterWindow() {
        loadScene(VIEW_PATH + "RegisterView.fxml", "LMS - Register");
    }

    public static void showAdminDashboard() {
        loadScene(VIEW_PATH + "AdminDashboardView.fxml", "Admin Dashboard");
    }

    /**
     * The Engine: Loads the FXML and sets it to the stage.
     */
    private static void loadScene(String fxmlPath, String title) {
        try {
            System.out.println("Searching for FXML at: " + fxmlPath);
            
            // Method 1: Search relative to the class location
            URL xmlLocation = ViewFactory.class.getResource(fxmlPath);

            // Method 2: Fallback to the ClassLoader
            if (xmlLocation == null) {
                // remove the leading slash for getResource
                xmlLocation = ViewFactory.class.getClassLoader().getResource(fxmlPath.substring(1));
            }

            if (xmlLocation == null) {
                throw new IllegalStateException("CRITICAL ERROR: FXML not found at " + fxmlPath + 
                    "\nCheck if the 'resource/view' folder exists inside your 'bin' folder!");
            }

            FXMLLoader loader = new FXMLLoader(xmlLocation);
            Parent root = loader.load();

            if (primaryStage == null) {
                primaryStage = new Stage();
            }

            primaryStage.setTitle(title);
            primaryStage.setScene(new Scene(root));
            primaryStage.show();

        } catch (Exception e) {
            System.err.println("Failed to load scene: " + fxmlPath);
            e.printStackTrace();
        }
    }
}