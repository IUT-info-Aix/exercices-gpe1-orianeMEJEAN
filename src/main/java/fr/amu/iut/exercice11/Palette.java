package fr.amu.iut.exercice11;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

@SuppressWarnings("Duplicates")
public class Palette extends Application {

    private SimpleIntegerProperty nbVert;
    private SimpleIntegerProperty nbRouge;
    private SimpleIntegerProperty nbBleu;

    private SimpleIntegerProperty nbFois;
    private Label texteDuHaut;

    private Button vert;
    private Button rouge;
    private Button bleu;

    private Button reset;

    private BorderPane root;
    private Pane panneau;
    private HBox boutons;

    private Label texteDuBas;
    private SimpleStringProperty message;
    private SimpleStringProperty couleurPanneau;
    private BooleanProperty pasEncoreDeClic;

    @Override
    public void start(Stage primaryStage)
    {
        root = new BorderPane();
        nbVert = new SimpleIntegerProperty(0);
        nbRouge = new SimpleIntegerProperty(0);
        nbBleu = new SimpleIntegerProperty(0);
        nbFois = new SimpleIntegerProperty(0);

        // On s'occupe du haut de la pages
        texteDuHaut = new Label();
        texteDuHaut.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        BorderPane.setAlignment(texteDuHaut, Pos.CENTER);
//        texteDuHaut.textProperty().bind(Bindings.concat(
//                "Total de clic : ", nbFois.asString(), " fois",
//                "; Vert choisi ", nbVert.asString(), " fois ;",
//                " Rouge choisi ", nbRouge.asString(), " fois ;",
//                " Bleu choisi ", nbBleu.asString(), " fois "
//        ));

        // On s'occupe du plateau
        panneau = new Pane();
        panneau.setPrefSize(800, 200);
        couleurPanneau = new SimpleStringProperty("#000000");
//        panneau.styleProperty().bind(Bindings.concat("-fx-background-color: ", couleurPanneau, ";"));

        // On s'occupe des boutons en bas
        VBox bas = new VBox();
        boutons = new HBox(10);
        boutons.setAlignment(Pos.CENTER);
        boutons.setPadding(new Insets(10, 5, 10, 5));
        texteDuBas = new Label();
        bas.setAlignment(Pos.CENTER_RIGHT);
        bas.getChildren().addAll(boutons, texteDuBas);

        // Appel de la méthode
        createBindings();

        vert = new Button("Vert");
        rouge = new Button("Rouge");
        bleu = new Button("Bleu");
        reset = new Button("Reset");

        // On s'occupe du texte en bas de la page
        message = new SimpleStringProperty("Pas de couleur = pas de couleur dans ça vie ");
        texteDuBas.textProperty().bind(message);

        // Gestionnaire des Actions pour les Boutons
        vert.setOnAction(e ->
        {
            nbVert.set(nbVert.get() + 1);
            nbFois.set(nbFois.get() + 1);
            couleurPanneau.set("#3a9c64;");
            message.set("On dit que le vert et une couleur chance...tu en pense quoi toi ? ");
        });

        rouge.setOnAction(e ->
        {
            nbRouge.set(nbRouge.get() + 1);
            nbFois.set(nbFois.get() + 1);
            couleurPanneau.set("#ed4f4f;");
            message.set("Le Rouge est une jolie couleur ! Mais c'est également la couleur de l'amour ! Et aussi de colère dans Inside Out :) ");
        });

        bleu.setOnAction(e ->
        {
            nbBleu.set(nbBleu.get() + 1);
            nbFois.set(nbFois.get() + 1);
            couleurPanneau.set("#67c3fb;");
            message.set("Tu savais que la grande barrière de corail pouvait être vue de la lune ?  ");
        });

        reset.setOnAction(e ->
        {
            nbVert.set(0);
            nbRouge.set(0);
            nbBleu.set(0);
            nbFois.set(0);
            couleurPanneau.set("#000000");
            message.set("Tu es de retour ? Pourquoi ;-; ? ");
        });

        boutons.getChildren().addAll(vert, rouge, bleu, reset);

        root.setCenter(panneau);
        root.setTop(texteDuHaut);
        root.setBottom(bas);

        // Création de la scene
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Méthode des bindings
    private void createBindings()
    {
        pasEncoreDeClic = new SimpleBooleanProperty(true);
        pasEncoreDeClic.bind(Bindings.equal(nbFois, 0));

        // Lien
        panneau.styleProperty().bind(Bindings.concat("-fx-background-color: ", couleurPanneau));

        texteDuHaut.textProperty().bind(Bindings.when(pasEncoreDeClic)
                .then("Il n'y a pas de clic au compteur...lance toi ! :D").otherwise(Bindings.concat(
                                "Total de clic : ", nbFois.asString(), " fois ; ",
                                "Vert choisi ", nbVert.asString(), " fois ; ",
                                "Rouge choisi ", nbRouge.asString(), " fois ; ",
                                "Bleu choisi ", nbBleu.asString(), " fois."
                ))
        );

        texteDuBas.styleProperty().bind(Bindings.concat("-fx-text-color-fill: ", couleurPanneau));
    }
}