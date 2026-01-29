package main.java.com.yourorg.lms.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
// import main.java.com.yourorg.lms.model.Course; // Uncomment when you have the model

public class StudentCoursesController {

    // Tab 1: Enrolled Courses
    @FXML private TableView<?> enrolledTable; // Change <?> to <Course> later
    @FXML private TableColumn<?, String> colCourseCode;
    @FXML private TableColumn<?, String> colCourseName;
    @FXML private TableColumn<?, String> colInstructor;
    @FXML private TableColumn<?, String> colGrade;

    // Tab 2: Course Catalog
    @FXML private TextField searchField;
    @FXML private TableView<?> catalogTable; // Change <?> to <Course> later
    @FXML private TableColumn<?, String> colCatName;
    @FXML private TableColumn<?, String> colCatDesc;
    @FXML private TableColumn<?, String> colCatSeats;
    @FXML
    public void initialize() {
        // Matches getId()
        colCourseCode.setCellValueFactory(new PropertyValueFactory<>("id"));
        // Matches getTitle()
        colCourseName.setCellValueFactory(new PropertyValueFactory<>("title"));
        // Matches getInstructorId() 
        colInstructor.setCellValueFactory(new PropertyValueFactory<>("instructorId"));
        
        // Note: Since you don't have 'seats' or 'grade' in your model yet, 
        // those columns will remain empty for now.
    }
}