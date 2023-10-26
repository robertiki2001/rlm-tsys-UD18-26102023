package ejercicios;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import DB.ConnectionDB;
import DB.FunctionsSQL;

public class Ej7 {

    public Ej7() throws SQLException {

		// Seleccionar la base de datos "mi_nueva_base_de_datos"
		FunctionsSQL.seleccionarBaseDeDatos("RobertDB");

		String sqlCientificos = "DROP TABLE IF EXISTS cientificos; " +
                "CREATE TABLE cientificos (" +
                "  DNI VARCHAR(8) PRIMARY KEY," +
                "  nomApels NVARCHAR(255)" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;" +
                "INSERT INTO cientificos (DNI, nomApels) VALUES ('12345678', 'Juan Perez'), ('87654321', 'Maria Lopez'),('23456789', 'Pedro Ramirez'), ('98765432', 'Luisa Martinez'), ('34567890', 'Ana Sanchez');";
		String sqlProyecto = "DROP TABLE IF EXISTS proyecto; " +
                "CREATE TABLE proyecto (" +
                "  id CHAR(4) PRIMARY KEY," +
                "  nombre NVARCHAR(255) NOT NULL," +
                "  horas INT" + 
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;" +
                "INSERT INTO proyecto (id, nombre, horas) VALUES " +
                "('P1', 'Proyecto A', 100), " +
                "('P2', 'Proyecto B', 150), " +
                "('P3', 'Proyecto C', 200), " +
                "('P4', 'Proyecto D', 120), " +
                "('P5', 'Proyecto E', 180);";

		
		String sqlAsignado_a = "DROP TABLE IF EXISTS asignado_a; " +
                "CREATE TABLE asignado_a (" +
                "  cientifico VARCHAR(8)," +
                "  proyecto CHAR(4)," +
                "  PRIMARY KEY (cientifico, proyecto)," +
                "  FOREIGN KEY (cientifico) REFERENCES cientificos (DNI) ON UPDATE CASCADE ON DELETE CASCADE," + 
                "  FOREIGN KEY (proyecto) REFERENCES proyecto (id) ON UPDATE CASCADE ON DELETE CASCADE" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;" +
                "INSERT INTO asignado_a (cientifico, proyecto) VALUES " +
                "('12345678', 'P1')," +
                "('87654321', 'P2')," +
                "('23456789', 'P3')," +
                "('98765432', 'P4')," +
                "('34567890', 'P5');";
		
		//Ejecutar la sentencia SQL para crear las tablas y hacer las inserciones
		FunctionsSQL.ejecutarSQL(sqlCientificos);
		FunctionsSQL.ejecutarSQL(sqlProyecto);
		FunctionsSQL.ejecutarSQL(sqlAsignado_a);
		
		// Mostrar todos los datos de la tabla "almacenes"
		List<String> columnas = new ArrayList<>();
		columnas.add("*"); // Esto seleccionará todas las columnas
		FunctionsSQL.mostrarDatos("cientificos", columnas);
		FunctionsSQL.mostrarDatos("proyecto", columnas);
		FunctionsSQL.mostrarDatos("asignado_a", columnas);

		// Cerrar la conexión después de todas las operaciones
		ConnectionDB.closeConnection();
    }
}