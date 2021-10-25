package retos.evalart.utils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Clase para establecer una conexión con la base de datos
 * @autor James Tovar Rodriguez
 */
public class ConnectionDB {

    private static Connection connection;
    private String driver;
    private String url;
    private String user;
    private String pass;
    private String database;

    private ConnectionDB() throws Exception {
        driver = FileConfig.getPropertie("database.driver","com.mysql.cj.jdbc.Driver");
        url = FileConfig.getPropertie("database.url", "jdbc:mysql://localhost:3306");
        user = FileConfig.getPropertie("database.user", "root");
        pass = FileConfig.getPropertie("database.pass", "");
        database = FileConfig.getPropertie("database.name", "evalart_reto");

        Class.forName(driver);
        connection = DriverManager.getConnection(url+"/"+database+"?useSSL=false",user,pass);
    };

    /**
     * Este método retorna la instancia de conexion.
     * @return Una conexion a la base de datos
     */
    public static Connection getConnection() throws Exception {
        if (connection == null) {
            new ConnectionDB();
        }
        return connection;
    }

}
