package fr.amu.iut.exercice2;

import com.sun.javafx.menu.MenuItemBase;
import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Random;

public class TicTacToe extends Application
{
    @Override
    public void start(Stage primaryStage)
    {
        // Création du GridPane
        GridPane ticTacToe = new GridPane();
        ticTacToe.setAlignment(Pos.CENTER);
        ticTacToe.setHgap(10); // Espacement horizontal
        ticTacToe.setVgap(10); // Espacement vertical

        // Création du Random
        Random random = new Random();

        // Tableau pour les chemins des images
        String[] images = {
                "exercice2/Croix.png", // 0
                "exercice2/Rond.png", // 1
                "exercice2/Vide.png" // 2
        };

        // Remplissage de la grille
        for (int row = 0; row < 3; row++) // row est inférieur a 3 car il va de 0 à 2, row++ permet l'incrémentation
        {
            for (int col = 0; col < 3; col++) // col est inférieur a 3 car il va de 0 à 2, col++ permet l'incrémenta
            {
                int nombre = random.nextInt(3); // nombre random entre 0, 1 ou 2
                Label im = new Label();
                ImageView image = new ImageView(images[nombre]); // ImageView -> sous classe de Node
                im.setGraphic(image);
                ticTacToe.add(im, col, row); // ajout dans la colonne du GridPane
            }
        }

        // Création de la scene
        Scene scene = new Scene(ticTacToe);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Tic-Tac-Toe");
        primaryStage.show();
     }

    public static void main(String[] args)
    {
        launch(args);
    }
}
