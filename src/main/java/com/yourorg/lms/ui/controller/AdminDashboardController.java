package main.java.com.yourorg.lms.ui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.java.com.yourorg.lms.model.user.*;
import main.java.com.yourorg.lms.repository.impl.FileUserRepository;
import main.java.com.yourorg.lms.util.SessionManager;

import java.util.List;

public class AdminDashboardController {

    @FXML private Label welcomeLabel;
    @FXML private Label totalUsersLabel;
    @FXML private TableView<User> userTable;
    @FXML private TableColumn<User, String> colRole;
    @FXML private TableColumn<User, String> colName;
    @FXML private TableColumn<User, String> colEmail;

    @FXML
    public void initialize() {
        User admin = SessionManager.getCurrentUser();
        if (admin != null) {
            welcomeLabel.setText("Admin: " + admin.getName());
        }

        setupTableColumns();
        loadUserData();
    }

    private void setupTableColumns() {
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    private void loadUserData() {
        // Get all users from the repository cache
        List<User> allUsers = FileUserRepository.getInstance().findAll();
        ObservableList<User> userList = FXCollections.observableArrayList(allUsers);
        
        userTable.setItems(userList);
        totalUsersLabel.setText(String.valueOf(allUsers.size()));
    }
}