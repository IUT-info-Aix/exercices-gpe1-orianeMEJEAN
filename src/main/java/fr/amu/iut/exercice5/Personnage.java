package fr.amu.iut.exercice5;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;

class Personnage extends Group
{
    protected final static double LARGEUR_MOITIE_PERSONNAGE = 10;
    protected final static double LARGEUR_PERSONNAGE = LARGEUR_MOITIE_PERSONNAGE * 2;
    private final Circle corps;
    private String direction;

    public Personnage(String direction, Color couleurContour, Color couleurRemplissage)
    {
        this.direction = direction;
        corps = new Circle(10, 10, LARGEUR_MOITIE_PERSONNAGE, couleurContour);
        corps.setFill(couleurRemplissage);
        getChildren().add(corps);
    }

    public void deplacerAGauche(ArrayList<Obstacle> obstacles)
    {
        //    ****
        //   *    *
        //  *---   *
        //   *    *
        //    ****

        //déplacement <----
        if (getLayoutX() >= LARGEUR_PERSONNAGE)
        {
            setLayoutX(getLayoutX() - LARGEUR_PERSONNAGE);
        }
        if (!direction.equals("gauche"))
        {
            direction = "gauche";
        }
        if (collisionAvecObstacle(obstacles))
        {
            setLayoutX(getLayoutX() + LARGEUR_PERSONNAGE);
        }
    }

    public void deplacerADroite(double largeurJeu, ArrayList<Obstacle> obstacles)
    {
        //    ****
        //   *    *
        //  *   ---*
        //   *    *
        //    ****
        //déplacement ---->
        if (getLayoutX() < largeurJeu - LARGEUR_PERSONNAGE)
        {
            setLayoutX(getLayoutX() + LARGEUR_PERSONNAGE);
        }
        if (!direction.equals("droite"))
        {
            direction = "droite";
        }
        if (collisionAvecObstacle(obstacles))
        {
            setLayoutX(getLayoutX() - LARGEUR_PERSONNAGE);
        }
    }

    public void deplacerEnBas(double hauteurJeu, ArrayList<Obstacle> obstacles)
    {
        //    *****
        //   *     *
        //  *   |   *
        //   *  |  *
        //    *****
        if (getLayoutY() <= hauteurJeu - LARGEUR_PERSONNAGE)
        {
            setLayoutY(getLayoutY() + LARGEUR_PERSONNAGE);
        }
        if (!direction.equals("bas"))
        {
            direction = "bas";
        }
        if (collisionAvecObstacle(obstacles))
        {
            setLayoutY(getLayoutY() - LARGEUR_PERSONNAGE);
        }
    }

    public void deplacerEnHaut(ArrayList<Obstacle> obstacles)
    {
        //    *****
        //   *  |  *
        //  *   |   *
        //   *     *
        //    *****
        if (getLayoutY() >= LARGEUR_PERSONNAGE)
        {
            setLayoutY(getLayoutY() - LARGEUR_PERSONNAGE);
        }
        if (!direction.equals("haut"))
        {
            direction = "haut";
        }
        if (collisionAvecObstacle(obstacles))
        {
            setLayoutY(getLayoutY() + LARGEUR_PERSONNAGE);
        }
    }

    boolean estEnCollision(Personnage autrePersonnage)
    {
        return getBoundsInParent().contains(autrePersonnage.getBoundsInParent())
                || autrePersonnage.getBoundsInParent().contains(getBoundsInParent());
    }

    boolean collisionAvecObstacle(List<Obstacle> obstacles)
    {
        for (Obstacle obstacle : obstacles)
        {
            if (this.getBoundsInParent().contains(obstacle.getBoundsInParent()) || obstacle.getBoundsInParent().contains(getBoundsInParent()))
            {
                return true;
            }
        }
        return false;
    }
}