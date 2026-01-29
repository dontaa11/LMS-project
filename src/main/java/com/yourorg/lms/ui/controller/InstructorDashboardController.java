package main.java.com.yourorg.lms.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import main.java.com.yourorg.lms.model.user.User;
import main.java.com.yourorg.lms.util.SessionManager;

public class InstructorDashboardController {

    @FXML private Label welcomeLabel;
    @FXML private Label taughtCountLabel;
    @FXML private TableView<?> instructorCourseTable; // Update '?' with Course model later

    @FXML
    public void initialize() {
        User instructor = SessionManager.getCurrentUser();
        if (instructor != null) {
            welcomeLabel.setText("Instructor: " + instructor.getName());
        }
        
        // Logic to load courses taught by this specific instructor goes here
        taughtCountLabel.setText("0"); 
    }
}