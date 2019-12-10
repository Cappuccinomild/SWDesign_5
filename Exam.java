public class Exam extends Course{
	private String place, time;
	
	public Exam(String lecture_code, String lecture_name, String professor_info, String lecture_time, String lecture_room, int building_number, String place, String time) {
		super(lecture_code, lecture_name, professor_info, lecture_time, lecture_room, building_number);
		setPlace(place);
		setTime(time);
	}
	
	public void setPlace(String place) {
		this.place = place;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
