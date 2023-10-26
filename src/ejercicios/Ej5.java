package ejercicios;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import DB.ConnectionDB;
import DB.FunctionsSQL;

public class Ej5 {

    public Ej5() throws SQLException {

		// Seleccionar la base de datos
		FunctionsSQL.seleccionarBaseDeDatos("RobertDB");

		String sqlDespachos = "DROP TABLE IF EXISTS despachos; " +
                "CREATE TABLE despachos (" +
                "  NUMERO INT AUTO_INCREMENT PRIMARY KEY," +
                "  CAPACIDAD INT NOT NULL" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;" +
                "INSERT INTO despachos (CAPACIDAD) VALUES (10), (8), (12), (15), (20);";
		
		String sqlDirector = "DROP TABLE IF EXISTS director; " +
                "CREATE TABLE director (" +
                "  DNI VARCHAR(8) PRIMARY KEY," +
                "  NOMBRE_APELLIDOS VARCHAR(255) NOT NULL," +
                "  DESPACHO INT NOT NULL," +
                "  DNI_JEFE VARCHAR(8) NULL," +
                "  FOREIGN KEY (DESPACHO) REFERENCES despachos(NUMERO)," +
                "  FOREIGN KEY (DNI_JEFE) REFERENCES director(DNI) ON DELETE CASCADE ON UPDATE CASCADE" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;" +
                "INSERT INTO director (DNI, NOMBRE_APELLIDOS, DESPACHO) VALUES " +
                "('12345678', 'Juan Perez', 1)," +
                "('87654321', 'Maria Lopez', 2)," +
                "('23456789', 'Pedro Ramirez', 1)," +
                "('98765432', 'Luisa Martinez', 3)," +
                "('34567890', 'Ana Sanchez', 4);";

		//Ejecutar la sentencia SQL para crear las tablas y hacer las inserciones
		FunctionsSQL.ejecutarSQL(sqlDespachos);
		FunctionsSQL.ejecutarSQL(sqlDirector);
		
		// Mostrar todos los datos de la tabla "almacenes"
		List<String> columnas = new ArrayList<>();
		columnas.add("*"); // Esto seleccionará todas las columnas
		FunctionsSQL.mostrarDatos("despachos", columnas);
		FunctionsSQL.mostrarDatos("director", columnas);

		// Cerrar la conexión después de todas las operaciones
		ConnectionDB.closeConnection();
    }
}