import java.util.Scanner;

public class ProfessorUI {

	public void ProfessorMenu()
	{
		System.out.println("1.���� ���� ��ȸ");
		System.out.println("2.���� ����");
		System.out.println("3.�������� ����");
	}
	
	public void ProfessorSelect(int command)
	{
		switch(command) {
		case 1: System.out.println("�������� �����ϴ� �����Դϴ�.");
		break;
		case 2: System.out.println("���� ���� ȣ��");
		break;
		case 3: System.out.println("�������� ���� ȣ��");
		break;
		}
	}
}

