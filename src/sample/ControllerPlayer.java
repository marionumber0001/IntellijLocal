package sample;

import db.AccesToData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import modelos.Jugador;

import java.util.ArrayList;

public class ControllerPlayer {
    private static AccesToData accesToData = new AccesToData();

    @FXML
    private ListView<String> listaJugadores = new ListView<String>();
    public ControllerPlayer(){
        ArrayList<Jugador> jugadors = accesToData.getJugadores();

        ObservableList<String> items = FXCollections.observableArrayList();

        for (var jugador: jugadors)
            items.add(jugador.getNombre());

        listaJugadores.setItems(items);
        listaJugadores.setCellFactory();

    }
    public void linkJugadores(ActionEvent actionEvent) {
        Main.SetScene("Player.fxml");
    }
}
