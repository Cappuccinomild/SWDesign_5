package design;

import java.sql.SQLException;
import java.util.ArrayList;

public class StudentUI {

	Student student;
	boolean valid;

	public StudentUI(Student student, String ID, String pwd) {
		this.student = student;
		valid = this.student.logIn(ID, pwd);
		this.student.setLecture();
		this.student.setExam();
	}

	public void StudentMenu() {
		System.out.println("1.���� ���� ��ȸ");
		System.out.println("2.���� ��ȸ");
		System.out.println("3.�ð�ǥ��ȸ");
		System.out.println("4.�α׾ƿ�");

	}

	public void StudentSelect(int command) throws SQLException {
		switch (command) {
		case 1:
			System.out.println("�������� ����");
			student.getLecture();
			break;
		case 2:
			System.out.println("���� ��ȸ");
			student.getExam();
			break;
		case 3:
			System.out.println("�ð�ǥ ��ȸ");
			student.getETime();
			break;

		}
		return;
	}
}