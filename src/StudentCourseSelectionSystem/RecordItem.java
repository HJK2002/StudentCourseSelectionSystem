package StudentCourseSelectionSystem;

public class RecordItem {
	
	public static final String NEW_LINE=System.getProperty("line.separator");
	private Course outCourse;
	private Student outStudent;
	private String selectTime;
	public RecordItem(Course course,Student student,String selectTime) {
		
		this.outCourse=course;
		this.outStudent=student;
		this.selectTime=selectTime;
		
	}
	public Course getOutCourse() {
		
		return outCourse;
		
	}
	public Student getOutStudent() {
		
		return outStudent;
		
	}
	public String getSelectTime() {
		
		return selectTime;
		
	}
	public String toString() {
		
		return "    Course:"+outCourse.getCode()+"  "+outCourse.getName()+"！！！！>"+
			   "Student:"+outStudent.getID()+"  "+outStudent.getName()+
			   NEW_LINE+"SelectTime:"+selectTime;
		
	}
	
}
