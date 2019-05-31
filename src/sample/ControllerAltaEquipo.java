package sample;

import javafx.event.ActionEvent;

public class ControllerAltaEquipo {
    public void clickCancelar(ActionEvent actionEvent) {
    }

    public void clickAceptar(ActionEvent actionEvent) {
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

    public void linkAltaJugadores(ActionEvent actionEvent) {
        Main.SetScene("AltaJugador.fxml");
    }

    public void linkAltaEquipos(ActionEvent actionEvent) {
        Main.SetScene("AltaEquipo.fxml");
    }
}
