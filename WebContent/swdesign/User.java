package swdesign;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;


public class User {
	public static final String URL = "jdbc:oracle:thin:@localhost:1600:xe";
	public static final String USER_KNU = "system";
	public static final String USER_PASSWD = "oracle";
	public static final String TABLE_NAME = "TEST";

	public static String ID = "";
	private static String pwd = "";
	
	public ArrayList<Course> lecture = new ArrayList<>();
	public ArrayList<Exam> exam = new ArrayList<>();
	public String[] day = {"", "SUN", "MON", "THU", "WEN", "TUR", "FRI", "SAT"};
	
	public String[][] timetable = new String[5000][5000]; 
	
	Connection conn = null; // Connection object
	Statement stmt = null; // Statement object
	
	
	public User() throws SQLException {
		try {
			// Load a JDBC driver for Oracle DBMS
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Get a Connection object
			System.out.println("Success!");
		} catch (ClassNotFoundException e) {
			System.err.println("error = " + e.getMessage());
			System.exit(1);
		}

		// Make a connection
		try {
			conn = DriverManager.getConnection(URL, USER_KNU, USER_PASSWD);
		} catch (SQLException ex) {
			System.err.println("Cannot get a connection: " + ex.getMessage());
			System.exit(1);
		}
		try {
			conn.setAutoCommit(false); // auto-commit disabled
			// Create a statement object
			stmt = conn.createStatement();

		} catch (SQLException ex2) {
			System.err.println("sql error = " + ex2.getMessage());
			System.exit(1);
		}
	}

	public ArrayList<String> getLecture() {
		ArrayList<String> result = new ArrayList<>();
		for(Course course : this.lecture)
			result.add(course.getCourse());
		
		return result;
	}
	
	public ArrayList<String> getExam() {
		ArrayList<String> result = new ArrayList<>();
		for(Exam exam : this.exam)
			result.add(exam.getExam());
		
		return result;
	}
	
	public ArrayList<String> getETime(){
		ArrayList<String> result = new ArrayList<>();
		
		for(Exam exam : this.exam) {
			String[] Stime = exam.stime.split(":");
			String[] Etime = exam.ftime.split(":");

	        //시작시간을 8시로해서 5분단위로 쪼갠 index
	        int j = (int)((Integer.parseInt(Stime[0]) * 60 + Integer.parseInt(Stime[1]) - 480)/5);
	        int index = -1;
	        for (int i = 0; i < day.length; i++) {
	            if (exam.exam_date.equals(day[i])) {
	                index = i;
	                break;
	            }            
	        }
	        timetable[j][index] = exam.lecture_name+"<br>"+ exam.exam_room + "<br> :" + Integer.toString(((Integer.parseInt(Etime[0]) * 60 + Integer.parseInt(Etime[1]) - 480)/5) - j);
	        
		}
		
		for(int i = 0 ; i < 180 ; i ++){
			if(i % 12 == 0){
		        result.add("<tr style = ' border-top : 1px solid #444444;'>");
		        int time = (int)i*5 / 60 + 8;

		        result.add("<td rowspan = '12' style = 'vertical-align: top; border : 1px solid #444444'>"+Integer.toString(time)+"시</td>");
	      	}
			else
				result.add("<tr>");
			
		      for(int j = 1 ; j < 8 ; j ++){

		          if(timetable[i][j] == null)
		            result.add("<td></td>");
		    	  
		    	  else if( timetable[i][j].equals("1"));//테이블을 만들지 않음
		    	  
		    	  else{//셀 병합 길이 데이터가 존재할때

		            //랜덤색상 만들기
		            Random random = new Random();
		          	
		            int mixnum1 = 0x51;
		            int mixnum2 = 0xC1;

		            switch(random.nextInt(3)){
		              case 0:
		                mixnum1 = 0x4D;
		                mixnum2 = 0x8A;
		                break;
		              case 1:
		                mixnum1 = 0x72;
		                mixnum2 = 0xC3;
		                break;
		              case 2:
		                mixnum1 = 0x6C;
		                mixnum2 = 0xC1;
		                break;

		            }

		            int[] mix = {random.nextInt(mixnum2 - mixnum1) + mixnum1, mixnum1, mixnum2};
		            int temp;
		            for(int x = 0 ; x < 10; x++) {
		            	temp = mix[x%mix.length];
		            	int rand = random.nextInt(mix.length);
		            	mix[x%mix.length] = mix[rand];
		            	mix[rand] = temp;
		            }

		            String color = "#" + Integer.toHexString(mix[0]) + Integer.toHexString(mix[1]) + Integer.toHexString(mix[2]);

		            String[] code = timetable[i][j].split(":");
		            System.out.println(code[1]);
		            for(int k = 0; k < Integer.parseInt(code[1]) ; k ++) // 이 자리부터 시간표만큼 테이블을 만들지 않음
		              timetable[i + k][j] = "1";
		            result.add("<td style = 'background : "+color+";' rowspan = '"+code[1]+"'> "+code[0] +"</td>");
		          }


		        }
			result.add("</tr>");
		}
		
		return result;
	}
	
}
