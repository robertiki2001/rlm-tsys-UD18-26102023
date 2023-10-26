package ejercicios;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import DB.ConnectionDB;
import DB.FunctionsSQL;

public class Ej9 {

    public Ej9() throws SQLException {

		// Seleccionar la base de datos "mi_nueva_base_de_datos"
		FunctionsSQL.seleccionarBaseDeDatos("RobertDB");

		String sqlFacultad = "DROP TABLE IF EXISTS facultad; " +
                "CREATE TABLE facultad (" +
                "  codigo INT AUTO_INCREMENT PRIMARY KEY," +
                "  nombre VARCHAR(100) NOT NULL" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;" +
                "INSERT INTO facultad (nombre) VALUES "
                + "('Facultad de Ciencias'), "
                + "('Facultad de Ingenieria'),"
                + "('Facultad de Medicina'), "
                + "('Facultad de Artes'), "
                + "('Facultad de Derecho');";
		
		String sqlInvestigador = "DROP TABLE IF EXISTS investigador; " +
                "CREATE TABLE investigador (" +
                "  dni VARCHAR(8) PRIMARY KEY," +
                "  nombre_apellidos VARCHAR(255) NOT NULL," +
                "  facultad INT NOT NULL," +
                "  FOREIGN KEY (facultad) REFERENCES facultad(codigo)," +
                "  Odni_profesor CHAR(9) NOT NULL REFERENCES profesores ON DELETE CASCADE ON UPDATE CASCADE" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;" +
                "INSERT INTO investigador (dni, nombre_apellidos, facultad, Odni_profesor) VALUES " +
                "('12345678', 'Juan Perez', 1, 'P12345678'), " +
                "('87654321', 'Maria Lopez', 2, 'P87654321'), " +
                "('23456789', 'Pedro Ramirez', 3, 'P23456789'), " +
                "('98765432', 'Luisa Martinez', 4, 'P98765432'), " +
                "('34567890', 'Ana Sanchez', 5, 'P34567890');";
		
		String sqlEquipo = "DROP TABLE IF EXISTS equipo; " +
                "CREATE TABLE equipo (" +
                "  num_serie CHAR(4) PRIMARY KEY," +
                "  nombre VARCHAR(100) NOT NULL," +
                "  facultad INT NOT NULL," +
                "  FOREIGN KEY (facultad) REFERENCES facultad(codigo)," +
                "  dni_profesor CHAR(9) NOT NULL REFERENCES profesores ON DELETE CASCADE ON UPDATE CASCADE" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;" +
                "INSERT INTO equipo (num_serie, nombre, facultad, dni_profesor) VALUES " +
                "('E101', 'Equipo 1', 1, 'P12345678'), " +
                "('E102', 'Equipo 2', 2, 'P87654321'), " +
                "('E103', 'Equipo 3', 3, 'P23456789'), " +
                "('E104', 'Equipo 4', 4, 'P98765432'), " +
                "('E105', 'Equipo 5', 5, 'P34567890');";

		
		String sqlReserva = "DROP TABLE IF EXISTS reserva; " +
                "CREATE TABLE reserva (" +
                "  dni_investigador VARCHAR(8) NOT NULL," +
                "  num_equipo CHAR(4) NOT NULL," +
                "  comienzo DATETIME NOT NULL," +
                "  fin DATETIME NOT NULL," + 
                "  PRIMARY KEY (dni_investigador, num_equipo)," + 
                "  FOREIGN KEY (dni_investigador) REFERENCES investigador(dni)," + 
                "  FOREIGN KEY (num_equipo) REFERENCES equipo(num_serie)," + 
                "  dni_profesor CHAR(9) NOT NULL REFERENCES profesores ON DELETE CASCADE ON UPDATE CASCADE" + 
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;" +
                "INSERT INTO reserva (dni_investigador, num_equipo, comienzo, fin, dni_profesor) VALUES " +
                "('12345678', 'E101', '2023-10-25 09:00:00', '2023-10-25 12:00:00', 'P12345678')," +
                " ('87654321', 'E102', '2023-10-25 10:00:00', '2023-11-25 13:00:00', 'P87654321')," +
                "('23456789', 'E103', '2023-10-25 11:00:00', '2023-11-25 14:00:00', 'P23456789')," +
                "('98765432', 'E104', '2023-10-25 12:00:00', '2023-11-25 15:00:00', 'P98765432')," +
                "('34567890', 'E105', '2023-10-25 13:00:00', '2023-11-25 16:00:00', 'P34567890');";
		
		//Ejecutar la sentencia SQL para crear las tablas y hacer las inserciones
		FunctionsSQL.ejecutarSQL(sqlFacultad);
		FunctionsSQL.ejecutarSQL(sqlInvestigador);
		FunctionsSQL.ejecutarSQL(sqlEquipo);
		FunctionsSQL.ejecutarSQL(sqlReserva);
		
		// Mostrar todos los datos de la tabla "almacenes"
		List<String> columnas = new ArrayList<>();
		columnas.add("*"); // Esto seleccionará todas las columnas
		FunctionsSQL.mostrarDatos("facultad", columnas);
		FunctionsSQL.mostrarDatos("investigador", columnas);
		FunctionsSQL.mostrarDatos("equipo", columnas);
		FunctionsSQL.mostrarDatos("reserva", columnas);


		// Cerrar la conexión después de todas las operaciones
		ConnectionDB.closeConnection();
    }
}