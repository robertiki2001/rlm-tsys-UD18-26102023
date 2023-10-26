package ejercicios;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import DB.ConnectionDB;
import DB.FunctionsSQL;

public class Ej4 {

    public Ej4() throws SQLException {

		// Seleccionar la base de datos
		FunctionsSQL.seleccionarBaseDeDatos("RobertDB");

		String sqlPeliculas = "DROP TABLE IF EXISTS peliculas; " +
	             "CREATE TABLE peliculas (" +
	             "  CODIGO int NOT NULL," +
	             "  NOMBRE varchar(255) NOT NULL," +
	             "  CALIFICACIONEDAD varchar(255) DEFAULT NULL," +
	             "  PRIMARY KEY (CODIGO)" +
	             ") ENGINE=InnoDB DEFAULT CHARSET=utf8; " +
	             "INSERT INTO peliculas VALUES " +
	             "(1,'Citizen Kane','PG')," +
	             "(2,'Singin'' in the Rain','G')," +
	             "(3,'The Wizard of Oz','G')," +
	             "(4,'The Quiet Man',NULL)," +
	             "(5,'North by Northwest',NULL)," +
	             "(6,'The Last Tango in Paris','NC-17')," +
	             "(7,'Some Like it Hot','PG-13')," +
	             "(8,'A Night at the Opera',NULL)," +
	             "(9,'Citizen King','G');";

		String sqlSalas = "DROP TABLE IF EXISTS salas; " +
	             "CREATE TABLE salas (" +
	             "  CODIGO int NOT NULL," +
	             "  NOMBRE varchar(255) NOT NULL," +
	             "  PELICULA int DEFAULT NULL," +
	             "  PRIMARY KEY (CODIGO)," +
	             "  KEY PELICULA (PELICULA)," +
	             "  CONSTRAINT salas_ibfk_1 FOREIGN KEY (PELICULA) REFERENCES peliculas (CODIGO)" +
	             ") ENGINE=InnoDB DEFAULT CHARSET=utf8; " +
	             "INSERT INTO salas VALUES " +
	             "(1,'Odeon',5)," +
	             "(2,'Imperial',1)," +
	             "(3,'Majestic',NULL)," +
	             "(4,'Royale',6)," +
	             "(5,'Paraiso',3)," +
	             "(6,'Nickelodeon',NULL);";

		//Ejecutar la sentencia SQL para crear las tablas y hacer las inserciones
		FunctionsSQL.ejecutarSQL(sqlPeliculas);
		FunctionsSQL.ejecutarSQL(sqlSalas);
		
		// Mostrar todos los datos de la tabla "almacenes"
		List<String> columnas = new ArrayList<>();
		columnas.add("*"); // Esto seleccionará todas las columnas
		FunctionsSQL.mostrarDatos("peliculas", columnas);
		FunctionsSQL.mostrarDatos("salas", columnas);

		// Cerrar la conexión después de todas las operaciones
		ConnectionDB.closeConnection();
    }
}
