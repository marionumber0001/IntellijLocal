package modelos;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.ResultSet;

public class Equipo {

    private StringProperty nombre;
    private StringProperty ciudad;
    private StringProperty conferencia;
    private StringProperty division;

    public Equipo() {

    }

    public Equipo(ResultSet resultado) throws Exception {
        this.nombre = new SimpleStringProperty(resultado.getString("Nombre"));
        this.ciudad = new SimpleStringProperty(resultado.getString("Ciudad"));
        this.conferencia = new SimpleStringProperty(resultado.getString("Conferencia"));
        this.division = new SimpleStringProperty(resultado.getString("Division"));
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public String getCiudad() {
        return ciudad.get();
    }

    public void setCiudad(String ciudad) {
        this.ciudad.set(ciudad);
    }

    public StringProperty ciudadProperty() {
        return ciudad;
    }

    public String getConferencia() {
        return conferencia.get();
    }

    public void setConferencia(String conferencia) {
        this.conferencia.set(conferencia);
    }

    public StringProperty conferenciaProperty() {
        return conferencia;
    }

    public String getDivision() {
        return division.get();
    }

    public void setDivision(String division) {
        this.division.set(division);
    }

    public StringProperty divisionProperty() {
        return division;
    }

    @Override
    public String toString() {
        return this.getNombre();
    }
}
