package fr.amu.iut.exercice4;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Palette extends Application
{
    private int nbVert = 0;
    private int nbRouge = 0;
    private int nbBleu = 0;

    private Button vert;
    private Button rouge;
    private Button bleu;

    private BorderPane root;
    private Label label;
    private Pane panneau;
    private HBox bas;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        // Création du BorderPane
        root = new BorderPane();

        // Bande du haut
        label = new Label(" ");
        BorderPane.setAlignment(label, Pos.CENTER); // positionnement de tout les texte au centre
        label.setFont(new Font("Arial", 20)); // changement de la police & taille
        root.setTop(label);

        // Création du centre
        panneau = new Pane();
        panneau.setStyle("-fx-background-color: white;"); // on met la couleur du fond sur blanc
        root.setCenter(panneau);

        // Création du bas
        vert = new Button("Vert");
        rouge = new Button("Rouge");
        bleu = new Button("Bleu");

        bas = new HBox(10);
        bas.getChildren().addAll(vert, rouge, bleu);
        bas.setAlignment(Pos.CENTER);
        root.setBottom(bas);

        // Gestion des événements
        vert.setOnAction(e ->
        {
            nbVert++; // on incrémente
            panneau.setStyle("-fx-background-color: #3a9c64;");
            label.setText("Vert a été choisi " + nbVert + " fois"); // on change le texte
        });

        rouge.setOnAction(e ->
        {
            nbRouge++;
            panneau.setStyle("-fx-background-color: #ed4f4f;");
            label.setText("Rouge a été choisi " + nbRouge + " fois");
        });

        bleu.setOnAction(e ->
        {
            nbBleu++;
            panneau.setStyle("-fx-background-color: #67c3fb;");
            label.setText("Bleu a été choisi " + nbBleu + " fois");
        });

        // Création de la scène
        Scene scene = new Scene(root, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}

