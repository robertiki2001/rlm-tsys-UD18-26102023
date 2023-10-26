package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

    private static Connection connection = null;

    private static final String DB_URL = "jdbc:mysql://localhost:33060";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "robert";
    
    //Metodo para establecer una conexion con la base de datos
    public static Connection getConnection() {
        if (connection != null) {//Verifica si ya existe una conexión establecida
            return connection;
        }

        try {//Si no existe una conexión previa, el método realiza lo siguiente
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Servidor conectado");
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("No se ha podido conectar con la base de datos");
            System.out.println(ex);
        }
        return connection;
    }

    public static void closeConnection() {//Este método se utiliza para cerrar una conexión previamente establecida con la base de datos.
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexión cerrada");
            } catch (SQLException ex) {
                System.out.println("Error al cerrar la conexión");
                System.out.println(ex);
            }
        }
    }

}
