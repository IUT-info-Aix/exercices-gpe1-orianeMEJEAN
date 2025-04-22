package fr.amu.iut.exercice1;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;

public class FenetreLogiciel extends Application
{
    @Override
    public void start(Stage primaryStage)
    {
        // Manipulation du conteneur principal
        BorderPane root = new BorderPane();

        // Création du menu en haut
        HBox MenuBut = new HBox(10);

        Menu n1 = new Menu("File");
        Menu n2 = new Menu("Edit");
        Menu n3 = new Menu("Help");

        // Ajout des items dans le menu File
        MenuItem New = new MenuItem("New");
        MenuItem Open = new MenuItem("Open");
        MenuItem Save = new MenuItem("Save");
        MenuItem Close = new MenuItem("Close");

        n1.getItems().addAll(New, Open, Save, Close);

        // Ajout des items dans le menu Edit
        MenuItem Cut = new MenuItem("Cut");
        MenuItem Copy = new MenuItem("Copy");
        MenuItem Paste = new MenuItem("Paste");

        n2.getItems().addAll(Cut, Copy, Paste);

        // Ajout de la barre de menus
        MenuBar menuBar = new MenuBar(n1, n2, n3);

        // Ajout de la MenuBar dans la fenêtre
        MenuBut.getChildren().add(menuBar);

        // Ajout séparateur en dessous du menu
        Separator separator = new Separator();
        separator.setOrientation(Orientation.HORIZONTAL);

        VBox topBox = new VBox();
        topBox.getChildren().addAll(menuBar, separator);
        root.setTop(topBox);

        // Création du centre
        VBox centerContent = new VBox(10);

        // Ajout des labels et des zones de texte
        HBox nameBox = new HBox(10);
        Label name = new Label("Name : ");
        TextField champN = new TextField();
        champN.setMaxWidth(260);
        nameBox.getChildren().addAll(name, champN);
        nameBox.setAlignment(Pos.CENTER);

        HBox mail = new HBox(10);
        Label email = new Label("Email :");
        TextField champE = new TextField();
        champE.setMaxWidth(260);
        mail.getChildren().addAll(email, champE);
        mail.setAlignment(Pos.CENTER);

        HBox mdpBox = new HBox(10);
        Label mdp = new Label("Password : ");
        PasswordField champMDP = new PasswordField();
        champMDP.setMaxWidth(260);
        mdpBox.getChildren().addAll(mdp, champMDP);
        mdpBox.setAlignment(Pos.CENTER);

        // Ajout des boutons submit et cancel
        HBox button = new HBox(10);
        Button submit = new Button("Submit");
        Button cancel = new Button("Cancel");

        button.getChildren().addAll(submit, cancel);
        button.setAlignment(Pos.CENTER);

        // On centre tout
        centerContent.getChildren().addAll(nameBox, mail, mdpBox, button);
        centerContent.setAlignment(Pos.CENTER);
        root.setCenter(centerContent);

        // Création du bandeau en bas
        Label texte = new Label("Ceci est un label de bas de page");

        // Ajout séparateur au dessus
        Separator separator1 = new Separator();
        separator1.setOrientation(Orientation.HORIZONTAL);

        VBox bottom = new VBox();
        bottom.getChildren().addAll(separator1, texte);
        bottom.setAlignment(Pos.CENTER);
        root.setBottom(bottom);

        // Création du bandeau à gauche
        Label texte2 = new Label("Boutons : ");
        Button b1 = new Button("Bouton 1");
        Button b2 = new Button("Bouton 2");
        Button b3 = new Button("Bouton 3");

        // Ajout séparateur a droite
        Separator separator2 = new Separator();
        separator2.setOrientation(Orientation.VERTICAL);

        VBox leftBox = new VBox(10);
        leftBox.getChildren().addAll(texte2, b1, b2, b3);
        HBox right = new HBox(10);
        right.getChildren().addAll(leftBox, separator2);
        leftBox.setAlignment(Pos.CENTER);
        root.setLeft(right);

        // Création de la scène
        Scene scene = new Scene(root, 650, 550);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Exemple avec séparateurs et bordures");
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
