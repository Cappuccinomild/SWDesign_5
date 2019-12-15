import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Professor extends User{
	
	public Professor() throws SQLException {
		super();
	}

	private String ProfessorID = "";
	private ArrayList<Course> lecture = new ArrayList<>();
	
	public String getProfessorID() {
		return ProfessorID;
	}

	public void setProfessorID(String professorID) {
		ProfessorID = professorID;
	}

	public ArrayList<Course> getLecture() {
		for(Course course : lecture)
			course.getCourse();
		
		return lecture;
	}

	public void setLecture() {
		String sql = "select distinct * from classhour, course where conum = cnumber and course.pnum = '"+this.ProfessorID+"'";
		ResultSet rs;
		
		try {
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				this.lecture.add(new Course(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
			}
			rs.close();
		} catch (SQLException ex2) {
			System.err.println("sql error = " + ex2.getMessage());
			System.exit(1);
		}
		
		
	}
	
	public void logIn(String ID, String pwd) {
		
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
		}
		else {
			System.exit(1);
		}

	}
	
	
}
