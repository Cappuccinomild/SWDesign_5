package design;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class User {
	public static final String URL = "jdbc:oracle:thin:@localhost:1600:xe";
	public static final String USER_KNU = "system";
	public static final String USER_PASSWD = "oracle";
	public static final String TABLE_NAME = "TEST";

	public static String ID = "";
	private static String pwd = "";

	public ArrayList<Course> lecture = new ArrayList<>();
	public ArrayList<Exam> exam = new ArrayList<>();
	public String[] day = { "", "SUN", "MON", "THU", "WEN", "TUR", "FRI", "SAT" };

	public String[][] timetable = new String[5000][5000];

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

	public void getLecture() {
		for (Course course : this.lecture)
			System.out.println(course.getCourse());
	}

	public void getExam() {
		for (Exam exam : this.exam)
			System.out.println(exam.getExam());
	}

	public ArrayList<String> getETime() {
		ArrayList<String> result = new ArrayList<>();

		for (Exam exam : this.exam) {
			String[] Stime = exam.stime.split(":");
			String[] Etime = exam.ftime.split(":");

			// 시작시간을 8시로해서 5분단위로 쪼갠 index
			int j = (int) ((Integer.parseInt(Stime[0]) * 60 + Integer.parseInt(Stime[1]) - 480) / 30);
			int index = -1;
			for (int i = 0; i < day.length; i++) {
				if (exam.exam_date.equals(day[i])) {
					index = i;
					break;
				}
			}
			timetable[j][index] = exam.lecture_name + "\n" + exam.exam_room + "\n :"
					+ Integer.toString(((Integer.parseInt(Etime[0]) * 60 + Integer.parseInt(Etime[1]) - 480) / 30) - j);

		}

		for (int i = 0; i < 30; i++) {
			if (i % 3 == 0) {
				int time = (int) i / 2 + 8;

				System.out.println(Integer.toString(time) + "시");
			} else
				System.out.println();

			for (int j = 1; j < 8; j++) {

				if (timetable[i][j] == null)
					;
				else if (timetable[i][j].equals("1"))
					System.out.println(" ");
					// 테이블을 만들지 않음

				else {// 셀 병합 길이 데이터가 존재할때

					String[] code = timetable[i][j].split(":");
					System.out.println(code[1]);
					for (int k = 0; k < Integer.parseInt(code[1]); k++) // 이 자리부터 시간표만큼 테이블을 만들지 않음
						timetable[i + k][j] = "1";
					System.out.println(code[0]);
				}

			}
		}

		return result;
	}

}
