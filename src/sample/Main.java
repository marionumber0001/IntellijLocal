package sample;

import com.jfoenix.controls.JFXDecorator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private static Main instancia;
    private static JFXDecorator decorator;

    public static void main(String[] args) {
        launch(args);
    }

    public static void SetScene(String page) {
        try {
            Parent root = FXMLLoader.load(instancia.getClass().getResource(page));
            decorator.setContent(root);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Equipo.fxml"));

        instancia = this;
        decorator = new JFXDecorator(primaryStage, root, false, false, true);

        primaryStage.setTitle("NBA");

        Scene scene = new Scene(decorator, 750, 500);
        primaryStage.setMaxWidth(750);
        primaryStage.setMaxHeight(500);
        primaryStage.setMinWidth(750);
        primaryStage.setMinHeight(500);
        primaryStage.setResizable(false);
        String uri = instancia.getClass().getResource("../css/ph.css").toExternalForm();
        scene.getStylesheets().add(uri);

        primaryStage.setScene(scene);
        primaryStage.show();


    }
}