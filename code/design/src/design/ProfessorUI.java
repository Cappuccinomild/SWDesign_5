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
		System.out.println("1.강의 과목 조회");
		System.out.println("2.시험 관리");
		System.out.println("3.공지사항 수정");
		System.out.println("4.시험등록");
		System.out.println("5.로그아웃");
	}

	public void ProfessorSelect(int command) throws SQLException {

		switch (command) {
		case 1:
			System.out.println("교수님이 강의하는 과목입니다.");
			professor.getLecture();
			break;

		case 2:
			System.out.println("시험 관리 호출");
			professor.getExam();
			break;

		case 3:
			System.out.println("공지사항 수정 호출");
			professor.getETime();
			break;

		case 4:
			System.out.println("시험 등록 호출");
			em.InsertExam();
			this.professor.setExam();
			break;

		}
		return;
	}

}
