package tb_kasir;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

import java.sql.Connection;

public class databaseHandler {
	static Statement stat;
	static ResultSet rs;
	Connection conn;

	public Connection getConnect(){
		String url = "jdbc:mysql://localhost:3306/kasir";
		String username = "root";
		String password = "";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,username,password);
			stat = conn.createStatement();
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "koneksi gagal "+e.getMessage());
		}
		return conn;
		
	}
}
