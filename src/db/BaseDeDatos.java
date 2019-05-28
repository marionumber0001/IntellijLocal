package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDeDatos {
    static BaseDeDatos instancia = null;

    // Librería de MySQL
    private String driver = "com.mysql.jdbc.Driver";
    // Nombre de la base de datos
    private String database = "nba";
    // Host
    private String hostname = "nba.cqgv7vsphdtj.us-east-2.rds.amazonaws.com";
    // Puerto
    private String port = "3306";
    // Ruta de nuestra base de datos (desactivamos el uso de SSL con "?useSSL=false")
    private String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?useSSL=false";
    // Nombre de usuario
    private String username = "ruut";
    // Clave de usuario
    private String password = "Holamundo66";

    private BaseDeDatos(){

    }

    public static BaseDeDatos getInstancia(){
     if (instancia==null)
         instancia = new BaseDeDatos();

     return instancia;
    }

    public Connection conectarMySQL() {
        Connection conn = null;

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

}
