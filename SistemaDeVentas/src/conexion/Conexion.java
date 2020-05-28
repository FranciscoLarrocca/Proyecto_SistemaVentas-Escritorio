package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	private static Connection con;
	private static final String URL = "jdbc:mysql://localhost:3306/bd_ventas?useSSL=false&serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PASS = "root";

	public static Connection conectar() throws SQLException{
		try {
			con = DriverManager.getConnection(URL, USER, PASS);
		} catch (SQLException e) {
			System.out.println("Ocurrio un error en la conexion: " + e.getMessage());
		}
		return con;
	}
}
