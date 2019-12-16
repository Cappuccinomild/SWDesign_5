package swdesign;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

public class ManageUI {
	public static final String URL = "jdbc:oracle:thin:@localhost:1600:xe";
	public static final String USER_KNU = "system";
	public static final String USER_PASSWD = "oracle";
	public static final String TABLE_NAME = "TEST";

	Connection conn = null; // Connection object
	Statement stmt = null; // Statement object
	ResultSet rs;
	ExamManagement EM;
	
	ArrayList<String> location = new ArrayList<>();

	public ManageUI() throws SQLException {
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
	
	public void setExam(String cnum, String class_room, String stime, String ftime, String date) {
		
		EM = new ExamManagement();
		String[] day = { "", "SUN", "MON", "THU", "WEN", "TUR", "FRI", "SAT" };
		date = day[Integer.parseInt(date)];
		EM.setExam(cnum, class_room, stime, ftime, date);
	}
	
	public boolean submit() throws SQLException {
		System.out.println("aa");
		if(EM.checkExam()) {
			EM.InsertExam();
			return true;
		}
		return false;
	}

	public ArrayList<String> getLocation() throws SQLException {
		ArrayList<String> result = new ArrayList<>();

		String sql = "select * from location";

		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		while (rs.next()) {
			result.add("<option value='" + rs.getString(1) + "'>" + rs.getString(2) + "</option>");
			location.add(rs.getString(1));
		}
		rs.close();

		return result;
	}

	public ArrayList<String> getClassRoom() throws SQLException {
		ArrayList<String> result = new ArrayList<>();

		for (String Lnum : location) {
			String sql = "select class_room from classroom where lnum = " + Lnum;

			try {
				rs = stmt.executeQuery(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			result.add("<div id = " + Lnum + " style = 'display:none'><select id='i" + Lnum + "'>");
			while (rs.next()) {
				result.add("<option value='" + rs.getString(1) + "'>" + rs.getString(1) + "</option>");
			}
			rs.close();
			result.add("</select></div>");
		}

		return result;
	}

	public ArrayList<String> getETime(String Lname, String date) throws SQLException {

		ArrayList<String> result = new ArrayList<>();
		ArrayList<String> classrooms = new ArrayList<>();

		String[] day = { "", "SUN", "MON", "THU", "WEN", "TUR", "FRI", "SAT" };

		String sql = "SELECT Class_room FROM CLASSROOM WHERE Lnum = '" + Lname + "'";

		rs = stmt.executeQuery(sql);
		int num = 0;
		while (rs.next()) {
			classrooms.add(rs.getString(1));
			num++;
		}

		result.add("<p>" + day[Integer.parseInt(date)] + "</p>");
		result.add(
				"<table style='width:100%;background-color: #ffffff; margin-left: auto; margin-right: auto; border-radius: 5px;border-top: solid; border-bottom:solid;'><tr><td>강의실</td>");

		for (int i = 8; i < 23; i++)
			result.add("<td colspan = '12'>" + Integer.toString(i) + "</td>");

		result.add("</tr>");

		for (int i = 0; i < num; i++) {
			int[] timetable = new int[180];
			Arrays.fill(timetable, 0);
			int size = 180;
			result.add("<tr><td>" + classrooms.get(i) + "</td>");
			sql = "SELECT Cname, Exam_room, Esdate, Efdate FROM COURSE, EXAM WHERE Cnumber=Cnum AND Eday = '"
					+ day[Integer.parseInt(date)] + "' AND Exam_room = '" + classrooms.get(i) + "'";
			
			rs = stmt.executeQuery(sql);
			while (rs.next()) {

				String[] Stime = rs.getString(3).substring(11).split(":");
				String[] Etime = rs.getString(4).substring(11).split(":");
				
				
				int j = (Integer.parseInt(Stime[0]) * 60 + Integer.parseInt(Stime[1]) - 480) / 5;
				timetable[j] = (Integer.parseInt(Etime[0]) * 60 + Integer.parseInt(Etime[1]) - 480) / 5 - j;

			}
			for (int j = 0; j < size; j++) {

				if (timetable[j] > 1) {

					result.add("<td colspan = '" + Integer.toString(timetable[j])
							+ "' style = 'background : gray;'></td>");
					size -= timetable[j];
					size += 1;
					for (int k = 0; k < timetable[j]; k++) // 이 자리부터 시간표만큼 테이블을 만들지 않음
						timetable[j + k] = 1;
				}

				else if (timetable[j] == 1) {

				}

				else
					result.add("<td></td>");
			}
			result.add("</tr><tr>");
		}
		
		result.add("</tr></table>");

		return result;
	}

}
