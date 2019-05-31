package sample;

import db.AccesToData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import modelos.Equipo;
import modelos.Partido;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerPartidos implements Initializable {
    private AccesToData accesToData = new AccesToData();

    public ListView pListViewLocal;
    public ListView pListViewVisitante;

    public Label pLabelShowTeamLocal;
    public Label pLabelShowTeamVisitante;

    public TableColumn<Partido,String> puntosLocal = new TableColumn<>("Puntos Local");
    public TableColumn<Partido,String> puntosVisitante = new TableColumn<>("Puntos Visitante");
    public TableColumn<Partido,String> temporada = new TableColumn<>("Temporada");

    private ObservableList<String> itemsLocal = FXCollections.observableArrayList();
    private ObservableList<String> itemsVisiante = FXCollections.observableArrayList();
    private ObservableList<Partido> itemsTabla = FXCollections.observableArrayList();

    public TableView pTableView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        puntosLocal.setCellValueFactory(new PropertyValueFactory<Partido,String>("puntosLocal"));
        puntosVisitante.setCellValueFactory(new PropertyValueFactory<Partido,String>("puntosVisitante"));
        temporada.setCellValueFactory(new PropertyValueFactory<Partido,String>("temporada"));

        pTableView.getColumns().addAll(puntosLocal, puntosVisitante, temporada);
        pTableView.setItems(itemsTabla);

        CargarLocal(accesToData.getEquipos());
    }

    public void linkEquipos(ActionEvent actionEvent) {
        Main.SetScene("Equipo.fxml");
    }

    public void linkJugadores(ActionEvent actionEvent) {
        Main.SetScene("Jugador.fxml");
    }

    public void linkPartidos(ActionEvent actionEvent) {
        Main.SetScene("Partidos.fxml");
    }

    public void linkAltaEquipos(ActionEvent actionEvent) {
        Main.SetScene("AltaEquipo.fxml");
    }

    public void linkAltaJugadores(ActionEvent actionEvent) {
        Main.SetScene("AltaJugador.fxml");
    }

    private void CargarVisitante(ArrayList<Equipo> aux) {
        itemsVisiante.clear();

        for (var part : aux) {
            itemsVisiante.add(part.toString());
        }
        pListViewVisitante.setItems(itemsVisiante);
        FXCollections.sort(itemsVisiante);
    }

    private void CargarLocal(ArrayList<Equipo> aux) {
        itemsLocal.clear();

        for (var part : aux) {
            itemsLocal.add(part.toString());
        }
        pListViewLocal.setItems(itemsLocal);
        FXCollections.sort(itemsLocal);
    }

    public void pClickLocal(MouseEvent mouseEvent) {
        pLabelShowTeamLocal.setText(pListViewLocal.getSelectionModel().getSelectedItem().toString());
        ArrayList<Equipo> list = accesToData.getEquiposFromLocal(pLabelShowTeamLocal.getText());

        CargarVisitante(list);
    }

    public void pClickVisitante(MouseEvent mouseEvent) {
        itemsTabla.clear();
        pLabelShowTeamVisitante.setText(pListViewVisitante.getSelectionModel().getSelectedItem().toString());
        itemsTabla.addAll(accesToData.getPartido(pLabelShowTeamVisitante.getText(), pLabelShowTeamLocal.getText()));
    }
}
