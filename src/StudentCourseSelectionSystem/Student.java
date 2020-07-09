package StudentCourseSelectionSystem;


public class Student {
	public static final String NEW_LINE=System.getProperty("line.separator");
	private String ID;
	private String password;
	private String name;
	private String major;
	private String classes;
	private String grade;
	private CourseCatalog studentCatalog;
	Student(String ID,String password,String name,String major,String classes,String grade){
		this.ID=ID;
		this.password=password;
		this.name=name;
		this.major=major;
		this.classes=classes;
		this.grade=grade;
		studentCatalog=new CourseCatalog();
	}
	public String getID() {
		
		return ID;
		
	}
	public String getPassword() {
		
		return password;
		
	}
	public void setPassword(String newPassword) {
		password=newPassword;
	}
	public String getName() {
		
		return name;
		
	}
	public String getMajor() {
		
		return major;
		
	}
	public String getClasses() {
		
		return classes;
		
	}
	public String getGrade() {
		
		return grade;
		
	}
	public String toString() {
		
		return "           ID: "+ID+NEW_LINE+
			   "         Name: "+name+NEW_LINE+
			   "        Major: "+major+NEW_LINE+
			   "        Class: "+classes+NEW_LINE+
			   "        Grade: "+grade;
		
	}
	public CourseCatalog getStudentCatalog() {
		return studentCatalog;
	}
	
}
