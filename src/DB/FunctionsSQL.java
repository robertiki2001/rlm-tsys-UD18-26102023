package DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class FunctionsSQL {
	

	 //Metodo para crear una nueva base de datos con el nombre especificado en el parámetro nombreBaseDatos
	
	public static void crearBaseDeDatos(String nombreBaseDatos) {
        Connection conexion = null;

        // Obtener la conexión utilizando la clase ConnectionDB
        conexion = ConnectionDB.getConnection();

        if (conexion != null) {
            try {
                Statement s = conexion.createStatement();
                String query = "CREATE DATABASE IF NOT EXISTS " + nombreBaseDatos;

                s.executeUpdate(query);
                System.out.println("Base de datos " + nombreBaseDatos + " creada.");
            } catch (SQLException e) {
                System.out.println("Error al crear la base de datos: " + e.getMessage());
            } 
        }
    }
	

	// Metodo para seleccionar una base de datos existente para su uso en la conexión actual.
	public static void seleccionarBaseDeDatos(String nombreBaseDatos) {
        Connection conexion = null;

        // Obtener la conexión utilizando la clase ConnectionDB
        conexion = ConnectionDB.getConnection();

        if (conexion != null) {
            try {
                Statement s = conexion.createStatement();
                String query = "USE " + nombreBaseDatos;

                s.executeUpdate(query);
                System.out.println("Base de datos " + nombreBaseDatos + " seleccionada.");
            } catch (SQLException e) {
                System.out.println("Error al seleccionar la base de datos: " + e.getMessage());
            }
        }
    }
	
	//Metodo para ejecutar codigo SQL que puede incluir múltiples sentencias SQL separadas por punto y coma (;).
	
	public static void ejecutarSQL(String sql) {
	    Connection conexion = null;

	    // Obtener la conexión utilizando la clase ConnectionDB
	    conexion = ConnectionDB.getConnection();

	    if (conexion != null) {
	        try {
	            Statement s = conexion.createStatement();
	            
	            // Dividir las declaraciones SQL en sentencias separadas
	            String[] sentenciasSQL = sql.split(";");
	            
	            for (String sentencia : sentenciasSQL) {
	                if (!sentencia.trim().isEmpty()) {
	                    s.execute(sentencia);
	                }
	            }

	            System.out.println("Código SQL ejecutado con éxito.");
	        } catch (SQLException e) {
	            System.out.println("Error al ejecutar el código SQL: " + e.getMessage());
	        }
	    }
	}
	
	 //Metodo para printar por consola todos los datos de la tabla seleccionada
	
	public static void mostrarDatos(String nombreTabla, List<String> columnas) {
	    Connection conexion = null;

	    // Obtener la conexión utilizando la clase ConnectionDB
	    conexion = ConnectionDB.getConnection();

	    if (conexion != null) {
	        try {
	            // Consulta SQL para verificar si la tabla existe
	            String consultaTabla = "SHOW TABLES LIKE '" + nombreTabla + "'";

	            Statement s = conexion.createStatement();
	            ResultSet rs = s.executeQuery(consultaTabla);

	            if (rs.next()) {
	                // La tabla existe, ahora puedes mostrar los datos
	                Statement sMostrar = conexion.createStatement();

	                String columnasSQL;
	                if (columnas != null && !columnas.isEmpty()) {
	                    // Si se proporcionan columnas específicas, las usamos
	                    columnasSQL = String.join(", ", columnas);
	                } else {
	                    // Si no se proporcionan columnas específicas, seleccionamos todas
	                    columnasSQL = "*";
	                }

	                String sql = "SELECT " + columnasSQL + " FROM " + nombreTabla;
	                ResultSet rsMostrar = sMostrar.executeQuery(sql);
	                System.out.println("\n");
	                System.out.println("-----------------MOSTRANDO DATOS DE LA TABLA " + nombreTabla + "----------------\n");

	                while (rsMostrar.next()) {
	                    if ("*".equals(columnasSQL)) {
	                        // Si se seleccionaron todas las columnas, obtenemos la lista de nombres de columnas
	                        ResultSetMetaData metaData = rsMostrar.getMetaData();
	                        int columnCount = metaData.getColumnCount();
	                        for (int i = 1; i <= columnCount; i++) {
	                            String columnName = metaData.getColumnName(i);
	                            String columnValue = rsMostrar.getString(i);
	                            System.out.println(columnName + ": " + columnValue);
	                        }
	                        System.out.println();
	                    } else {
	                    	
	                        for (String columna : columnas) {
	                            String valor = rsMostrar.getString(columna);
	                            System.out.println(columna + ": " + valor);
	                        }
	                        System.out.println();
	                    }
	                }
	            } else {
	                System.out.println("La tabla " + nombreTabla + " no existe.");
	            }
	        } catch (SQLException e) {
	            System.out.println("Error al mostrar datos: " + e.getMessage());
	        }
	    }
	}





}
