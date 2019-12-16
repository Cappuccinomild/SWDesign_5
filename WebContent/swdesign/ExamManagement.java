package swdesign;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ExamManagement {
	public static final String URL = "jdbc:oracle:thin:@localhost:1600:xe";
	public static final String USER_KNU = "system";
	public static final String USER_PASSWD = "oracle";
	public static final String TABLE_NAME = "TEST";

	Connection conn = null; // Connection object
	Statement stmt = null; // Statement object
	ResultSet rs;

	Exam exam;

	public ExamManagement() {
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

	public void setExam(String cnum, String class_room, String stime, String ftime, String day) {
		exam = new Exam(cnum, "", "", "", "", "", "", stime, ftime, day, class_room);
	}
	
	public boolean checkExam() throws SQLException {
		int num = 0;
		// �ش� ���������� ��ġ�� �ٸ� ���������� ������ Ȯ���Ѵ�.
		String sql = "CREATE OR REPLACE VIEW EXAM_VIEW AS SELECT Enumber AS VEnumber, Pnum FROM EXAM, COURSE WHERE Cnum = "+exam.lecture_code+" AND Exam_room = '"+exam.exam_room+"' AND Eday = '"+exam.exam_date+"'";
		rs = stmt.executeQuery(sql);
		sql = "SELECT DISTINCT VEnumber, Pnum FROM EXAM JOIN EXAM_VIEW ON Enumber = VEnumber WHERE TO_DATE('"+exam.stime+"', 'HH24:MI:SS') BETWEEN Esdate AND Efdate OR TO_DATE('"+exam.ftime+"', 'HH24:MI:SS') BETWEEN Esdate AND Efdate";
		System.out.println(sql);
		rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
			rs.getString(1);
			num++;
		}

		if (num >= 1) { // ���������� ������ ��쿡�� ��û �޽����� ������ �ʴ´�.
		    return false;
		}
		else{
		  // �л��� �ٸ� ���������� ��ĥ ��� ��� �޽����� ������.
		  sql = "CREATE OR REPLACE VIEW TAKE_VIEW AS SELECT Snum AS VSnum FROM TAKE_CLASS WHERE Cno = '"+exam.lecture_code+"'";
		  rs = stmt.executeQuery(sql);
		  sql = "CREATE OR REPLACE VIEW EXAM_VIEW AS SELECT DISTINCT Enumber AS VEnumber FROM TAKE_CLASS, TAKE_VIEW, EXAM WHERE Snum = VSnum AND NOT Cno = '"+exam.lecture_code+"' AND Cno = Cnum AND Eday = '"+exam.exam_date+"'";
		  rs = stmt.executeQuery(sql);
		  
		  sql = "SELECT DISTINCT Enumber FROM EXAM_VIEW JOIN EXAM ON VEnumber = Enumber WHERE TO_DATE('"+exam.stime+"', 'HH24:MI:SS') BETWEEN Esdate AND Efdate OR TO_DATE('"+exam.ftime+"', 'HH24:MI:SS') BETWEEN Esdate AND Efdate";
		  rs = stmt.executeQuery(sql);
		  
		  num = 0;
		  while(rs.next()) {
				rs.getString(1);
				num++;
			}
		  
		  if (num >= 1) { // �ش� ������ ��� �л��� �ٸ� ���� ������ 1�� �̻� �����ϴ� ���
		      return false;
		  }
		}
		return true;
	}

	public void InsertExam() throws SQLException{
		String sql = "SELECT MAX(Enumber) FROM EXAM ";
		int num = 0;
		rs = stmt.executeQuery(sql);
		while(rs.next()) {
			num = Integer.parseInt(rs.getString(1));
		}
		
		if (num >= 1)
			sql = "INSERT INTO EXAM VALUES ("+Integer.toString(++num)+", "+exam.lecture_code+", '"+exam.exam_room+"', TO_DATE('"+exam.stime+"', 'HH24:MI:SS'), TO_DATE('"+exam.ftime+"', 'HH24:MI:SS'), '"+exam.exam_date+"')";
		else// ���ڰ� ���� ���
			sql = "INSERT INTO EXAM VALUES (1, "+exam.lecture_code+", '"+exam.exam_room+"', TO_DATE('"+exam.stime+"', 'HH24:MI:SS'), TO_DATE('"+exam.ftime+"', 'HH24:MI:SS'), '"+exam.exam_date+"')";
		
		stmt.executeUpdate(sql);
				  		
	}
}