package fr.amu.iut.exercice16;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class ConvertisseurTemperatures extends Application
{
    @Override
    public void start(Stage primaryStage)
    {
        // Stockent les valeurs de température
        DoubleProperty celsius = new SimpleDoubleProperty(0);
        DoubleProperty fahrenheit = new SimpleDoubleProperty(32);

        // Curseur pour Celsius
        Slider slideC = new Slider(-18, 100, 0);
        slideC.setShowTickLabels(true);
        slideC.setShowTickMarks(true);
        slideC.setMajorTickUnit(10);
        slideC.setOrientation(Orientation.VERTICAL);
        slideC.setPrefHeight(800);

        // Curseur pour Fahrenheit
        Slider slideF = new Slider(0, 212, 32);
        slideF.setShowTickLabels(true);
        slideF.setShowTickMarks(true);
        slideF.setMajorTickUnit(10);
        slideF.setOrientation(Orientation.VERTICAL);
        slideF.setPrefHeight(800);

        // Champs de texte pour afficher les valeurs
        TextField celsiusF = new TextField();
        celsiusF.setPrefSize(60, 25);

        TextField fahrenheitF = new TextField();
        fahrenheitF.setPrefSize(60, 25);

        // Labels pour les unités
        Label celsiusL = new Label("°C");
        celsiusL.setStyle("-fx-font-weight: bold");

        Label fahrenheitL = new Label("°F");
        fahrenheitL.setStyle("-fx-font-weight: bold");

        // Mise a jour de la propriété a chaque mouvement du curseur
        slideC.valueProperty().bindBidirectional(celsius);
        slideF.valueProperty().bindBidirectional(fahrenheit);

        // Binding bidirectionnel
        celsius.addListener((observater, oldVal, newVal) -> fahrenheit.set(newVal.doubleValue() * 9 / 5 + 32));
        fahrenheit.addListener((observater, oldVal, newVal) -> celsius.set((newVal.doubleValue() - 32) * 5 / 9));

        // Liaison bidirectionnelle
        Bindings.bindBidirectional(celsiusF.textProperty(), slideC.valueProperty(), new NumberStringConverter());
        Bindings.bindBidirectional(fahrenheitF.textProperty(), slideF.valueProperty(), new NumberStringConverter());

        VBox panneauCelsius = new VBox(10, celsiusL, slideC, celsiusF);
        panneauCelsius.setAlignment(Pos.CENTER);
        VBox panneauFahrenheit = new VBox(10, fahrenheitL, slideF, fahrenheitF);
        panneauFahrenheit.setAlignment(Pos.CENTER);

        HBox root = new HBox(30, panneauCelsius, panneauFahrenheit);
        root.setPadding(new Insets(20));

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}