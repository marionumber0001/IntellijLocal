package db;

import modelos.Equipo;
import modelos.Estadistica;
import modelos.Jugador;
import modelos.Partido;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.function.Function;

public class AccesToData {

    private static BaseDeDatos baseDeDatos = BaseDeDatos.getInstancia();

    ///////////////////////////////         JUGADORES          /////////////////////////////////////////////////
    public ArrayList<Jugador> getJugadores() {
        try {
            ArrayList<Jugador> jugadors = new ArrayList<Jugador>();

            var statement = baseDeDatos.conectarMySQL().prepareStatement("SELECT * FROM jugadores");
            var resultado = statement.executeQuery();

            while (resultado.next())
                jugadors.add(new Jugador(resultado));

            return jugadors;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public Jugador getJugador(int id) {
        try {
            var statement = baseDeDatos.conectarMySQL().prepareStatement("SELECT * FROM jugadores WHERE codigo LIKE " + id);
            var resultado = statement.executeQuery();

            if (resultado.next())
                return new Jugador(resultado);
            else return null;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Jugador getJugador(String nombre) {
        try {
            var statement = baseDeDatos.conectarMySQL().prepareStatement("SELECT * FROM jugadores WHERE Nombre LIKE '" + nombre + "'");
            var resultado = statement.executeQuery();

            if (resultado.next())
                return new Jugador(resultado);
            else return null;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Jugador> getJugadorFromEquipo(String equipo) {
        try {
            ArrayList<Jugador> jugadors = new ArrayList<Jugador>();

            var statement = baseDeDatos.conectarMySQL().prepareStatement("SELECT * FROM jugadores WHERE Nombre_equipo LIKE '" + equipo + "'");
            var resultado = statement.executeQuery();

            while (resultado.next())
                jugadors.add(new Jugador(resultado));

            return jugadors;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //////////////////////////////        EQUIPO         ///////////////////////////////////////////////
    public ArrayList<Equipo> getEquipos() {
        try {
            ArrayList<Equipo> equipos = new ArrayList<>();

            var statement = baseDeDatos.conectarMySQL().prepareStatement("SELECT * FROM equipos");
            var resultado = statement.executeQuery();

            while (resultado.next())
                equipos.add(new Equipo(resultado));

            return equipos;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Equipo getEquipo(String nombre) {
        try {
            var statement = baseDeDatos.conectarMySQL().prepareStatement("SELECT * FROM equipos WHERE Nombre LIKE '" + nombre + "'");
            var resultado = statement.executeQuery();

            if (resultado.next())
                return new Equipo(resultado);
            else return null;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    ////////////////////////        PARTIDO       /////////////////////////////
    public Partido getPartido(int id) {
        try {
            var statement = baseDeDatos.conectarMySQL().prepareStatement("SELECT * FROM partidos WHERE codigo LIKE " + id);
            var resultado = statement.executeQuery();

            if (resultado.next())
                return new Partido(resultado);
            else return null;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Partido> getPartidos() {
        try {
            ArrayList<Partido> partidos = new ArrayList<>();

            var statement = baseDeDatos.conectarMySQL().prepareStatement("SELECT * FROM partidos");
            var resultado = statement.executeQuery();

            while (resultado.next())
                partidos.add(new Partido(resultado));

            return partidos;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    ////////////////////////        ESTADISTICAS       ////////////////////////////
    public ArrayList<Estadistica> getEstadisticas() {
        try {
            ArrayList<Estadistica> estadisticas = new ArrayList<>();

            var statement = baseDeDatos.conectarMySQL().prepareStatement("SELECT * FROM estadisticas");
            var resultado = statement.executeQuery();

            while (resultado.next())
                estadisticas.add(new Estadistica(resultado));

            return estadisticas;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Estadistica getEstadistica(int jugador, String temporada) {
        try {
            var statement = baseDeDatos.conectarMySQL().prepareStatement("SELECT * FROM estadisticas WHERE temporada = '" + temporada + "' AND jugador LIKE " + jugador);
            var resultado = statement.executeQuery();

            if (resultado.next())
                return new Estadistica(resultado);
            else return null;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /////////////////////////////      ALGORITMOS DE BUSQUEDA     ////////////////////////
    public ArrayList<Jugador> buscarJugadores(String busqueda) {

        ArrayList<Jugador> jugadors = new ArrayList<>();

        final int str = 1;
        final int num = 0;
        BiFunction<String, Integer, Boolean> func = (String campo, Integer tipo) ->
        {
            try {
                PreparedStatement statement;
                if (tipo == 1)
                    statement = baseDeDatos.conectarMySQL().prepareStatement(
                            "SELECT * FROM jugadores WHERE " + campo + " LIKE '%" + busqueda + "%'"
                    );
                else
                    statement = baseDeDatos.conectarMySQL().prepareStatement(
                            "SELECT * FROM jugadores WHERE " + campo + " LIKE " + busqueda + ""
                    );
                var resultado = statement.executeQuery();

                while (resultado.next())
                    jugadors.add(new Jugador(resultado));

                if (jugadors.isEmpty()) return false;
                else return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        };

        if (func.apply("Nombre", str)) return jugadors;
        if (func.apply("Nombre_equipo", str)) return jugadors;
        if (func.apply("Procedencia", str)) return jugadors;
        if (func.apply("Altura", str)) return jugadors;
        if (func.apply("Posicion", str)) return jugadors;
        if (func.apply("Peso", num)) return jugadors;

        return jugadors;
    }

    public ArrayList<Equipo> buscarEquipos(String busqueda) {

        ArrayList<Equipo> equipos = new ArrayList<>();

        final int str = 1;
        final int num = 0;
        Function<String, Boolean> func = (String campo) ->
        {
            try {
                PreparedStatement statement = baseDeDatos.conectarMySQL().prepareStatement(
                        "SELECT * FROM equipos WHERE " + campo + " LIKE '%" + busqueda + "%'"
                );
                var resultado = statement.executeQuery();

                while (resultado.next())
                    equipos.add(new Equipo(resultado));

                if (equipos.isEmpty()) return false;
                else return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        };

        if (func.apply("Nombre")) return equipos;
        if (func.apply("Ciudad")) return equipos;
        if (func.apply("Conferencia")) return equipos;
        if (func.apply("Division")) return equipos;

        return equipos;
    }
}

