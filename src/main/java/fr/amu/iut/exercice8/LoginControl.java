package fr.amu.iut.exercice8;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class LoginControl extends GridPane
{
    @FXML
    private TextField user;
    @FXML
    private PasswordField pwd;

    @FXML
    private void okClicked()
    {
        // Zone de Texte
        String username = user.getText();
        String password = pwd.getText();

        // Remplacer le mot de passe par des *
        String stars = "*".repeat(password.length());

        // Affichage sur le terminal
        System.out.println("User Id : " + username);
        System.out.println("Password : " + stars);
    }

    @FXML
    private void cancelClicked()
    {
        // On efface tout
        user.clear();
        pwd.clear();
    }
}