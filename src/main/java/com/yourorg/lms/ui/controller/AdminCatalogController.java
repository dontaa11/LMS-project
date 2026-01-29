package main.java.com.yourorg.lms.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminCatalogController {

    @FXML private TextField searchField;
    @FXML private ComboBox<String> filterBox;
    
    @FXML private TableView<?> globalTable; // Change <?> to <Course>
    @FXML private TableColumn<?, String> colId;
    @FXML private TableColumn<?, String> colName;
    @FXML private TableColumn<?, String> colInstructor;
    @FXML private TableColumn<?, String> colStudents;
    @FXML private TableColumn<?, Void> colActions; // Void because this column will hold Buttons
    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("title"));
        colInstructor.setCellValueFactory(new PropertyValueFactory<>("instructorId"));
    }
}