package Inge2.voto_virtual;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.util.sqlcontainer.connection.JDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.connection.SimpleJDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.query.FreeformQuery;
import com.vaadin.data.util.sqlcontainer.query.TableQuery;

/**
 * Clase para realizar conexion con la base de datos PostgreSQL. Recibe usuario
 * y contraseNa en el constructor.
 * 
 * @author r1k4rd0
 */
public class Conexion {

	private static String nombreConexion;
	private static JDBCConnectionPool pool;

	public Conexion(String usuario, String password) {
		nombreConexion = "jdbc:postgresql://anon01.tk/edecisiones?user="
				+ usuario + "&password=" + password;
		//nombreConexion = "jdbc:postgresql://127.0.0.1:5432/voto_virtual?user=postgres&password=postgres";
		try {
			pool = new SimpleJDBCConnectionPool("org.postgresql.Driver",
					"jdbc:postgresql://anon01.tk:5432/edecisiones", usuario,
					password, 2, 5);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Ejecuta un comando SQL (INSERT o UPDATE) donde el parametro es un arreglo de bytes
	 * 
	 * @param sql
	 *            Consulta en lenguaje SQL.
	 * @return exito true si se ejecuto el comando, false sino.
	 */
	public void ejecutarSeguro(String query, byte[] pkBytes) {
		try {	
			//System.out.println("INICIO GUARDADO SEGURO");
			Connection conn = DriverManager.getConnection(nombreConexion);
			PreparedStatement pstat;

			pstat = conn.prepareStatement(query);
			pstat.setBytes(1, pkBytes);
			pstat.execute();
			//System.out.println("GUARDO");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Ejecuta un comando SQL (INSERT o UPDATE) y retorna si se tuvo exito o no.
	 * 
	 * @param sql
	 *            Consulta en lenguaje SQL.
	 * @return exito true si se ejecuto el comando, false sino.
	 */
	public boolean ejecutar(String sql) {
		boolean exito = false;
		try {
			// Se abre la conexion utilizando el driver de postgres
			Class.forName("org.postgresql.Driver");
			Connection conexion = DriverManager.getConnection(nombreConexion);
			Statement comando = conexion.createStatement();
			int n_resultado = comando.executeUpdate(sql);
			// Se ejecuta la instruccion SQL
			System.out.println("Se modificaron " + n_resultado + " filas");
			exito = true;
			// Hay que cerrar la conexion
			comando.close();
			conexion.close();
		} catch (Exception e) {
			// Es muy importante el try-catch para atrapar los posibles errores
			// que pueden ocurrir en las distintas consultas y comandos SQL
			System.out.println(e.getMessage());
		}
		// Se retorna si la ejecucion tuvo exito o no
		return exito;
	}

	/**
	 * Realiza una consulta SQL y retorna el resultado en un ResultSet.
	 * 
	 * @param sql
	 *            Consulta en lenguaje SQL.
	 * @return resultado Objeto ResultSet con los datos o null si no hubo
	 *         resultado.
	 */
	public ResultSet consultar(String sql) {
		ResultSet resultado = null;
		try {
			// Se abre la conexion utilizando el driver de postgres
			Class.forName("org.postgresql.Driver");
			Connection conexion = DriverManager.getConnection(nombreConexion);
			Statement comando = conexion.createStatement();
			resultado = comando.executeQuery(sql);
			if (resultado != null) {
				// Si resultado != null la consulta genero resultado
				System.out.println("Consulta ejecutada correctamente");
			} else {
				// Sino entonces no se ejecuto la consulta o no genero resultado
				System.out
						.println("No se ejecuto la consulta o no genero resultado");
			}
			// Se cierra la conexion
			//comando.close();
			//conexion.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		// Se retorna el ResultSet
		//Se retorna el ResultSet
        try {
			resultado.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	/*
	public void finDeConexion(){
		comando.close();
		conexion.close();		
	}*/
	
	/**
	 * Realiza una consulta SQL y retorna el resultado en un doble ArrayList de
	 * Strings.
	 * 
	 * @param sql
	 *            Consulta en lenguaje SQL.
	 * @return resultado ArrayList con los datos o null si no hubo resultado.
	 */
	public ArrayList<ArrayList<String>> consultarArray(String sql) {
		ArrayList<ArrayList<String>> resultado = new ArrayList<ArrayList<String>>();
		try {
			// Se abre la conexion utilizando el driver de postgres
			Class.forName("org.postgresql.Driver");
			Connection conexion = DriverManager.getConnection(nombreConexion);
			Statement comando = conexion.createStatement();
			ResultSet result = comando.executeQuery(sql);

			if (result != null) {
				ResultSetMetaData metaData = result.getMetaData();
				int columnas = metaData.getColumnCount();
				// Iteracion sobre las filas
				while (result.next()) {
					ArrayList<String> temp = new ArrayList<String>();
					for (int i = 1; i <= columnas; i++) {
						// Se saca la informacion de cada columna
						temp.add(result.getString(i));
					}
					resultado.add(temp);
				}
				result.close();
			} else {
				// Sino entonces no se ejecuto la consulta o no genero resultado
				System.out
						.println("No se ejecuto la consulta o no genero resultado");
			}
			// Se cierra la conexion
			comando.close();
			conexion.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		// Se retorna ArrayList o null (exito o no)
		return resultado;
	}

	/**
	 * Recupera una tabla de la base de datos en un contenedor SQL.
	 * 
	 * @param nombreTabla
	 *            nombre de la tabla de la base de datos a consultar.
	 * @return container resultado de la consulta o null si no genera ninguno.
	 */
	public SQLContainer consultarTabla(String nombreTabla) {
		SQLContainer container = null;
		try {
			TableQuery tablaBD = new TableQuery(nombreTabla, pool);
			container = new SQLContainer(tablaBD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return container;
	}

	/**
	 * Realiza una consulta SQL y retorna el resultado en un SQLContainer.
	 * 
	 * @param sql
	 *            Consulta en lenguaje SQL.
	 * @return container Objeto SQLContainer con los datos o null si no hubo.
	 */
	public SQLContainer consulta(String sql) {
		SQLContainer container = null;
		FreeformQuery consulta = new FreeformQuery(sql, pool);
		try {
			container = new SQLContainer(consulta);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return container;
	}

	public void cerrar() {
		pool.destroy();
	}

	/**
	 * Ejecuta una consulta de usuario + contraseña.
	 * Retorna un ResultSet con el usuario en cuestión o nulo
	 * si la combinación usuario + contraseña es inválida.
	 * @param query 
	 * @param user correo electrónico del usuario
	 * @param pass contraseña del usuario
	 * @return
	 */
	public ResultSet ejecutarLogin(String user, String pass) {
		ResultSet resultado = null;
		String sql = "SELECT * FROM persona WHERE correo = ? AND clave = ?;";
		try {
			Connection conn = DriverManager.getConnection(nombreConexion);
			PreparedStatement pstat;
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, user);
			pstat.setString(2, pass);
			if(pstat.execute()){
				resultado = pstat.getResultSet();
			}
			//Aqui hace falta un close?
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
	
	/**
	 * Inserta un nuevo padrón y retorna el ID asociado.
	 * @return id del padrón recién creado.
	 */
	public int insertarPadron(String ruta){
		String sql = "INSERT INTO padron(ruta) VALUES(?);";
		int id = -1;
		try {
			Connection conn = DriverManager.getConnection(nombreConexion);
			PreparedStatement pstat;
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, ruta);
			if(pstat.executeUpdate()>0){
				sql = "SELECT count(1) FROM padron;";
				Statement comando = conn.createStatement();
				ResultSet resultado = comando.executeQuery(sql);
				id = resultado.getInt(1);
			}
			//Aqui hace falta un close?
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
}