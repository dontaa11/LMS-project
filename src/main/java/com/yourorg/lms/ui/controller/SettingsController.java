package main.java.com.yourorg.lms.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.java.com.yourorg.lms.model.user.User;
import main.java.com.yourorg.lms.util.SessionManager;

public class SettingsController {

    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private TextField roleField;
    
    @FXML private PasswordField currentPassField;
    @FXML private PasswordField newPassField;

    @FXML
    public void initialize() {
        // 1. Pre-fill the form with the current user's data
        User current = SessionManager.getCurrentUser();
        if (current != null) {
            nameField.setText(current.getName());
            emailField.setText(current.getEmail());
            roleField.setText(current.getRole()); // Read-only field
        }
    }

    @FXML
    private void handleSave() {
        System.out.println("[ACTION] Save Profile clicked");
        
        String newName = nameField.getText();
        String newPass = newPassField.getText();
        
        // TODO: Call your UserRepository to update this data in the users.txt file
        System.out.println("Updating user: " + newName);
        if (!newPass.isEmpty()) {
            System.out.println("Updating password...");
        }
    }
}