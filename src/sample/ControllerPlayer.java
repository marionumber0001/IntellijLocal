package sample;

import db.AccesToData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import modelos.Estadistica;
import modelos.Jugador;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Function;


public class ControllerPlayer implements Initializable {
    private static AccesToData accesToData = new AccesToData();

    public ListView pyListViewTotal;
    public ListView pyListViewTeam;

    public TextField pySearcher;

    public Label pyLabelNameTeam;
    public Label pyLabelAltura;
    public Label pyLabelProcedencia;
    public Label pyLabelEquipo;
    public Label pyLabelPosicion;
    public Label pyLabelPeso;

    public AreaChart<String, Number> pyGrafica;
    public CategoryAxis xAxis;
    public NumberAxis yAxis;

    private ObservableList<String> items = FXCollections.observableArrayList();
    private ObservableList<String> itemsJug = FXCollections.observableArrayList();


    public void linkJugadores(ActionEvent actionEvent) {
        Main.SetScene("Jugador.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pyGrafica.setTitle("Estadisticas del jugador por partido");
        cargaListView(accesToData.getJugadores());
    }

    public void click(String nom) {
        var jugador = accesToData.getJugador(nom);
        cargaListViewJug(accesToData.getJugadorFromEquipo(jugador.getNombre_equipo()));

        pyLabelNameTeam.setText(jugador.getNombre());
        pyLabelEquipo.setText(jugador.getNombre_equipo());
        pyLabelAltura.setText(jugador.getAltura());
        pyLabelPeso.setText(jugador.getPeso() + " lbs");
        pyLabelProcedencia.setText(jugador.getProcedencia());
        pyLabelPosicion.setText(jugador.getPosicion());

        cargarTabla(jugador.getCodigo());
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

    private void cargaListViewJug(ArrayList<Jugador> aux) {
        itemsJug.clear();

        for (var jug : aux) {
            itemsJug.add(jug.toString());
        }
        pyListViewTeam.setItems(itemsJug);
        FXCollections.sort(itemsJug);
    }

    public void cargarTabla(int jugador) {
        ArrayList<Estadistica> estadisticas = accesToData.getEstadisticas(jugador, "%");

        ObservableList x = FXCollections.<String>observableArrayList();
        estadisticas.forEach(e -> x.add(e.getTemporada()));

        xAxis.setAutoRanging(false);
        xAxis.setCategories(x);

        Function<Estadistica, Float> high = e ->
        {
            Float big = new Float(0);

            if (e.getPuntosPorPartido() > big) big = e.getPuntosPorPartido();
            if (e.getAsistenciasPorPartido() > big) big = e.getAsistenciasPorPartido();
            if (e.getTaponesPorPartido() > big) big = e.getTaponesPorPartido();
            if (e.getRebotesPorPartido() > big) big = e.getRebotesPorPartido();

            return big;
        };

        ArrayList<Integer> big = new ArrayList<Integer>();
        big.add(0);
        estadisticas.forEach(e ->
        {
            Float tmp = high.apply(e);
            if (tmp > big.get(0)) big.set(0, tmp.intValue());
        });
        int bigger = big.get(0);

        yAxis.setAutoRanging(false);
        yAxis.setUpperBound(bigger + 1);
        yAxis.setLowerBound(0);
        yAxis.setTickUnit(Integer.valueOf(bigger / 7) + 1);

        XYChart.Series puntos = new XYChart.Series();
        puntos.setName("Puntos");
        estadisticas.forEach(e -> puntos.getData().addAll(new XYChart.Data(e.getTemporada(), e.getPuntosPorPartido())));

        XYChart.Series asistencia = new XYChart.Series();
        asistencia.setName("Asistencia");
        estadisticas.forEach(e -> asistencia.getData().addAll(new XYChart.Data(e.getTemporada(), e.getAsistenciasPorPartido())));

        XYChart.Series tapones = new XYChart.Series();
        tapones.setName("Tapones");
        estadisticas.forEach(e -> tapones.getData().addAll(new XYChart.Data(e.getTemporada(), e.getTaponesPorPartido())));

        XYChart.Series rebotes = new XYChart.Series();
        rebotes.setName("Rebotes");
        estadisticas.forEach(e -> rebotes.getData().addAll(new XYChart.Data(e.getTemporada(), e.getRebotesPorPartido())));


        pyGrafica.getData().clear();
        pyGrafica.getData().addAll(asistencia, tapones, rebotes, puntos);

    }

    public void clickModificar(ActionEvent actionEvent) {
        ControllerAltaJugador.setJugador(accesToData.getJugador(pyLabelNameTeam.getText()));
        Main.SetScene("AltaJugador.fxml");
    }

    public void clickEliminar(ActionEvent actionEvent) {
        accesToData.deleteJugador(accesToData.getJugador(pyLabelNameTeam.getText()).getCodigo());
        Main.SetScene("Jugador.fxml");
    }

    public void linkJugadorJug(MouseEvent mouseEvent) {
        click(pyListViewTeam.getSelectionModel().getSelectedItem().toString());
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

    public void linkAltaEquipos(ActionEvent actionEvent) {
        Main.SetScene("AltaEquipo.fxml");
    }
}