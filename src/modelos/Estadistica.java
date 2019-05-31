package modelos;

import javafx.beans.property.*;

import java.sql.ResultSet;

public class Estadistica {

    private StringProperty temporada;
    private IntegerProperty jugador;
    private FloatProperty puntosPorPartido;
    private FloatProperty asistenciasPorPartido;
    private FloatProperty taponesPorPartido;
    private FloatProperty rebotesPorPartido;

    public Estadistica() {

    }

    public Estadistica(ResultSet result) throws Exception {
        temporada = new SimpleStringProperty(result.getString("temporada"));
        jugador = new SimpleIntegerProperty(result.getInt("jugador"));
        puntosPorPartido = new SimpleFloatProperty(result.getFloat("Puntos_por_partido"));
        asistenciasPorPartido = new SimpleFloatProperty(result.getFloat("Asistencias_por_partido"));
        taponesPorPartido = new SimpleFloatProperty(result.getFloat("Tapones_por_partido"));
        rebotesPorPartido = new SimpleFloatProperty(result.getFloat("Rebotes_por_partido"));
    }

    public String getTemporada() {
        return temporada.get();
    }

    public void setTemporada(String temporada) {
        this.temporada.set(temporada);
    }

    public StringProperty temporadaProperty() {
        return temporada;
    }

    public int getJugador() {
        return jugador.get();
    }

    public void setJugador(int jugador) {
        this.jugador.set(jugador);
    }

    public IntegerProperty jugadorProperty() {
        return jugador;
    }

    public float getPuntosPorPartido() {
        return puntosPorPartido.get();
    }

    public void setPuntosPorPartido(float puntosPorTemporada) {
        this.puntosPorPartido.set(puntosPorTemporada);
    }

    public FloatProperty puntosPorPartidoProperty() {
        return puntosPorPartido;
    }

    public float getAsistenciasPorPartido() {
        return asistenciasPorPartido.get();
    }

    public void setAsistenciasPorPartido(float asistenciasPorPartido) {
        this.asistenciasPorPartido.set(asistenciasPorPartido);
    }

    public FloatProperty asistenciasPorPartidoProperty() {
        return asistenciasPorPartido;
    }

    public float getTaponesPorPartido() {
        return taponesPorPartido.get();
    }

    public void setTaponesPorPartido(float taponesPorPartido) {
        this.taponesPorPartido.set(taponesPorPartido);
    }

    public FloatProperty taponesPorPartidoProperty() {
        return taponesPorPartido;
    }

    public float getRebotesPorPartido() {
        return rebotesPorPartido.get();
    }

    public void setRebotesPorPartido(float rebotesPorPartido) {
        this.rebotesPorPartido.set(rebotesPorPartido);
    }

    public FloatProperty rebotesPorPartidoProperty() {
        return rebotesPorPartido;
    }

    @Override
    public String toString() {
        return getTemporada();
    }
}
