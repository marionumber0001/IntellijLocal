package sample;

import com.jfoenix.controls.JFXDecorator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static Main instancia;
    private static Stage stage;
    private static boolean cargada = false;

    public static void main(String[] args) {
        launch(args);
    }

    public static void SetScene(String page) {
        try {
            Parent root = FXMLLoader.load(instancia.getClass().getResource(page));
            if (cargada) {
                Scene scene = new Scene(root);
                stage.setScene(scene);
                return;
            }

            JFXDecorator decorator = new JFXDecorator(stage, root, false, false, true);
            Scene scene = new Scene(decorator, 750, 500);
            stage.setMinWidth(750);
            stage.setMinHeight(500);
            String uri = instancia.getClass().getResource("../css/ph.css").toExternalForm();
            scene.getStylesheets().add(uri);

            cargada = true;
            stage.setScene(scene);
            return;

        } catch (java.io.IOException ex) {
            return;
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        /*primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root2, 750, 500));
        primaryStage.show();*/
        instancia = this;
        stage = primaryStage;

        primaryStage.setTitle("NBA");
        SetScene("sample.fxml");
        primaryStage.show();

    }

}
