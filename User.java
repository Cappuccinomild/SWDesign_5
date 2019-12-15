import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class User {
	public static final String URL = "jdbc:oracle:thin:@localhost:1600:xe";
	public static final String USER_KNU = "system";
	public static final String USER_PASSWD = "oracle";
	public static final String TABLE_NAME = "TEST";

	public static Scanner scanner;
	private static String ID = "";
	private static String pwd = "";
	private static String auth = "";
	Connection conn = null; // Connection object
	Statement stmt = null; // Statement object
	
	public User() throws SQLException {
		try {
			// Load a JDBC driver for Oracle DBMS
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Get a Connection object
			System.out.println("Success!");
		} catch (ClassNotFoundException e) {
			System.err.println("error = " + e.getMessage());
			System.exit(1);
		}

		// Make a connection
		try {
			conn = DriverManager.getConnection(URL, USER_KNU, USER_PASSWD);
		} catch (SQLException ex) {
			System.err.println("Cannot get a connection: " + ex.getMessage());
			System.exit(1);
		}
		try {
			conn.setAutoCommit(false); // auto-commit disabled
			// Create a statement object
			stmt = conn.createStatement();

		} catch (SQLException ex2) {
			System.err.println("sql error = " + ex2.getMessage());
			System.exit(1);
		}
	}
	
}
