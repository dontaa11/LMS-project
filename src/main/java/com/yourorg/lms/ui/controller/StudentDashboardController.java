package main.java.com.yourorg.lms.ui.controller;

import main.java.com.yourorg.lms.model.course.Course;
import main.java.com.yourorg.lms.model.user.Student;
import main.java.com.yourorg.lms.model.user.User;
import main.java.com.yourorg.lms.service.EnrollmentService;
import main.java.com.yourorg.lms.ui.util.ViewFactory;
import main.java.com.yourorg.lms.util.SessionManager;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

/**
 * StudentDashboardController
 *
 * SOLID:
 * - SRP: Handles UI coordination only (no business logic).
 * - DIP: Depends on EnrollmentService abstraction via singleton access.
 *
 * Defensive Design:
 * - Prevents invalid role access.
 * - Prevents ClassCastException.
 */
public class StudentDashboardController {

    @FXML
    private Label welcomeLabel;

    @FXML
    private TableView<Course> courseTable;

    private final EnrollmentService enrollmentService =
            EnrollmentService.getInstance();

    @FXML
    private void handleLogout() {
        main.java.com.yourorg.lms.util.SessionManager.getInstance().logout();
        main.java.com.yourorg.lms.ui.util.ViewFactory.showLoginWindow();
}
    @FXML
    public void initialize() {

        // 1️⃣ CHECK LOGIN STATE
        SessionManager sessionManager = SessionManager.getInstance();
        User currentUser = sessionManager.getCurrentUser();

        if (currentUser == null) {
            System.out.println("[SECURITY] No active session. Redirecting to login.");
            ViewFactory.showLoginWindow();
            return;
        }

        // 2️⃣ CHECK ROLE (DEFENSIVE)
        if (!(currentUser instanceof Student student)) {
            System.out.println("[SECURITY] Non-student attempted to access student dashboard.");
            sessionManager.logout();
            ViewFactory.showLoginWindow();
            return;
        }

        // 3️⃣ UI SETUP
        welcomeLabel.setText("Welcome, " + student.getFullName());

        setupTableColumns();

        // 4️⃣ LOAD DATA SAFELY
        List<Course> courses =
                enrollmentService.getStudentEnrolledCourses(student.getId());

        courseTable.setItems(
                FXCollections.observableArrayList(courses)
        );
    }

    /**
     * Defines table columns manually to avoid FXML binding errors.
     */
    @SuppressWarnings("unchecked")
    private void setupTableColumns() {

        TableColumn<Course, String> idColumn = new TableColumn<>("Course ID");
        idColumn.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );

        TableColumn<Course, String> titleColumn = new TableColumn<>("Course Name");
        titleColumn.setCellValueFactory(
                new PropertyValueFactory<>("title")
        );

        courseTable.getColumns().clear();
        courseTable.getColumns().addAll(idColumn, titleColumn);
    }
}
