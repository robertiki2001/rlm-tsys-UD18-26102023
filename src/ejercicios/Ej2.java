package ejercicios;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import DB.ConnectionDB;
import DB.FunctionsSQL;

public class Ej2 {

    public Ej2() throws SQLException {

		// Seleccionar la base de datos
		FunctionsSQL.seleccionarBaseDeDatos("RobertDB");

		String sqlDepartamentos = "DROP TABLE IF EXISTS departamentos; " +
	             "CREATE TABLE departamentos (" +
	             "  CODIGO int NOT NULL," +
	             "  NOMBRE varchar(255) NOT NULL," +
	             "  PRESUPUESTO decimal(10,0) NOT NULL," +
	             "  PRIMARY KEY (CODIGO)" +
	             ") ENGINE=InnoDB DEFAULT CHARSET=utf8; " +
	             "INSERT INTO departamentos VALUES " +
	             "(14,'IT',65000),(37,'Accounting',15000),(59,'Human Resources',240000),(77,'Research',55000);";

		
		String sqlEmpleados = "DROP TABLE IF EXISTS empleados; " +
	             "CREATE TABLE empleados (" +
	             "  DNI int NOT NULL," +
	             "  NOMBRE varchar(255) NOT NULL," +
	             "  APELLIDOS varchar(255) NOT NULL," +
	             "  DEPARTAMENTO int NOT NULL," +
	             "  PRIMARY KEY (DNI)," +
	             "  KEY DEPARTAMENTO (DEPARTAMENTO)," +
	             "  CONSTRAINT empleados_ibfk_1 FOREIGN KEY (DEPARTAMENTO) REFERENCES departamentos (CODIGO)" +
	             ") ENGINE=InnoDB DEFAULT CHARSET=utf8; " +
	             "INSERT INTO empleados VALUES " +
	             "(123234877,'Michael','Rogers',14)," +
	             "(152934485,'Anand','Manikutty',14)," +
	             "(222364883,'Carol','Smith',37)," +
	             "(326587417,'Joe','Stevens',37)," +
	             "(332154719,'Mary-Anne','Foster',14)," +
	             "(332569843,'George','O''Donnell',77)," + 
	             "(546523478,'John','Doe',59)," +
	             "(631231482,'David','Smith',77)," +
	             "(654873219,'Zacary','Efron',59)," +
	             "(745685214,'Eric','Goldsmith',59)," +
	             "(845657233,'Luis','López',14)," +
	             "(845657245,'Elizabeth','Doe',14)," +
	             "(845657246,'Kumar','Swamy',14)," +
	             "(845657266,'Jose','Pérez',77);";


		//Ejecutar la sentencia SQL para crear las tablas y hacer las inserciones
		FunctionsSQL.ejecutarSQL(sqlDepartamentos);
		FunctionsSQL.ejecutarSQL(sqlEmpleados);
		
		// Mostrar todos los datos de la tabla "almacenes"
		List<String> columnas = new ArrayList<>();
		columnas.add("*"); // Esto seleccionará todas las columnas
		FunctionsSQL.mostrarDatos("departamentos", columnas);
		FunctionsSQL.mostrarDatos("empleados", columnas);

		// Cerrar la conexión después de todas las operaciones
		ConnectionDB.closeConnection();
    }
}
