package sample;

import db.AccesToData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelos.Jugador;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerPlayer implements Initializable {
    private static AccesToData accesToData = new AccesToData();
    public ListView listaJugadores;
    public TableView<Jugador> fTable;
    /*
    COLUMNAS
     */
    public ObservableList<Jugador> jData = FXCollections.observableArrayList();
    public TableColumn<Jugador, String> fJatributo = new TableColumn<>("");
    public TableColumn<Jugador, String> fJvalor = new TableColumn<>("");
    private ObservableList<String> items = FXCollections.observableArrayList();
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

        jData.add(accesToData.getJugador(66));

        fJatributo.setCellValueFactory(new PropertyValueFactory<Jugador, String>("nombre"));
        fJvalor.setCellValueFactory(new PropertyValueFactory<Jugador, String>("procedencia"));

        fTable.setItems(jData);
        fTable.getColumns().addAll(fJatributo, fJvalor);
    }
}
