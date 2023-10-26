package ejercicios;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import DB.ConnectionDB;
import DB.FunctionsSQL;

public class Ej3 {

    public Ej3() throws SQLException {

		// Seleccionar la base de datos 
		FunctionsSQL.seleccionarBaseDeDatos("RobertDB");

		String sqlAlmacenes = "DROP TABLE IF EXISTS almacenes; " +
	             "CREATE TABLE almacenes (" +
	             "  CODIGO int NOT NULL," +
	             "  LUGAR varchar(255) NOT NULL," +
	             "  CAPACIDAD int NOT NULL," +
	             "  PRIMARY KEY (CODIGO)" +
	             ") ENGINE=InnoDB DEFAULT CHARSET=utf8; " +
	             "INSERT INTO almacenes VALUES " +
	             "(1,'Valencia',3)," +
	             "(2,'Barcelona',4)," +
	             "(3,'Bilbao',7)," +
	             "(4,'Los Angeles',2)," +
	             "(5,'San Francisco',8);";
		
		String sqlCajas = "DROP TABLE IF EXISTS cajas; " +
	             "CREATE TABLE cajas (" +
	             "  NUMREFERENCIA varchar(255) NOT NULL," +
	             "  CONTENIDO varchar(255) NOT NULL," +
	             "  VALOR double NOT NULL," +
	             "  ALMACEN int NOT NULL," +
	             "  PRIMARY KEY (NUMREFERENCIA)," +
	             "  KEY ALMACEN (ALMACEN)," +
	             "  CONSTRAINT cajas_ibfk_1 FOREIGN KEY (ALMACEN) REFERENCES almacenes (CODIGO)" +
	             ") ENGINE=InnoDB DEFAULT CHARSET=utf8; " +
	             "INSERT INTO cajas VALUES " +
	             "('0MN7','Rocks',180,3)," +
	             "('4H8P','Rocks',250,1)," +
	             "('4RT3','Scissors',190,4)," +
	             "('7G3H','Rocks',200,1)," +
	             "('8JN6','Papers',75,1)," +
	             "('8Y6U','Papers',50,3)," +
	             "('9J6F','Papers',175,2)," +
	             "('LL08','Rocks',140,4)," +
	             "('P0H6','Scissors',125,1)," +
	             "('P2T6','Scissors',150,2)," +
	             "('TU55','Papers',90,5);";

		//Ejecutar la sentencia SQL para crear las tablas y hacer las inserciones
		FunctionsSQL.ejecutarSQL(sqlAlmacenes);
		FunctionsSQL.ejecutarSQL(sqlCajas);
		
		// Mostrar todos los datos de la tabla "almacenes"
		List<String> columnas = new ArrayList<>();
		columnas.add("*"); // Esto seleccionará todas las columnas
		FunctionsSQL.mostrarDatos("almacenes", columnas);
		FunctionsSQL.mostrarDatos("cajas", columnas);

		// Cerrar la conexión después de todas las operaciones
		ConnectionDB.closeConnection();
    }
}
