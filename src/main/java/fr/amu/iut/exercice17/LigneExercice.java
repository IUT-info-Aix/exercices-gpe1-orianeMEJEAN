package fr.amu.iut.exercice17;

import javafx.geometry.Insets;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;

public class LigneExercice extends HBox
{
    private Label enonce;
    private TextField reponse;
    private final Exercice exercice;

    public LigneExercice()
    {
        setPrefWidth(207);
        setPrefHeight(47);
        setSpacing(10);
        setPadding(new Insets(5));

        exercice = new Exercice();
        enonce = new Label(exercice.getEnonce());

        reponse = new TextField();
        // L'objet TextFormatter suivant permet de restreindre le texte aux chiffres
        TextFormatter<String> textFormatter = new TextFormatter<>(change -> {
            String texte = change.getControlNewText();
            if (texte.matches("\\d*")) {
                return change;
            }
            return null;
        });

        reponse.setTextFormatter(textFormatter);
        reponse.setPrefWidth(60);

        IntegerProperty intReponse = new SimpleIntegerProperty();
        reponse.textProperty().bindBidirectional(intReponse, new NumberStringConverter());

        getChildren().addAll(enonce, reponse);
    }
}