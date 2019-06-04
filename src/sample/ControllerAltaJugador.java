package sample;

import db.AccesToData;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import modelos.Jugador;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerAltaJugador implements Initializable {
    private static Jugador update = null;
    private static AccesToData accesToData = new AccesToData();
    public TextField aFieldSearchNombre;
    public TextField aFieldSearchAltura;
    public TextField aFieldSearchPosicion;
    public TextField aFieldSearchProcedencia;
    public TextField aFieldSearchPeso;
    public TextField aFieldSearchEquipo;

    public static Jugador getJugador() {
        return update;
    }

    public static void setJugador(Jugador jug) {
        update = jug;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (update != null) {
            aFieldSearchPosicion.setText(update.getPosicion());
            aFieldSearchNombre.setText(update.getNombre());
            aFieldSearchAltura.setText(update.getAltura());
            aFieldSearchProcedencia.setText(update.getProcedencia());
            aFieldSearchPosicion.setText(update.getPosicion());
            aFieldSearchPeso.setText(String.valueOf(update.getPeso()));
            aFieldSearchEquipo.setText(update.getNombre_equipo());
        }
    }

    public void updateJugador(Jugador nuevo) {
        nuevo.setCodigo(update.getCodigo());
        accesToData.updateJugador(nuevo);
    }

    public boolean instertJugador(Jugador nuevo) {
        return accesToData.insertJugador(nuevo);
    }

    public void clickCancelar(ActionEvent actionEvent) {
        update = null;
        Main.SetScene("Jugador.fxml");
    }

    public void clickAceptar(ActionEvent actionEvent) {
        Jugador jugador = new Jugador();

        jugador.setNombre(aFieldSearchNombre.getText());
        jugador.setAltura(aFieldSearchAltura.getText());
        jugador.setPosicion(aFieldSearchPosicion.getText());
        jugador.setProcedencia(aFieldSearchProcedencia.getText());
        jugador.setPeso(Integer.valueOf(aFieldSearchPeso.getText()));
        jugador.setNombre_equipo(aFieldSearchEquipo.getText());

        if (update != null) updateJugador(jugador);
        else instertJugador(jugador);

        update = null;
        Main.SetScene("Equipo.fxml");
    }

    public void linkJugadores(ActionEvent actionEvent) {
        update = null;
        Main.SetScene("Jugador.fxml");
    }

    public void linkEquipos(ActionEvent actionEvent) {
        update = null;
        Main.SetScene("Equipo.fxml");
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
}