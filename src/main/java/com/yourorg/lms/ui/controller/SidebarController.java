package main.java.com.yourorg.lms.ui.controller;

import java.util.Arrays;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import main.java.com.yourorg.lms.model.user.User;
import main.java.com.yourorg.lms.ui.util.ViewFactory;
import main.java.com.yourorg.lms.util.SessionManager;

public class SidebarController {

    @FXML private Button dashboardBtn; // Added this
    @FXML private Button coursesBtn;
    @FXML private Button teachingBtn;
    @FXML private Button adminBtn;
    @FXML private Button settingsBtn; // Added this
    @FXML private Label userNameLabel;

    public void initialize() {
        User current = SessionManager.getCurrentUser();
        
        if (current != null) {
            userNameLabel.setText("Welcome, " + current.getName());
            String role = current.getRole().toUpperCase();

            // Role-based visibility logic
            configureVisibility(role);
        }
    }

    private void configureVisibility(String role) {
        boolean isAdmin = role.equals("ADMIN");
        boolean isInstructor = role.equals("INSTRUCTOR");
        boolean isStudent = role.equals("STUDENT");

        adminBtn.setVisible(isAdmin);
        adminBtn.setManaged(isAdmin);

        teachingBtn.setVisible(isInstructor);
        teachingBtn.setManaged(isInstructor);

        coursesBtn.setVisible(isStudent);
        coursesBtn.setManaged(isStudent);
    }

    @FXML
    private void handleDashboard() {
        highlightActiveButton(dashboardBtn);
        User current = SessionManager.getCurrentUser();
        if (current == null) return;

        switch (current.getRole().toUpperCase()) {
            case "ADMIN" -> ViewFactory.showAdminDashboard();
            case "INSTRUCTOR" -> ViewFactory.showInstructorDashboard();
            default -> ViewFactory.showStudentDashboard();
        }
    }

    @FXML
    private void handleCourses() {
        highlightActiveButton(coursesBtn);
        User current = SessionManager.getCurrentUser();
        if (current == null) return;

        switch (current.getRole().toUpperCase()) {
            case "STUDENT" -> ViewFactory.showStudentCourses();
            case "INSTRUCTOR" -> ViewFactory.showInstructorCourses();
            case "ADMIN" -> ViewFactory.showAdminCourseCatalog();
        }
    }

    @FXML
    private void handleSettings() {
        highlightActiveButton(settingsBtn);
        ViewFactory.showSettings();
    }

    @FXML
    private void handleLogout() {
        SessionManager.logout(); 
        ViewFactory.showLoginWindow(); 
    }

    /**
     * Professional Highlight Logic
     * Removes "active" class from all and adds to the selected one.
     */
    private void highlightActiveButton(Button activeButton) {
        List<Button> allButtons = Arrays.asList(dashboardBtn, coursesBtn, teachingBtn, adminBtn, settingsBtn);
        
        for (Button btn : allButtons) {
            if (btn != null) {
                btn.getStyleClass().remove("active-sidebar-btn");
            }
        }
        
        if (activeButton != null) {
            activeButton.getStyleClass().add("active-sidebar-btn");
        }
    }
}