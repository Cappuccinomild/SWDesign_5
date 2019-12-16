package design;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProfessorUI {

	Professor professor;
	ExamManagement em;
	boolean valid;

	public ProfessorUI(Professor professor, String ID, String pwd, ExamManagement em) {
		this.professor = professor;
		this.em = em;
		valid = this.professor.logIn(ID, pwd);
		this.professor.setLecture();
		this.professor.setExam();
	}

	public void ProfessorMenu() {
		System.out.println("1.���� ���� ��ȸ");
		System.out.println("2.���� ����");
		System.out.println("3.�������� ����");
		System.out.println("4.������");
		System.out.println("5.�α׾ƿ�");
	}

	public void ProfessorSelect(int command) throws SQLException {

		switch (command) {
		case 1:
			System.out.println("�������� �����ϴ� �����Դϴ�.");
			professor.getLecture();
			break;

		case 2:
			System.out.println("���� ���� ȣ��");
			professor.getExam();
			break;

		case 3:
			System.out.println("�������� ���� ȣ��");
			professor.getETime();
			break;

		case 4:
			System.out.println("���� ��� ȣ��");
			em.InsertExam();
			this.professor.setExam();
			break;

		}
		return;
	}

}
