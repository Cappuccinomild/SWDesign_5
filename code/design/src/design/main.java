package design;

import java.sql.*;
import java.util.*;

public class main {

	public static Scanner scanner;
	private static String ID = "";
	private static String pwd = "";

	public static void main(String[] args) throws SQLException {

		scanner = new Scanner(System.in);
		int command = 0;

		Scanner scanner = new Scanner(System.in);

		main_menu mm = new main_menu();

		boolean svalid = false;
		boolean pvalid = false;

		while (true) {

			// 로그인
			boolean login_flag = false;

			while (!login_flag) {

				System.out.println("LOGIN");
				System.out.println("로그인 : 1 \t종료 : 2");

				switch (command = Integer.parseInt(scanner.nextLine())) {

				// 로그인
				case 1:
					System.out.println("로그인");

					System.out.print("ID : ");
					ID = scanner.nextLine();

					System.out.print("PASSWORD : ");
					pwd = scanner.nextLine();

					pvalid = mm.Professor_session(ID, pwd);
					
					System.out.println(pvalid);
					svalid = mm.Student_session(ID, pwd);

					System.out.println(svalid);
					// 로그인 결과 확인
					if ((svalid && pvalid) || (!svalid && !pvalid)) {
						System.out.println("Unvalid ID");
						// command 입력을 다시 받는다
						command = 0;
					} else { // valid 할 경우 그대로 놔두면 로그인 while문 을 종료함
						System.out.println("로그인 성공");
						login_flag = true;
					}
					break;
				// 프로그램 종료
				case 2:
					System.exit(0);
					break;

				}
			}

			if (pvalid) {// 교수메뉴
				while (login_flag) {
					mm.Call_menu();
					command = scanner.nextInt();
					
					if(command == 5) {
						System.out.println("로그아웃합니다.");
						System.exit(0);
					}
					mm.GetData(command);
				}

			} else if (svalid) {// 학생메뉴
				while (login_flag) {
					mm.Call_menu();
					command = scanner.nextInt();
					
					if(command == 4) {
						System.out.println("로그아웃합니다.");
						System.exit(0);
					}
					mm.GetData(command);
				}
			}

		}

	}

}