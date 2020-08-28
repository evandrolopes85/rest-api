package br.edu.devmedia.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDConfig {
	
	private static Connection connection;
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException{
		Class.forName( "com.mysql.jdbc.Driver" );
		connection = DriverManager.getConnection( "jdbc:mysql://localhost:3306/notas_db", "root", "Labr@d0r" );
		
		return connection;
	}
}
