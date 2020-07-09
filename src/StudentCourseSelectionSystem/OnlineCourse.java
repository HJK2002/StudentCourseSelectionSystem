package StudentCourseSelectionSystem;

public class OnlineCourse extends Course{
	private String platForm;
	private int numberOfVideo;
	public OnlineCourse(String code,String name,String kind,double credit,String platForm,int numberOfVideo) {
		
		super(code,name,kind,credit);
		this.numberOfVideo=numberOfVideo;
		this.platForm=platForm;
		
	}
	public String getPlatForm() {
		
		return platForm;
		
	}
	public int getNumberOfVideo() {
		
		return numberOfVideo;
		
	}
	public String toString() {
		
		return super.toString()+NEW_LINE+
				"     PlatForm: "+platForm+NEW_LINE+
				"NumberOfVideo: "+numberOfVideo+NEW_LINE;
				
	}
	
}
