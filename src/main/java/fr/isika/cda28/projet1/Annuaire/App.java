package fr.isika.cda28.projet1.Annuaire;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        

        Label label = new Label("ICI CEST LES CDA 28 GROUPE 2 ");
        Scene scene = new Scene(new StackPane(label), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    
    // Coucou  david
    // Allez DAVID TU PEUX LE FAIRE

}