package fr.amu.iut.exercice10;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class ConteneursControl
{
    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button submitButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button b1;

    @FXML
    private Button b2;

    @FXML
    private Button b3;

    @FXML
    private BorderPane root;

    @FXML
    private void Submit()
    {
        System.out.println("Name : " + nameField.getText());
        System.out.println("Email : " + emailField.getText());
        System.out.println("Password : " + "*".repeat(passwordField.getText().length()));
    }

    @FXML
    private void Cancel()
    {
        nameField.clear();
        emailField.clear();
        passwordField.clear();
    }

    @FXML
    private void theme1() {
        root.setStyle("-fx-background-color: #737373;");
    }

    @FXML
    private void theme2() {
        root.setStyle("-fx-background-color: #ddc091;");
    }

    @FXML
    private void theme3() {
        root.setStyle("-fx-background-color: #a0e1ce;");
    }

}