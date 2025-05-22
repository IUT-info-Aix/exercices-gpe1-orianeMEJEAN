package fr.amu.iut.exercice18;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RectanglePerimeterCalculatorAndDrawer extends Application
{
    @Override
    public void start(Stage stage)
    {
        PanneauPrincipal root = new PanneauPrincipal();
        stage.setTitle("Perimeter Calculator And Drawer");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
