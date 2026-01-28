package main.java.com.yourorg.lms.ui.controller;

import javafx.fxml.FXML;

public class SidebarController {

    @FXML
    private void handleDashboard() {
        System.out.println("Dashboard clicked");
    }

    @FXML
    private void handleCourses() {
        System.out.println("Courses clicked");
    }

    // THIS WAS MISSING:
    @FXML
    private void handleSettings() {
        System.out.println("Settings clicked");
    }

    @FXML
    private void handleLogout() {
        System.out.println("Logout clicked");
        // Add your logout logic here
    }
}