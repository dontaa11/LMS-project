package main.java.com.yourorg.lms.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class InstructorCoursesController {

    @FXML private TableView<?> myCoursesTable; // Change <?> to <Course>
    @FXML private TableColumn<?, String> colId;
    @FXML private TableColumn<?, String> colTitle;
    @FXML private TableColumn<?, String> colEnrollment;
    @FXML private TableColumn<?, String> colStatus;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        // Since your model doesn't have status/enrollmentCount yet, 
        // these columns won't show data until you add those fields to Course.java.
    }
    
    // You can add methods here for the "Create" and "Edit" buttons later
}