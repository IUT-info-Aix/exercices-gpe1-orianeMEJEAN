package fr.amu.iut.exercice5;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class JeuMain extends Application
{
    private Scene scene;
    private BorderPane root;
    private static ArrayList<Obstacle> obstacles = new ArrayList<>();

    @Override
    public void start(Stage primaryStage)
    {
        root = new BorderPane();

        //Acteurs du jeu
        Personnage pacman = new Pacman();
        Personnage fantome = new Fantome();

        // Placez le Pacman et le Fantôme aux extrémités
        pacman.setLayoutX(0);
        pacman.setLayoutY(0);
        fantome.setLayoutX(600);
        fantome.setLayoutY(440);

        // Création d'obstacles
        Obstacle mur1 = new Obstacle(200, 100, 100, 200);
        Obstacle mur2 = new Obstacle(0, 400, 200, 100);
        Obstacle mur3 = new Obstacle(0, 100, 200, 100);
        Obstacle mur4 = new Obstacle(400, 300, 300, 100);
        obstacles.add(mur1);
        obstacles.add(mur2);
        obstacles.add(mur3);
        obstacles.add(mur4);

        // Panneau du jeu
        Pane jeu = new Pane();
        jeu.setPrefSize(640, 480);
        jeu.getChildren().add(pacman);
        jeu.getChildren().add(fantome);
        root.setCenter(jeu);

        // Ajout des obstacles au panneau
        for (Obstacle obstacle : obstacles) {
            jeu.getChildren().add(obstacle);
        }

        // On construit une scene 640 * 480 pixels
        scene = new Scene(root, 640, 480);

        //Gestion du déplacement du personnage
        deplacer(pacman, fantome);

        primaryStage.setTitle("... Pac Man ...");

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Permet de gérer les événements de type clavier, pression des touches
     * pour le j1(up,down, right, left), pour le j2( z,q,s,d)
     *
     * @param j1
     * @param j2
     */
    private void deplacer(Personnage j1, Personnage j2)
    {
        scene.setOnKeyPressed((KeyEvent event) -> {
            // Coordonnées avant déplacement
            double oldX1 = j1.getLayoutX();
            double oldY1 = j1.getLayoutY();

            double oldX2 = j2.getLayoutX();
            double oldY2 = j2.getLayoutY();

            switch (event.getCode())
            {
                case LEFT:
                    j1.deplacerAGauche(obstacles);
                    break;
                case RIGHT:
                    j1.deplacerADroite(scene.getWidth(), obstacles);
                    break;
                case UP:
                    j1.deplacerEnHaut(obstacles);
                    break;
                case DOWN:
                    j1.deplacerEnBas(scene.getHeight(), obstacles);
                    break;
                case Z:
                    j2.deplacerEnHaut(obstacles);
                    break;
                case Q:
                    j2.deplacerAGauche(obstacles);
                    break;
                case S:
                    j2.deplacerEnBas(scene.getWidth(), obstacles);
                    break;
                case D:
                    j2.deplacerADroite(scene.getWidth(), obstacles);
                    break;
            }

            if (j1.estEnCollision(j2))
            {
                System.out.println("Collision....");

                // Affichage du "Game Over"
                Label label = new Label("Game Over...");
                label.setStyle("-fx-font-size: 36px; -fx-text-fill: #873333; -fx-alignment: center;");
                root.setCenter(label);
            }
        });
    }
}
