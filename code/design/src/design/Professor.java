package design;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Professor extends User{
	
	public Professor() throws SQLException {
		super();
	}

	public String getProfessorID() {
		return ID;
	}

	public void setProfessorID(String professorID) {
		this.ID = professorID;
	}

	
	public boolean logIn(String ID, String pwd) {
		
		String sql = "select count(*) from professor where pnumber = '"+ ID +"' and ppwd = '"+ pwd+"'";  
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
			setProfessorID(ID);
			return true;
		}
		else {
			return false;
		}

	}
	
	public void setLecture() {
		String sql = "select cnumber, cname, pnum, csdate, cfdate, cday, course_room from classhour, course where conum = cnumber and course.pnum = '"+this.ID+"'";
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
		String sql = "select distinct cnumber, cname, pnum, esdate, efdate, eday, exam_room from classhour, course, exam where conum = cnumber and cnumber = cnum and course.pnum = '"+this.ID+"'";
		ResultSet rs;
		
		try {
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				this.exam.add(new Exam(rs.getString(1), rs.getString(2), rs.getString(3),"","","","", rs.getString(4).substring(11), rs.getString(5).substring(11), rs.getString(6), rs.getString(7)));
			}
			rs.close();
		} catch (SQLException ex2) {
			System.err.println("sql error = " + ex2.getMessage());
			System.exit(1);
		}
	}
}
