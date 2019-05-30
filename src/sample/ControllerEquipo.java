package sample;

import com.jfoenix.controls.JFXButton;
import db.AccesToData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import modelos.Equipo;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerEquipo implements Initializable {

    private static AccesToData accesToData = new AccesToData();
    public JFXButton botonNavEquipo;
    public JFXButton botonNavPartido;
    public MenuButton botonNavModificacion;
    public Label eLabelNomEquipo;
    public ListView eListView;
    public Label eLabelConferencia;
    public Label eLabelDivision;
    public TableView fTable;
    public JFXButton botonNavJugador;
    public TextField eFieldSearcher;
    private ObservableList<String> items = FXCollections.observableArrayList();

    public void linkJugadores(ActionEvent actionEvent) {
        Main.SetScene("Jugador.fxml");
    }

    public void clickModificar(ActionEvent actionEvent) {
    }

    public void clickEliminar(ActionEvent actionEvent) {
    }

    public void elinkJugador(MouseEvent mouseEvent) {
        click(eListView.getSelectionModel().getSelectedItem().toString());
    }

    private void click(String toString) {
    }

    public void eKeyPress(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            cargaListView(accesToData.buscarEquipos(eFieldSearcher.getText()));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cargaListView(accesToData.getEquipos());
    }

    private void cargaListView(ArrayList<Equipo> aux) {
        items.clear();

        for (var jug : aux) {
            items.add(jug.toString());
        }
        eListView.setItems(items);
        FXCollections.sort(items);
    }


}
