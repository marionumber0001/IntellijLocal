package modelos;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

import java.sql.ResultSet;

public class Partido {

    public IntegerProperty codigo;
    public StringProperty equipoLocal;
    public StringProperty equipoVisitante;
    public IntegerProperty puntosLocal;
    public IntegerProperty puntosVisitante;
    public StringProperty temporada;

    public Partido(ResultSet result) throws Exception {
        setCodigo(result.getInt("codigo"));
        setEquipoLocal(result.getString("equipo_local"));
        setEquipoVisitante(result.getString("equipo_visitante"));
        setPuntosLocal(result.getInt("equipo_visitante"));
        setPuntosVisitante(result.getInt("puntos_visitante"));
        setTemporada(result.getString("temporada"));
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
