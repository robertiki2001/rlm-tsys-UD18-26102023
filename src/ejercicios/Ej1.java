package ejercicios;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import DB.ConnectionDB;
import DB.FunctionsSQL;

public class Ej1 {

    public Ej1() throws SQLException {
        // Crear una base de datos 
		FunctionsSQL.crearBaseDeDatos("RobertDB");

		// Seleccionar la base de datos
		FunctionsSQL.seleccionarBaseDeDatos("RobertDB");

		
		String sqlFabricantes = "DROP TABLE IF EXISTS fabricantes; " +
	             "CREATE TABLE fabricantes (" +
	             "  CODIGO int NOT NULL," +
	             "  NOMBRE varchar(255) NOT NULL," +
	             "  PRIMARY KEY (CODIGO)" +
	             ") ENGINE=InnoDB DEFAULT CHARSET=utf8; " +
	             "INSERT INTO fabricantes VALUES " +
	             "(1,'Sony'),(2,'Creative Labs'),(3,'Hewlett-Packard'),(4,'Iomega'),(5,'Fujitsu'),(6,'Winchester');";
		
		String sqlArticulos = "DROP TABLE IF EXISTS articulos; " +
	             "CREATE TABLE articulos (" +
	             "  CODIGO int NOT NULL," +
	             "  NOMBRE varchar(255) NOT NULL," +
	             "  PRECIO decimal(10,0) NOT NULL," +
	             "  FABRICANTE int NOT NULL," +
	             "  PRIMARY KEY (CODIGO)," +
	             "  KEY FABRICANTE (FABRICANTE)," +
	             "  CONSTRAINT articulos_ibfk_1 FOREIGN KEY (FABRICANTE) REFERENCES fabricantes (CODIGO)" +
	             ") ENGINE=InnoDB DEFAULT CHARSET=utf8; " +
	             "INSERT INTO articulos VALUES " +
	             "(1,'Hard drive',240,5),(2,'Memory',120,6),(3,'ZIP drive',150,4),(4,'Floppy disk',5,6),(5,'Monitor',240,1),(6,'DVD drive',180,2),(7,'CD drive',90,2),(8,'Printer',270,3),(9,'Toner cartridge',66,3),(10,'DVD burner',180,2);";


		//Ejecutar la sentencia SQL para crear las tablas y hacer las inserciones
		FunctionsSQL.ejecutarSQL(sqlFabricantes);
		FunctionsSQL.ejecutarSQL(sqlArticulos);
		
		// Mostrar todos los datos de la tabla
		List<String> columnas = new ArrayList<>();
		columnas.add("*"); // Esto seleccionará todas las columnas
		FunctionsSQL.mostrarDatos("fabricantes", columnas);
		FunctionsSQL.mostrarDatos("articulos", columnas);

		// Cerrar la conexión después de todas las operaciones
		//ConnectionDB.closeConnection();
    }
}
