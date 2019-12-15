import java.util.Scanner;

public class StudentUI {

	public void showMenu()
	{
		int select;
		Scanner keyboard = new Scanner(System.in);
		
		do 
		{
			System.out.println("메뉴를 선택하세요: 1. ");
			select = keyboard.nextInt();
			
			if(select == 1)
			{
				
			}
			else if(select == 2)
			{
				
			}
			else if(select == 3)
			{
				
			}
		} while(select != 0);
	}
	

}

import java.sql.SQLException;
import java.util.Scanner;

public class ProfessorUI {
	
	Student student;
	
	public StudentUI(Professor professor, String ID, String pwd) {
		this.professor = professor;
		this.professor.logIn(ID, pwd);
		this.professor.setLecture();
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
			professor.getLecture();
		break;
		case 2: System.out.println("시험 조회");
		break;
		
		}
	}
	

}


