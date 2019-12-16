package swdesign;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProfessorUI {
	
	Professor professor;
	boolean valid;
	
	public ProfessorUI(Professor professor, String ID, String pwd) {
		this.professor = professor;
		valid = this.professor.logIn(ID, pwd);
		this.professor.setLecture();
		this.professor.setExam();
	}
	public void ProfessorMenu()
	{
		System.out.println("1.���� ���� ��ȸ");
		System.out.println("2.���� ����");
		System.out.println("3.�������� ����");
	}
	
	public ArrayList<String> ProfessorSelect(int command) throws SQLException
	{
		
		switch(command) {
		case 1: System.out.println("�������� �����ϴ� �����Դϴ�.");
			return professor.getLecture();
		
		case 2: System.out.println("���� ���� ȣ��");
			return professor.getExam();
		
		case 3: System.out.println("�������� ���� ȣ��");
			return professor.getETime();
		
		}
		return null;
	}
	

}
