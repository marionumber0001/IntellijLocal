package modelos;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.ResultSet;

public class Jugador {

    private IntegerProperty codigo;
    private StringProperty nombre;
    private StringProperty procedencia;
    private StringProperty altura;
    private IntegerProperty peso;
    private StringProperty posicion;
    private StringProperty nombre_equipo;

    public Jugador() {

    }

    public Jugador(ResultSet resultado) throws Exception {
        this.codigo = new SimpleIntegerProperty(resultado.getInt("codigo"));
        this.nombre = new SimpleStringProperty(resultado.getString("Nombre"));
        this.procedencia = new SimpleStringProperty(resultado.getString("Procedencia"));
        this.altura = new SimpleStringProperty(resultado.getString("Altura"));
        this.peso = new SimpleIntegerProperty(resultado.getInt("Peso"));
        this.posicion = new SimpleStringProperty(resultado.getString("Posicion"));
        this.nombre_equipo = new SimpleStringProperty(resultado.getString("Nombre_equipo"));

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

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public String getProcedencia() {
        return procedencia.get();
    }

    public void setProcedencia(String procedencia) {
        this.procedencia.set(procedencia);
    }

    public StringProperty procedenciaProperty() {
        return procedencia;
    }

    public String getAltura() {
        return altura.get();
    }

    public void setAltura(String altura) {
        this.altura.set(altura);
    }

    public StringProperty alturaProperty() {
        return altura;
    }

    public int getPeso() {
        return peso.get();
    }

    public void setPeso(int peso) {
        this.peso.set(peso);
    }

    public IntegerProperty pesoProperty() {
        return peso;
    }

    public String getPosicion() {
        return posicion.get();
    }

    public void setPosicion(String posicion) {
        this.posicion.set(posicion);
    }

    public StringProperty posicionProperty() {
        return posicion;
    }

    public String getNombre_equipo() {
        return nombre_equipo.get();
    }

    public void setNombre_equipo(String nombre_equipo) {
        this.nombre_equipo.set(nombre_equipo);
    }

    public StringProperty nombre_equipoProperty() {
        return nombre_equipo;
    }

    @Override
    public String toString() {
        return this.getNombre();
    }
}
