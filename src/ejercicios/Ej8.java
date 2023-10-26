package ejercicios;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import DB.ConnectionDB;
import DB.FunctionsSQL;

public class Ej8 {

    public Ej8() throws SQLException {

		// Seleccionar la base de datos "mi_nueva_base_de_datos"
		FunctionsSQL.seleccionarBaseDeDatos("RobertDB");

		String sqlProductos = "DROP TABLE IF EXISTS productos; " +
                "CREATE TABLE productos (" +
                "  codigo INT PRIMARY KEY," +
                "  nombre VARCHAR(100) NOT NULL," +
                "  precio INT NOT NULL" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;" +
                "INSERT INTO productos (codigo, nombre, precio) VALUES (1, 'Producto A', 10), (2, 'Producto B', 15),(3, 'Producto C', 20), (4, 'Producto D', 25), (5, 'Producto E', 30);";
		
		String sqlCajeros = "DROP TABLE IF EXISTS cajeros; " +
                "CREATE TABLE cajeros (" +
                "  codigo INT PRIMARY KEY," +
                "  nom_apels VARCHAR(255) NOT NULL" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;" +
                "INSERT INTO cajeros (codigo, nom_apels) VALUES " +
                "(101, 'Juan Pérez'), " +
                "(102, 'María López'), " +
                "(103, 'Pedro Ramírez'), " +
                "(104, 'Luisa Martínez'), " +
                "(105, 'Ana Sánchez');";
		
		String sqlMaquinasRegistradoras = "DROP TABLE IF EXISTS maquinas_registradoras; " +
                "CREATE TABLE maquinas_registradoras (" +
                "  codigo INT PRIMARY KEY," +
                "  piso INT NOT NULL" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;" +
                "INSERT INTO maquinas_registradoras (codigo, piso) VALUES " +
                "(501, 1), " +
                "(502, 1), " +
                "(503, 2), " +
                "(504, 2), " +
                "(505, 3);";

		
		String sqlVenta = "DROP TABLE IF EXISTS venta; " +
                "CREATE TABLE venta (" +
                "  cajero INT REFERENCES cajeros," +
                "  maquina INT REFERENCES maquinas_registradoras," +
                "  producto INT REFERENCES productos," +
                "  PRIMARY KEY (cajero , maquina , producto)" + 
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;" +
                "INSERT INTO venta (cajero, maquina, producto) VALUES " +
                "(101, 501, 1)," +
                "(102, 502, 2)," +
                "(103, 503, 3)," +
                "(104, 504, 4)," +
                "(105, 505, 5);";
		
		//Ejecutar la sentencia SQL para crear las tablas y hacer las inserciones
		FunctionsSQL.ejecutarSQL(sqlProductos);
		FunctionsSQL.ejecutarSQL(sqlCajeros);
		FunctionsSQL.ejecutarSQL(sqlMaquinasRegistradoras);
		FunctionsSQL.ejecutarSQL(sqlVenta);
		
		// Mostrar todos los datos de la tabla "almacenes"
		List<String> columnas = new ArrayList<>();
		columnas.add("*"); // Esto seleccionará todas las columnas
		FunctionsSQL.mostrarDatos("productos", columnas);
		FunctionsSQL.mostrarDatos("cajeros", columnas);
		FunctionsSQL.mostrarDatos("maquinas_registradoras", columnas);
		FunctionsSQL.mostrarDatos("venta", columnas);


		// Cerrar la conexión después de todas las operaciones
		ConnectionDB.closeConnection();
    }
}