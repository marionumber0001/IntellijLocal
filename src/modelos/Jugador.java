package modelos;

import java.sql.ResultSet;

public class Jugador {

    private int codigo;
    private String nombre;
    private String procedencia;
    private String altura;
    private int peso;
    private String  posicion;
    private String nombre_equipo;

    public  Jugador (ResultSet resultado) throws Exception{
        this.codigo = resultado.getInt("codigo");
        this.nombre = resultado.getString("Nombre");
        this.procedencia = resultado.getString("Procedencia");
        this.altura = resultado.getString("Altura");
        this.peso = resultado.getInt("Peso");
        this.posicion = resultado.getString("Posicion");
        this.nombre_equipo = resultado.getString("Nombre_equipo");

    }
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProcedencia() {
        return procedencia;
    }

    public void setProcedencia(String procedencia) {
        this.procedencia = procedencia;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public String getNombre_equipo() {
        return nombre_equipo;
    }

    public void setNombre_equipo(String nombre_equipo) {
        this.nombre_equipo = nombre_equipo;
    }
}
