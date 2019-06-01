package sample;

import db.AccesToData;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import modelos.Equipo;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerAltaEquipo implements Initializable {
    private static Equipo update = null;
    private static AccesToData accesToData = new AccesToData();
    public TextField aFieldSearchNombre;
    public TextField aFieldSearchCiudad;
    public TextField aFieldSearchConferancia;
    public TextField aFieldSearchDivision;

    public static Equipo getEquipo() {
        return update;
    }

    public static void setEquipo(Equipo jug) {
        update = jug;
    }

    public void updateEquipo(Equipo nuevo) {
        accesToData.updateEquipo(nuevo);
    }

    public boolean instertEquipo(Equipo nuevo) {
        return accesToData.insertEquipo(nuevo);
    }

    public void clickCancelar(ActionEvent actionEvent) {
        update = null;
        Main.SetScene("Equipo.fxml");
    }

    public void clickAceptar(ActionEvent actionEvent) {
        Equipo equipo = new Equipo();

        equipo.setNombre(aFieldSearchNombre.getText());
        equipo.setConferencia(aFieldSearchConferancia.getText());
        equipo.setDivision(aFieldSearchDivision.getText());
        equipo.setCiudad(aFieldSearchCiudad.getText());

        if (update != null) updateEquipo(equipo);
        else instertEquipo(equipo);

        update = null;
        Main.SetScene("Equipo.fxml");
    }

    public void linkEquipos(ActionEvent actionEvent) {
        update = null;
        Main.SetScene("Equipo.fxml");
    }

    public void linkJugadores(ActionEvent actionEvent) {
        update = null;
        Main.SetScene("Jugador.fxml");
    }

    public void linkPartidos(ActionEvent actionEvent) {
        update = null;
        Main.SetScene("Partidos.fxml");
    }

    public void linkAltaJugadores(ActionEvent actionEvent) {
        update = null;
        Main.SetScene("AltaJugador.fxml");
    }

    public void linkAltaEquipos(ActionEvent actionEvent) {
        update = null;
        Main.SetScene("AltaEquipo.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (update != null) {
            aFieldSearchNombre.setText(update.getNombre());
            aFieldSearchCiudad.setText(update.getCiudad());
            aFieldSearchDivision.setText(update.getDivision());
            aFieldSearchConferancia.setText(update.getConferencia());
        }
    }
}