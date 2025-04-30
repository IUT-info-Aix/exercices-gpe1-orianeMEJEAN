package fr.amu.iut.exercice6;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class IHMPendu extends Application
{
    private String motADeviner;
    private int nbVie = 7;
    private StringBuilder motCache = new StringBuilder();

    private Label labelVies;
    private Label labelMot;

    @Override
    public void start(Stage primaryStage)
    {
        // Récupération du mot à deviner
        Dico dico = new Dico();
        motADeviner = dico.getMot().toUpperCase();

        // Initialiser le mot caché avec des étoiles
        for (int i = 0; i < motADeviner.length(); i++)
        {
            motCache.append("*");
        }

        // Ajout du Champs d'écrit & du Label des vies
        TextField champLettre = new TextField();
        champLettre.setPromptText("Proposez une lettre");
        champLettre.setMaxWidth(150);

        labelVies = new Label("Nombre de vies : " + nbVie);
        labelMot = new Label(motCache.toString());

        champLettre.setOnAction(event ->
        {
            String input = champLettre.getText().toUpperCase();
            if (input.length() == 1 && Character.isLetter(input.charAt(0)))
            {
                traitementDesLettre(input.charAt(0), dico);
            }
            champLettre.clear();
        });

        VBox vbox = new VBox(10, champLettre, labelVies, labelMot);
        vbox.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Jeu du pendu");
        primaryStage.setWidth(500);
        primaryStage.setHeight(550);
        primaryStage.show();
    }

    private void traitementDesLettre(char lettre, Dico dico)
    {
        boolean bonneLettre = false;
        for (int index : dico.getPositions(lettre, motADeviner))
        {
            motCache.setCharAt(index, lettre);
            bonneLettre = true;
        }

        if (!bonneLettre)
        {
            nbVie--;
        }

        labelMot.setText(motCache.toString());
        labelVies.setText("Nombre de vies : " + nbVie);

        if (nbVie == 0) {
            labelVies.setText("Tu as été pendu... Le mot était : " + motADeviner);
        }
        else if (motCache.toString().equals(motADeviner))
        {
            labelVies.setText("Bravo ! Tu as évité ton destin funeste");
        }
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
