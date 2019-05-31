package modelos;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.ResultSet;

public class Partido {

    public IntegerProperty codigo;
    public StringProperty equipoLocal;
    public StringProperty equipoVisitante;
    public IntegerProperty puntosLocal;
    public IntegerProperty puntosVisitante;
    public StringProperty temporada;

    public Partido() {
        codigo = new SimpleIntegerProperty();
        equipoLocal = new SimpleStringProperty();
        equipoVisitante = new SimpleStringProperty();
        puntosLocal = new SimpleIntegerProperty();
        puntosVisitante = new SimpleIntegerProperty();
        temporada = new SimpleStringProperty();
    }

    public Partido(ResultSet result) throws Exception {
        codigo = new SimpleIntegerProperty(result.getInt("codigo"));
        equipoLocal = new SimpleStringProperty(result.getString("equipo_local"));
        equipoVisitante = new SimpleStringProperty("equipo_visitante");
        puntosLocal = new SimpleIntegerProperty(result.getInt("puntos_local"));
        puntosVisitante = new SimpleIntegerProperty(result.getInt("puntos_visitante"));
        temporada = new SimpleStringProperty(result.getString("temporada"));
    }

    public int getCodigo() {
        return codigo.get();
    }

    public void setCodigo(int codigo) {
        this.codigo.set(codigo);
    }

    public IntegerProperty codigoProperty() {
        return codigo;
    }

    public String getEquipoLocal() {
        return equipoLocal.get();
    }

    public void setEquipoLocal(String equipoLocal) {
        this.equipoLocal.set(equipoLocal);
    }

    public StringProperty equipoLocalProperty() {
        return equipoLocal;
    }

    public String getEquipoVisitante() {
        return equipoVisitante.get();
    }

    public void setEquipoVisitante(String equipoVisitante) {
        this.equipoVisitante.set(equipoVisitante);
    }

    public StringProperty equipoVisitanteProperty() {
        return equipoVisitante;
    }

    public int getPuntosLocal() {
        return puntosLocal.get();
    }

    public void setPuntosLocal(int puntosLocal) {
        this.puntosLocal.set(puntosLocal);
    }

    public IntegerProperty puntosLocalProperty() {
        return puntosLocal;
    }

    public int getPuntosVisitante() {
        return puntosVisitante.get();
    }

    public void setPuntosVisitante(int puntosVisitante) {
        this.puntosVisitante.set(puntosVisitante);
    }

    public IntegerProperty puntosVisitanteProperty() {
        return puntosVisitante;
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

    @Override
    public String toString() {
        return getEquipoLocal() + " / " + getEquipoVisitante();
    }
}
