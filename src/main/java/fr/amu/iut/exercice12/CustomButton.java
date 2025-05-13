package fr.amu.iut.exercice12;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Button;

public class CustomButton extends Button
{

    private String couleur;
    private final IntegerProperty nbClics;

    public CustomButton(String texte, String couleur)
    {
        super(texte);
        this.couleur = couleur;
        this.nbClics = new SimpleIntegerProperty(0);
        this.setStyle("-fx-background-color: " + couleur);
    }

    public int getNbClics()
    {
        return nbClics.get();
    }

    public IntegerProperty nbClicsProperty()
    {
        return nbClics;
    }

    public void setNbClics(int nbClics)
    {
        this.nbClics.set(nbClics);
        updateText();
    }

    public void incrementerCompteur()
    {
        nbClics.set(nbClics.get() + 1);
        updateText();
    }

    private void updateText()
    {
        this.setText(couleurName());
    }

    private String couleurName()
    {
        if (getText().startsWith("Vert")) return "Vert";
        if (getText().startsWith("Rouge")) return "Rouge";
        if (getText().startsWith("Bleu")) return "Bleu";
        return "Couleur";
    }

    public String getCouleur()
    {
        return couleur;
    }

    public void setCouleur(String couleur)
    {
        this.couleur = couleur;
        this.setStyle("-fx-background-color: " + couleur);
    }
}
