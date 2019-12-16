package swdesign;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

class ExamManagement {
	private static final String URL = "jdbc:oracle:thin:@155.230.36.61:1521:orcl";
	private static final String USER_KNU = "s2017111978";
	private static final String USER_PASSWD = "2017111978";
	private Connection conn = null; // Connection object
	private Statement stmt = null; // Statement object
	private String sql = ""; // an SQL statement
	private ResultSet res;
	private Scanner scanner = new Scanner(System.in);

	ExamManagement() {

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
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void InsertExam() {
		String cnum;
		String classname = null;
		String date;
		String stime, ftime;
		String floor, classroom = null;
		int exam_id = 0;
		// 해당 교수의 수업 목록 보여줌
		sql = "select Cnumber, Cname, Course_room, Cstime, Cftime, Hnumber from Course, classhour where Pnum = 1 and Conum = Cnumber";
		try {
			res = stmt.executeQuery(sql);
			System.out.println("Course_Number      Course_Name    Course_room      Start     End       Hours");
			while (res.next()) {
				System.out.println(res.getString("Cnumber") + " " + res.getString("Cname") + " "
						+ res.getString("Course_room") + " " + res.getString("Cstime") + " " + res.getString("Cftime")
						+ " " + res.getString("Hnumber"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("수업 목록 찾는데 에러남");
		}

		// 어떤 수업 할 건지 입력받기
		System.out.print("수업 과목코드를 입력하세요");
		cnum = scanner.next();

		sql = "select Cname from course where Cnumber = '" + cnum + "'";
		try {
			res = stmt.executeQuery(sql);
			classname = res.getString("Cname");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		System.out.print("요일을 선택하세요: MON TUE WED THU FRI SAT SUN");
		date = scanner.next();

		// 해당 날짜 시간목록 보여줌
		sql = "select Cnum, Exam_room, Estime, Eftime, Eday from exam where Eday = '" + date + "'";
		try {
			res = stmt.executeQuery(sql);
			System.out.println("Course_Number   Exam_room   Start   End   Day");
			while (res.next()) {
				System.out.println(res.getString("Cnumber") + res.getString("exam_room") + res.getString("cstime")
						+ res.getString("cftime") + res.getString("eday"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("강의실을 입력하세요(층 강의실)");
		floor = scanner.next();
		classroom = scanner.next();

		System.out.print("시작 시간을 입력허세요(hh:mm)");
		stime = scanner.next();
		stime = stime + ":00";
		System.out.print("마침 시간을 입력하세요(hh:mm)");
		ftime = scanner.next();
		ftime = ftime + ":00";

		sql = "select count(Enumber) from exam";
		try {
			res = stmt.executeQuery(sql);
			exam_id = res.getInt(1);
			exam_id++;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("시험 아이디 찾는데 에러남");
		}

		sql = "INSERT INTO EXAM VALUES (" + exam_id + ", " + cnum + ", '" + floor + " " + classroom + "' , TO_CHAR('"
				+ stime + "', 'HH24:MI:SS'), TO_CHAR('" + ftime + "', 'HH24:MI:SS'), '" + date + "', 6)";
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("해당 시간에는 시험을 칠 수 없습니다.");
		}

		System.out.println(classname + " (" + cnum + ")" + "의 시험시간이 " + date + " " + stime + "~" + ftime + " " + classroom + "으로 설정되었습니다.");

	}

}