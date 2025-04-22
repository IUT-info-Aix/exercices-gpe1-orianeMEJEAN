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

        // Création du centre avec un GridPane
        GridPane centerContent = new GridPane();
        centerContent.setVgap(10);
        centerContent.setHgap(10);
        centerContent.setAlignment(Pos.CENTER);

        // Création des labels et des champs de texte
        Label name = new Label("Name : ");
        TextField champN = new TextField();
        champN.setMaxWidth(260);

        Label email = new Label("Email :");
        TextField champE = new TextField();
        champE.setMaxWidth(260);

        Label mdp = new Label("Password : ");
        PasswordField champMDP = new PasswordField();
        champMDP.setMaxWidth(260);

        // Ajout des éléments dans le GridPane
        centerContent.add(name, 0, 0);  // (colonne, ligne)
        centerContent.add(champN, 1, 0);

        centerContent.add(email, 0, 1);
        centerContent.add(champE, 1, 1);

        centerContent.add(mdp, 0, 2);
        centerContent.add(champMDP, 1, 2);

        // Ajout des boutons submit et cancel
        HBox button = new HBox(10);
        Button submit = new Button("Submit");
        Button cancel = new Button("Cancel");

        button.getChildren().addAll(submit, cancel);
        button.setAlignment(Pos.CENTER);

        VBox fin = new VBox(10);
        fin.getChildren().addAll(centerContent, button);
        fin.setAlignment(Pos.CENTER);
        root.setCenter(fin);

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
        primaryStage.setTitle("Premier exemple manipulant les conteneurs");
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
