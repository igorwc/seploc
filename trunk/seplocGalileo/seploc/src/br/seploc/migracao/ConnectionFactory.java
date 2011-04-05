package br.seploc.migracao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public Connection getConnection(String BD, String user, String passwd) {
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost/"+BD,
					user, passwd);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public Connection getConnection(String host, String BD, String user, String passwd) {
		try {
			return DriverManager.getConnection("jdbc:mysql://"+host+"/"+BD,
					user, passwd);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
//	public Connection getConnectionSeploc() {
//		try {
//			return DriverManager.getConnection("jdbc:mysql://localhost/fj21",
//					"root", "");
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//		}
//	}
}
