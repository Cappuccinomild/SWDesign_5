public class Course {

	private String lecture_code, lecture_name, 
		   		   professor_info, lecture_time,
		   		   lecture_room;
	private int building_number;
	
	public Course(String lecture_code, String lecture_name, String professor_info, String lecture_time, String lecture_room, int building_number) {
		setLectureCode(lecture_code);
		setLectureName(lecture_name);
		setProfessorInfo(professor_info);
		setLectureTime(lecture_time);
		setLectureRoom(lecture_room);
		setBuildingNumber(building_number);
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
	
	public void setLectureTime(String lecture_time) {
		this.lecture_time = lecture_time;
	}
	
	public void setLectureRoom(String lecture_room) {
		this.lecture_room = lecture_room;
	}
	
	public void setBuildingNumber(int building_number) {
		this.building_number = building_number;
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
