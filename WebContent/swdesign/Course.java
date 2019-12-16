package swdesign;
public class Course {

	String lecture_code, lecture_name, 
		   		   professor_info, lecture_stime, lecture_ftime, lecture_date,
		   		   lecture_room, building_number;
	
	public Course(String lecture_code, String lecture_name, String professor_info, String lecture_stime, String lecture_ftime, String lecture_date,String lecture_room) {
		setLectureCode(lecture_code);
		setLectureName(lecture_name);
		setProfessorInfo(professor_info);
		setLectureTime(lecture_stime, lecture_ftime);
		setLectureDay(lecture_date);
		setLectureRoom(lecture_room);
	}
	
	private void setLectureDay(String lecture_date) {
		this.lecture_date = lecture_date;
	}

	public void setLectureCode(String lecture_code) {
		this.lecture_code = lecture_code;
	}
	
	public void setLectureName(String lecture_name) {
		this.lecture_name = lecture_name;
	}
	
	public void setProfessorInfo(String professor_info) {
		this.professor_info = professor_info;
	}
	
	public void setLectureTime(String lecture_stime, String lecture_ftime) {
		this.lecture_stime = lecture_stime; 
		this.lecture_ftime = lecture_ftime;
	}
	
	
	public void setLectureRoom(String lecture_room) {
		this.lecture_room = lecture_room;
	}
	
	public String getCourse() {
		return "<tr style=' border : 1px solid #808080; text-align: center; padding: 10px; ' id=course_text>"+
				"<td>"+lecture_name + "</td>" +
				"<td>"+professor_info + "</td>" +
				"<td>"+lecture_room + "</td>"+
				"<td>"+lecture_stime + "</td>" +
				"<td>"+lecture_ftime + "</td>" +
				"<td>"+lecture_date + "</td>"+
				"<td><div id = '"+lecture_code+"' name = 'manage' style = 'display:none'><form action = '../Exam Manage/Manage.jsp' method = 'post'>" + 
					"<input type = 'hidden' id = 'course_name' name = 'course_name' value = '"+lecture_code+"'></input>" + 
					"<input type = 'hidden' id = 'course_number' name = 'course_number' value = '"+lecture_name+"'></input>" +
					"<input type = 'submit'></input>\r\n" + 
				"                            </form></div></td></tr>";
	}
	
}
