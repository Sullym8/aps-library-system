

package com.aps.LibrarySystem;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class SqliteConnector {
	Connection connection = null;
	static String DBDir = new String(System.getProperty("user.home")+"\\Documents\\LMS\\LibraryDB.db"); 
	
	public static Connection dbConnection() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection connection = DriverManager.getConnection("jdbc:sqlite:"+DBDir);
			return connection;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "A critical error occured", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "A critical error occured", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		
		
	}

}








