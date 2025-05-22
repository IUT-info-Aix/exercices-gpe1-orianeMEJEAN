package fr.amu.iut.exercice15;

import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class LoginControl extends GridPane
{
    @FXML
    private TextField userId;

    @FXML
    private PasswordField pwd;

    @FXML
    private Button okBtn;

    @FXML
    private Button cancelBtn;

    // Controleur de la class
    public void initialize()
    {
        createBindings();
    }

    private boolean isPasswordValid(String password)
    {
        // Mdp vide ou inférieur a 8 == ok non clickable
        if (password == null || password.length() < 8)
        {
            return false;
        }

        boolean upperCase = password.chars().anyMatch(Character::isUpperCase);
        boolean digit = password.chars().anyMatch(Character::isDigit);
        return upperCase && digit;
    }

    private void createBindings()
    {
        // Mdp non editable si id user == 6 carac
        pwd.editableProperty().bind(userId.textProperty().length().lessThan(6).not());

        // Cancel non cliquable si 2 champs vide
        cancelBtn.disableProperty().bind(userId.textProperty().isEmpty().and(pwd.textProperty().isEmpty()));

        // Ok non cliquable si mdp n’a pas au moins 8 caractères
        okBtn.disableProperty().bind(
                pwd.textProperty().isEmpty().or(
                        new BooleanBinding()
                        {
                            {
                                super.bind(pwd.textProperty());
                            }

                            @Override
                            protected boolean computeValue() {
                                return !isPasswordValid(pwd.getText());
                            }
                        }
                )
        );
    }

    @FXML
    private void okClicked()
    {
        System.out.print(userId.getText() + " ");
        for (char c : pwd.getText().toCharArray())
        {
            System.out.print("*");
        }
        System.out.println();
    }

    @FXML
    private void cancelClicked()
    {
        userId.setText("");
        pwd.setText("");
    }
}