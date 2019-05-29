package sample;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;

public class ControllerEquipo {

    public JFXButton botonNavEquipo;
    public JFXButton botonNavPartido;
    public MenuButton botonNavModificacion;
    public Label eLabelNomEquipo;
    public ListView eListView;
    public Label eLabelConferencia;
    public Label eLabelDivision;
    public TableView fTable;

    public void linkJugadores(ActionEvent actionEvent) {
        Main.SetScene("Jugador.fxml");
    }

    public void clickModificar(ActionEvent actionEvent) {
    }

    public void clickEliminar(ActionEvent actionEvent) {
    }

    public void eKeyPress(KeyEvent keyEvent) {
    }
}
