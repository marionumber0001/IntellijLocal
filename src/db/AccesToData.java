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

    /////////////////////////////// JUGADORES /////////////////////////////////////////////////
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

    public boolean insertJugador(Jugador jug) {
        try {
            var statement = baseDeDatos.conectarMySQL().prepareStatement("SELECT MAX(codigo) AS id FROM jugadores");
            var resultado = statement.executeQuery();

            resultado.next();
            int id = resultado.getInt("id") + 1;

            statement = baseDeDatos.conectarMySQL().prepareStatement(
                    String.format(
                            "INSERT INTO jugadores (codigo,Nombre,Procedencia,Altura,Peso,Posicion,Nombre_equipo)" +
                                    "VALUES (%s , '%s' , '%s' , '%s' , %s, '%s', '%s')",
                            id, jug.getNombre(), jug.getProcedencia(), jug.getAltura(), jug.getPeso(), jug.getPosicion(), jug.getNombre_equipo()
                    )
            );
            statement.executeUpdate();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void updateJugador(Jugador update) {
        BiFunction<String, String, Boolean> function = (String campo, String valor) ->
        {
            try {
                var statement = baseDeDatos.conectarMySQL().prepareStatement(
                        "UPDATE jugadores SET " + campo + " = " + valor + " WHERE codigo = " + update.getCodigo()
                );
                statement.executeUpdate();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        };

        Jugador jugador = getJugador(update.getCodigo());

        if (jugador.getNombre() != update.getNombre()) function.apply("Nombre", "'" + update.getNombre() + "'");
        if (jugador.getNombre_equipo() != update.getNombre_equipo())
            function.apply("Nombre_equipo", "'" + update.getNombre_equipo() + "'");
        if (jugador.getAltura() != update.getAltura()) function.apply("Altura", "'" + update.getAltura() + "'");
        if (jugador.getPeso() != update.getPeso()) function.apply("Peso", String.valueOf(update.getPeso()));
        if (jugador.getPosicion() != update.getPosicion()) function.apply("Posicion", "'" + update.getPosicion() + "'");
        if (jugador.getProcedencia() != update.getProcedencia())
            function.apply("Procedencia", "'" + update.getProcedencia() + "'");
    }

    public boolean deleteJugador(int id) {
        try {
            var statement = baseDeDatos.conectarMySQL().prepareStatement("DELETE FROM jugadores WHERE codigo LIKE " + id);
            var resultado = statement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    ////////////////////////////// EQUIPO ///////////////////////////////////////////////
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

    public ArrayList<Equipo> getEquiposFromLocal(String local) {
        try {
            ArrayList<Equipo> equipos = new ArrayList<>();

            var statement = baseDeDatos.conectarMySQL().prepareStatement
                    ("SELECT DISTINCT Nombre, Ciudad, Conferencia, Division FROM equipos INNER JOIN partidos ON equipo_visitante LIKE Nombre WHERE equipo_local LIKE '" + local + "'");
            var resultado = statement.executeQuery();

            while (resultado.next())
                equipos.add(new Equipo(resultado));

            return equipos;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean insertEquipo(Equipo equip) {
        try {
            var statement = baseDeDatos.conectarMySQL().prepareStatement(
                    String.format(
                            "INSERT INTO equipos (Nombre,Ciudad,Conferencia,Division)" +
                                    "VALUES ('%s' , '%s' , '%s' , '%s')",
                            equip.getNombre(), equip.getCiudad(), equip.getConferencia(), equip.getDivision()
                    )
            );
            statement.executeUpdate();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void updateEquipo(Equipo update) {
        BiFunction<String, String, Boolean> function = (String campo, String valor) ->
        {
            try {
                var statement = baseDeDatos.conectarMySQL().prepareStatement(
                        "UPDATE equipos SET " + campo + " = " + valor + " WHERE Nombre = '" + update.getNombre() + "'"
                );
                statement.executeUpdate();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        };

        Equipo equipo = getEquipo(update.getNombre());

        if (equipo.getCiudad() != update.getCiudad()) function.apply("Ciudad", "'" + update.getCiudad() + "'");
        if (equipo.getConferencia() != update.getConferencia())
            function.apply("Conferencia", "'" + update.getConferencia() + "'");
        if (equipo.getDivision() != update.getDivision()) function.apply("Division", "'" + update.getDivision() + "'");
    }

    public boolean deleteEquipo(String nombre) {
        try {
            var statement = baseDeDatos.conectarMySQL().prepareStatement("DELETE FROM equipos WHERE Nombre LIKE '" + nombre + "'");
            statement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //////////////////////// PARTIDO /////////////////////////////
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

    public ArrayList<Partido> getPartido(String visitante, String local) {
        try {
            ArrayList<Partido> partidos = new ArrayList<>();

            var statement = baseDeDatos.conectarMySQL().prepareStatement(
                    "SELECT * FROM partidos WHERE equipo_local LIKE '" + local + "'" + "AND equipo_visitante LIKE '" + visitante + "'"
            );
            var resultado = statement.executeQuery();

            while (resultado.next())
                partidos.add(new Partido(resultado));

            return partidos;

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

    //////////////////////// ESTADISTICAS ////////////////////////////
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

    public ArrayList<Estadistica> getEstadisticas(int jugador, String temporada) {
        try {
            ArrayList<Estadistica> estadisticas = new ArrayList<>();

            var statement = baseDeDatos.conectarMySQL().prepareStatement("SELECT * FROM estadisticas WHERE temporada LIKE '" + temporada + "' AND jugador LIKE " + jugador);
            var resultado = statement.executeQuery();

            while (resultado.next())
                estadisticas.add(new Estadistica(resultado));

            return estadisticas;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    ///////////////////////////// ALGORITMOS DE BUSQUEDA ////////////////////////
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

                return !jugadors.isEmpty();
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

        try {
            Double.parseDouble(busqueda);
        } catch (Exception e) {
            return jugadors;
        }

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

                return !equipos.isEmpty();
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