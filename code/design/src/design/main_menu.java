package design;

import java.sql.SQLException;
import java.util.ArrayList;

public class main_menu {
	private String user = "";
	ProfessorUI professorui;
	StudentUI studentui;

	public boolean Professor_session(String ID, String pwd) throws SQLException {

		this.professorui = new ProfessorUI(new Professor(), ID, pwd, new ExamManagement());
		if (!professorui.valid)
			return false;
		this.user = "P";

		// professorui.ProfessorSelect(Integer.parseInt(scanner.nextLine()));

		return true;
	}

	public boolean Student_session(String ID, String pwd) throws SQLException {

		this.studentui = new StudentUI(new Student(), ID, pwd);

		if (!studentui.valid)
			return false;
		this.user = "S";

		// studentui.StudentSelect(Integer.parseInt(scanner.nextLine()));

		return true;
	}

	public void Call_menu() {
		if (this.user.equals("P")) {
			professorui.ProfessorMenu();
		} else if (this.user.equals("S")) {
			studentui.StudentMenu();
		}
	}

	public void GetData(int num) throws SQLException {
		if (this.user.equals("P")) {
			professorui.ProfessorSelect(num);
			return;
		} else if (this.user.equals("S")) {
			studentui.StudentSelect(num);
			return;
		}
		return;
	}
}
