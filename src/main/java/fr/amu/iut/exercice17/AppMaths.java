package fr.amu.iut.exercice17;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AppMaths extends Application
{
    @Override
    public void start(Stage primaryStage)
    {
        ComboBox<Integer> comboB = new ComboBox<>();
        comboB.getItems().addAll(6, 9, 12, 15);
        comboB.setValue(0);

        VBox vbox = new VBox(10, comboB);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.TOP_RIGHT);

        // Ligne d'exercice
        VBox lignesContainer = new VBox(10);
        lignesContainer.setAlignment(Pos.CENTER);
        vbox.getChildren().add(lignesContainer);

        // Bouton de validation
        Button validateButton = new Button("Valider les résultats");
        validateButton.setDisable(true);

        comboB.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Nombre d'exercice choisit : "+ newValue);

            // Effacer les anciennes lignes
            lignesContainer.getChildren().clear();
            validateButton.setDisable(false);

            // Ajouter les lignes d'exercices
            for (int i = 0; i < newValue; i++)
            {
                LigneExercice ligne = new LigneExercice();
                lignesContainer.getChildren().add(ligne);
            }

            // Ajouter le bouton de validation à la fin du conteneur
            if (!lignesContainer.getChildren().contains(validateButton))
            {
                lignesContainer.getChildren().add(validateButton);
            }

            lignesContainer.getScene().getWindow().sizeToScene();
        });

        Scene scene = new Scene(vbox, 200, 800);
        primaryStage.setTitle("AppMaths");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}