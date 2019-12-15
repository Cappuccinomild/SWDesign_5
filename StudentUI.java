import java.sql.SQLException;

public class StudentUI {
	
	Student student;
	
	public StudentUI(Student student, String ID, String pwd) {
		this.student = student;
		this.student.logIn(ID, pwd);
		this.student.setLecture();
		this.student.setExam();
	}
	public void StudentMenu()
	{
		System.out.println("1.강의 과목 조회");
		System.out.println("2.시험 조회");
		
	}
	
	public void StudentSelect(int command) throws SQLException
	{
		switch(command) {
		case 1: System.out.println("수강중인 과목");
			student.getLecture();
		break;
		case 2: System.out.println("시험 조회");
			student.getExam();
		break;
		
		}
	}
}


