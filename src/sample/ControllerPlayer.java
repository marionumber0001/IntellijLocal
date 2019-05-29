package sample;

import db.AccesToData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import modelos.Jugador;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerPlayer implements Initializable {
    private static AccesToData accesToData = new AccesToData();

    public ListView pyListViewTotal;
    public TextField pySearcher;
    public Label pyLabelNameTeam;
    private ObservableList<String> items = FXCollections.observableArrayList();

    /*
    public TableView<ArrayList<String>> fTable;
    public TableColumn<ArrayList<String>, String> fJatributo = new TableColumn<>("");
    public TableColumn<ArrayList<String>, String> fJvalor = new TableColumn<>("");
    private ObservableList<ArrayList<String>> jData = FXCollections.observableArrayList();
     */

    public void linkJugadores(ActionEvent actionEvent) {
        Main.SetScene("Jugador.fxml");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*
        fJatributo.setCellValueFactory(e -> Bindings.createStringBinding(() -> e.getValue().get(0)));
        fJvalor.setCellValueFactory(e -> Bindings.createStringBinding(() -> e.getValue().get(1)));
        fTable.getColumns().addAll(fJatributo, fJvalor);
         */
        cargaListView(accesToData.getJugadores());
    }

    //Esta funcion se debe ejecutar al hacer click en un jugador
    public void click(String nom) {
       /* jData.clear();

        var jug = accesToData.getJugador(nom);

        BiFunction<String, String, Boolean> function = (String tipo, String valor) -> {
            ArrayList<String> nombre = new ArrayList<>();
            nombre.add(tipo);
            nombre.add(valor);
            jData.add(nombre);

            return true;
        };

        function.apply("NOMBRE", jug.getNombre());
        function.apply("PROCEDENCIA", jug.getProcedencia());
        function.apply("ALTURA", jug.getAltura());
        function.apply("PESO", jug.getPeso() + " lbs");
        function.apply("POSICIÓN", jug.getPosicion());

        fTable.setItems(jData);

        */

        //Sucesivamente con todas las propieadades
    }

    public void linkJugador(MouseEvent mouseEvent) {
        click(pyListViewTotal.getSelectionModel().getSelectedItem().toString());
    }

    public void pyKeyPress(KeyEvent keyEvent) {

        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            cargaListView(accesToData.buscarJugadores(pySearcher.getText()));
        }
    }

    private void cargaListView(ArrayList<Jugador> aux) {
        items.clear();

        for (var jug : aux) {
            items.add(jug.toString());
        }
        pyListViewTotal.setItems(items);
        FXCollections.sort(items);
    }

    public void clickModificar(ActionEvent actionEvent) {
    }

    public void clickEliminar(ActionEvent actionEvent) {
    }
}
