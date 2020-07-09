package StudentCourseSelectionSystem;

public class EntityCourse extends Course{
	private String term;
	private int studyHour;
	public EntityCourse() {
		
	}
	public EntityCourse(String code,String name,String kind,double credit,String term,int studyHour) {
		
		super(code, name, kind,credit);
		this.term=term;
		this.studyHour=studyHour;
		
	}
	public String getTerm() {
		
		return term;
		
	}
	public int getStudyHour() {
		
		return studyHour;
		
	}
	public String toString() {
		
		return super.toString()+NEW_LINE+
				"         Term: "+term+NEW_LINE+
				"    StudyHour: "+studyHour;
		
	}
}
