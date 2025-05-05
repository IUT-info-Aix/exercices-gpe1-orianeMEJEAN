package fr.amu.iut.exercice6;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;

public class IHMPendu extends Application
{
    private String motADeviner;
    private int nbVie;
    private StringBuilder motCache;
    private Label labelVies;
    private Label labelMot;
    private ImageView imagePendu;
    private VBox touchesClavier;
    private Button boutonRejouer;
    private Dico dico;
    private boolean partieFinie;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        dico = new Dico();
        labelVies = new Label();
        labelMot = new Label();
        imagePendu = new ImageView();
        touchesClavier = new VBox(5);
        boutonRejouer = new Button("Rejouer");

        VBox vBox = new VBox(15);
        vBox.setSpacing(15);
        vBox.setStyle("-fx-alignment: center; -fx-background-color: #cddde4;");

        debutPartie();

        boutonRejouer.setOnAction(e -> debutPartie());

        vBox.getChildren().addAll(imagePendu, labelVies, labelMot, touchesClavier, boutonRejouer);

        Scene scene = new Scene(vBox);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Jeu du pendu");
        primaryStage.setWidth(500);
        primaryStage.setHeight(550);
        primaryStage.show();
    }

    // Debut de game
    private void debutPartie()
    {
        motADeviner = dico.getMot().toUpperCase();
        nbVie = 7;
        partieFinie = false;

        motCache = new StringBuilder();
        for (int i = 0; i < motADeviner.length(); i++)
        {
            motCache.append("*");
        }

        // Zone de Vie
        labelVies.setText("Nombre de vies : " + nbVie);
        labelVies.setStyle("-fx-font-size: 20");

        // Zone pour le mot a deviner
        labelMot.setText(motCache.toString());
        labelMot.setStyle("-fx-font-size: 20");

        // Changement de image quand il y a 1 faute
        mettreAJourImage();

        // On efface la clavier avant de le remettre (sinon on la en double)
        touchesClavier.getChildren().clear();
        creerTouchesClavier();
    }

    // Création du clavier
    private void creerTouchesClavier()
    {
        // On crée une Hbox avec 1 button pour chaque lettre
        HBox ligne = new HBox(5);
        for (char lettre = 'A'; lettre <= 'Z'; lettre++)
        {
            Button bouton = new Button(String.valueOf(lettre));
            bouton.setPrefWidth(40);

            // On crée l'evenement quand on clique sur un bouton
            bouton.setOnAction(o ->
            {
                if (partieFinie) return; // On renvoie rien car la partie est fini (sinon le nombre de vie va dans le négatif)

                char lettreChoisie = bouton.getText().charAt(0);
                traiterLettre(lettreChoisie);

                bouton.setOpacity(0.3); // On change l'opacité
            });

            ligne.getChildren().add(bouton);

            // On ajoute 7 bouton max sur chaque ligne
            if ((lettre - 'A' + 1) % 7 == 0 || lettre == 'Z')
            {
                touchesClavier.getChildren().add(ligne);
                ligne = new HBox(5);
            }
            ligne.setStyle("-fx-alignment: center;");
        }
    }

    // On verifie si c'est une bonne lettre ou non
    private void traiterLettre(char lettre)
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
        mettreAJourImage();

        if (nbVie == 0)
        {
            labelVies.setText("As-tu vraiment essayé ? Le mot était : " + motADeviner);
            partieFinie = true;
        }
        else if (motCache.toString().equals(motADeviner))
        {
            labelVies.setText("Bravo ! Tu as évité ton destin funeste !");
            partieFinie = true;
        }
    }

    private void mettreAJourImage()
    {
        String cheminImage = "exercice6/pendu" + (0 + nbVie) + ".png";
        ImageView nouvelleImage = new ImageView(cheminImage);
        nouvelleImage.setFitHeight(200);
        nouvelleImage.setPreserveRatio(true);

        // Remplacer l'image dans l'imageView
        imagePendu.setImage(nouvelleImage.getImage());

        if (motCache.toString().equals(motADeviner))
        {
            cheminImage = "exercice6/penduWin.png";
            ImageView win = new ImageView(cheminImage);
            imagePendu.setImage(win.getImage());
        }
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}