package db;

import modelos.Jugador;

import java.util.ArrayList;

public class AccesToData {

    private static BaseDeDatos baseDeDatos = BaseDeDatos.getInstancia();

    public ArrayList<Jugador> getJugadores(){

        try {
            ArrayList<Jugador> jugadors = new ArrayList<Jugador>();

            var statement = baseDeDatos.conectarMySQL().prepareStatement("SELECT * FROM jugadores");
            var resultado = statement.executeQuery();

            while (resultado.next())
                jugadors.add(new Jugador(resultado));

            return jugadors;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
