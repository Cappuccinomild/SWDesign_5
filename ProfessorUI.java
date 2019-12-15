import java.sql.SQLException;
import java.util.Scanner;

public class ProfessorUI {
	
	Professor professor;
	
	public ProfessorUI(Professor professor, String ID, String pwd) {
		this.professor = professor;
		this.professor.logIn(ID, pwd);
		this.professor.setLecture();
	}
	public void ProfessorMenu()
	{
		System.out.println("1.강의 과목 조회");
		System.out.println("2.시험 관리");
		System.out.println("3.공지사항 수정");
	}
	
	public void ProfessorSelect(int command) throws SQLException
	{
		
		switch(command) {
		case 1: System.out.println("교수님이 강의하는 과목입니다.");
			professor.getLecture();
		break;
		case 2: System.out.println("시험 관리 호출");
		break;
		case 3: System.out.println("공지사항 수정 호출");
		break;
		}
	}
	

}

