import java.util.Scanner;

public class StudentUI {

	public void showMenu()
	{
		int select;
		Scanner keyboard = new Scanner(System.in);
		
		do 
		{
			System.out.println("�޴��� �����ϼ���: 1. ");
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
		System.out.println("1.���� ���� ��ȸ");
		System.out.println("2.���� ��ȸ");
		
	}
	
	public void StudentSelect(int command) throws SQLException
	{
		
		switch(command) {
		case 1: System.out.println("�������� ����");
			professor.getLecture();
		break;
		case 2: System.out.println("���� ��ȸ");
		break;
		
		}
	}
	

}


