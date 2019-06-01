package sample;

import com.jfoenix.controls.JFXButton;
import db.AccesToData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import modelos.Equipo;
import modelos.Jugador;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerEquipo implements Initializable {

    private static AccesToData accesToData = new AccesToData();
    public JFXButton botonNavEquipo;
    public JFXButton botonNavPartido;
    public MenuButton botonNavModificacion;
    public Label eLabelNomEquipo;
    public Label eLabelConferencia;
    public Label eLabelDivision;
    public JFXButton botonNavJugador;
    public TextField eFieldSearcher;
    public ListView eListView;

    public TableColumn<Jugador, String> nombre = new TableColumn<>("Nombre");
    public TableColumn<Jugador, String> procedencia = new TableColumn<>("Procednecia");
    public TableColumn<Jugador, String> posicion = new TableColumn<>("Posición");
    public TableView<Jugador> fTable;

    private ObservableList<Jugador> itemsTabla = FXCollections.observableArrayList();
    private ObservableList<String> items = FXCollections.observableArrayList();

    public void clickModificar(ActionEvent actionEvent) {
        ControllerAltaEquipo.setEquipo(accesToData.getEquipo(eLabelNomEquipo.getText()));
        Main.SetScene("AltaEquipo.fxml");
    }

    public void clickEliminar(ActionEvent actionEvent) {
        accesToData.deleteEquipo(eLabelNomEquipo.getText());
        Main.SetScene("Equipo.fxml");
    }

    private void click(String nom) {
        var list = accesToData.getJugadorFromEquipo(nom);
        itemsTabla.clear();
        itemsTabla.addAll(list);

        var equipo = accesToData.getEquipo(nom);
        eLabelNomEquipo.setText(equipo.getNombre());
        eLabelConferencia.setText(equipo.getConferencia());
        eLabelDivision.setText(equipo.getDivision());
    }

    public void eKeyPress(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            cargaListView(accesToData.buscarEquipos(eFieldSearcher.getText()));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cargaListView(accesToData.getEquipos());

        nombre.setCellValueFactory(new PropertyValueFactory<Jugador, String>("nombre"));
        procedencia.setCellValueFactory(new PropertyValueFactory<Jugador, String>("procedencia"));
        posicion.setCellValueFactory(new PropertyValueFactory<Jugador, String>("posicion"));

        fTable.getColumns().addAll(nombre, procedencia, posicion);
        fTable.setItems(itemsTabla);
    }

    private void cargaListView(ArrayList<Equipo> aux) {
        items.clear();

        for (var jug : aux) {
            items.add(jug.toString());
        }
        eListView.setItems(items);
        FXCollections.sort(items);
    }

    public void elinkEquipo(MouseEvent mouseEvent) {
        click(eListView.getSelectionModel().getSelectedItem().toString());
    }

    public void linkEquipos(ActionEvent actionEvent) {
        Main.SetScene("Equipo.fxml");
    }

    public void linkPartidos(ActionEvent actionEvent) {
        Main.SetScene("Partidos.fxml");
    }

    public void linkAltaJugadores(ActionEvent actionEvent) {
        Main.SetScene("AltaJugador.fxml");
    }

    public void linkJugadores(ActionEvent actionEvent) {
        Main.SetScene("Jugador.fxml");
    }

    public void linkAltaEquipos(ActionEvent actionEvent) {
        Main.SetScene("AltaEquipo.fxml");
    }
}