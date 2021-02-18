package proekt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
static Connection conn=null;
	
	static Connection getConnection() {
		try {
			Class.forName("org.h2.Driver");
			conn=DriverManager.getConnection(" jdbc:h2:tcp://localhost/D:\\Desktop\\OOP\\informatika-DB2", "sa", "1234");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}

}