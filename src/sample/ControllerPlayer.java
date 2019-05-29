package sample;

import db.AccesToData;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import modelos.Jugador;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerPlayer implements Initializable {
    private static AccesToData accesToData = new AccesToData();

    public ListView listaJugadores;

    public TableView<Jugador> fTable;

    public TableColumn<String[], String> fJatributo = new TableColumn<>("");
    public TableColumn<String[], String> fJvalor = new TableColumn<>("");

    /*
    COLUMNAS
     */
    private ObservableList<String> items = FXCollections.observableArrayList();
    private ObservableList<String[]> jData = FXCollections.observableArrayList();
     /*
    COLUMNAS
     */

    public void linkJugadores(ActionEvent actionEvent) {
        Main.SetScene("Player.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (var jug : accesToData.getJugadores()) {
            items.add(jug.toString());
        }
        listaJugadores.setItems(items);
        FXCollections.sort(items);

        fJatributo.setCellValueFactory((String e [])-> Bindings.createStringBinding(()->e[0]));

        fTable.getColumns().addAll(fJatributo, fJvalor);
    }

}
