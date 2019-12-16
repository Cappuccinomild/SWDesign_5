package design;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Student extends User{
	
	public Student() throws SQLException {
		super();
	}
	
	public String getStduentID() {
		return ID;
	}

	public void setStudentID(String StudentID) {
		this.ID = StudentID;
	}
	
	public boolean logIn(String ID, String pwd) {
		
		String sql = "select count(*) from student where snumber = '"+ ID +"' and spwd = '"+ pwd+"'";  
		ResultSet rs;
		int cnt = 0;
		
		try {
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				cnt = rs.getInt(1);
			}
			rs.close();
		} catch (SQLException ex2) {
			System.err.println("sql error login = " + ex2.getMessage());
			System.exit(1);
		}
		
		if (cnt == 1) {
			setStudentID(ID);
			return true;
		}
		else {
			return false;
		}

	}
	
	public void setLecture() {
		String sql = "select cnumber, cname, pnum, csdate, cfdate, cday, course_room from classhour, course, take_class where conum = cnumber and cno = cnumber and snum = '"+this.ID+"'";
		ResultSet rs;
		
		try {
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				this.lecture.add(new Course(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4).substring(11), rs.getString(5).substring(11), rs.getString(6), rs.getString(7)));
			}
			rs.close();
		} catch (SQLException ex2) {
			System.err.println("sql lecture error = " + ex2.getMessage());
			System.exit(1);
		}
		
	}
	
	public void setExam() {
		String sql = "select distinct cnumber, cname, pnum, esdate, efdate, eday, exam_room from take_class, classhour, course, exam where conum = cnumber and cno = cnumber and cnum = cnumber and snum = '"+this.ID+"'";
		ResultSet rs;
		
		try {
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				this.exam.add(new Exam(rs.getString(1), rs.getString(2), rs.getString(3),"","","","", rs.getString(4).substring(11), rs.getString(5).substring(11), rs.getString(6), rs.getString(7)));
			}
			rs.close();
		} catch (SQLException ex2) {
			System.err.println("sql exam error = " + ex2.getMessage());
			System.exit(1);
		}
	}
}