package br.com.smartmall;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private static final String URL = "jdbc:mysql://localhost/smartmall_db";
	private static final String USER = "root";
	private static final String SENHA = "Sm@rtm@11";
	
	//Metodo que abre a conex�o
	public Connection getConnection() {
	     try {
	         return DriverManager.getConnection(URL, USER, SENHA);
	     } catch (SQLException e) {
	         throw new RuntimeException(e);
	     }
	 }
	
	//Metodo que fecha uma conexao
	public static void closeConnection(Connection con){
		try{
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

}
