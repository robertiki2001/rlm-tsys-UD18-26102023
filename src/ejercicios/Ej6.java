package ejercicios;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import DB.ConnectionDB;
import DB.FunctionsSQL;

public class Ej6 {

    public Ej6() throws SQLException {

		// Seleccionar la base de datos
		FunctionsSQL.seleccionarBaseDeDatos("RobertDB");

		String sqlPiezas = "DROP TABLE IF EXISTS piezas; " +
                "CREATE TABLE piezas (" +
                "  codigo INT AUTO_INCREMENT PRIMARY KEY," +
                "  nombre NVARCHAR(100) NOT NULL" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;" +
                "INSERT INTO piezas (nombre) VALUES ('Tornillo'), ('Arandela'),('Brida'), ('Tuerca'), ('Pasador');";
		

		String sqlProveedores = "DROP TABLE IF EXISTS proveedores; " +
                "CREATE TABLE proveedores (" +
                "  id CHAR(4) PRIMARY KEY," +
                "  nombre VARCHAR(100) NOT NULL" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;" +
                "INSERT INTO proveedores (id, nombre) VALUES ('P1', 'Proveedor 1'), ('P2', 'Proveedor 2'), ('P3', 'Proveedor 3'),('P4', 'Proveedor 4'), ('P5', 'Proveedor 5');";


		String sqlDirector = "DROP TABLE IF EXISTS suministra; " +
                "CREATE TABLE suministra (" +
                "  codigoPieza INT," +
                "  idProveedor CHAR(4)," +
                "  precio INT," +
                "  PRIMARY KEY (codigoPieza , idProveedor)," +
                "  FOREIGN KEY (codigoPieza) REFERENCES piezas (codigo) ON UPDATE CASCADE ON DELETE CASCADE," +
                "  FOREIGN KEY (idProveedor) REFERENCES proveedores (id) ON UPDATE CASCADE ON DELETE CASCADE" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;" +
                "INSERT INTO suministra (codigoPieza, idProveedor, precio) VALUES " +
                "(1, 'P1', 100)," +
                "(2, 'P2', 150)," +
                "(3, 'P3', 200)," +
                "(4, 'P4', 120)," +
                "(5, 'P5', 180);";



		//Ejecutar la sentencia SQL para crear las tablas y hacer las inserciones
		FunctionsSQL.ejecutarSQL(sqlPiezas);
		FunctionsSQL.ejecutarSQL(sqlProveedores);
		FunctionsSQL.ejecutarSQL(sqlDirector);
		
		// Mostrar todos los datos de la tabla "almacenes"
		List<String> columnas = new ArrayList<>();
		columnas.add("*"); // Esto seleccionará todas las columnas
		FunctionsSQL.mostrarDatos("piezas", columnas);
		FunctionsSQL.mostrarDatos("proveedores", columnas);
		FunctionsSQL.mostrarDatos("suministra", columnas);

		// Cerrar la conexión después de todas las operaciones
		ConnectionDB.closeConnection();
    }
}