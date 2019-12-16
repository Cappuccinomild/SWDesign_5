package swdesign;
public class Exam extends Course{
	public String exam_room, stime, ftime, exam_date;
	
	public Exam(String lecture_code, String lecture_name, String professor_info, String lecture_stime, String lecture_ftime, String lecture_date,String lecture_room, String stime, String ftime, String exam_date, String exam_room) {
		super(lecture_code, lecture_name, professor_info, lecture_stime, lecture_ftime, lecture_date, lecture_room);
		setPlace(exam_room);
		setTime(stime, ftime, exam_date);
	}
	
	public void setPlace(String exam_room) {
		this.exam_room = exam_room;
	}
	
	public void setTime(String stime, String ftime, String exam_date) {
		this.stime = stime;
		this.ftime = ftime;
		this.exam_date = exam_date;
	}
	public String getExam() {
		return	   "<tr style=' border : 1px solid #808080; text-align: center; padding: 10px; ' id=course_text>"+
				   "<td>"+lecture_code + "</td>" +
				   "<td>"+lecture_name + "</td>" +
				   "<td>"+exam_room + "</td>" +
				   "<td>"+stime + "</td>" +
				   "<td>"+ftime + "</td>" +
				   "<td>"+exam_date + "</td>" +
				   "</tr>";
				   	
	}
		
}
