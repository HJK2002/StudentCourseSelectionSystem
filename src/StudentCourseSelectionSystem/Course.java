package StudentCourseSelectionSystem;

public class Course {
	public static final String NEW_LINE=System.getProperty("line.separator");
	private String code;
	private String name;
	private String kind;
	private double credit;
	public Course(){
		
	}
	public Course(String code,String name,String kind,double credit){
		
			this.code=code;
			this.name=name;
			this.kind=kind;
			this.credit=credit;
			
	}
	public String getCode() {
		
		return code;
		
	}
	public String getName() {
		
		return name;
		
	}
	public String getKind() {
		
		return kind;
		
	}
	public double getCredit() {
		
		return credit;
		
	}
	public String toString() {
		
		String outString=new String();
	    outString+=	"         Code: "+code+NEW_LINE+
					"         Name: "+name+NEW_LINE+
					"         Kind: "+kind+NEW_LINE+
					"       Credit: "+credit;
	    return outString;
	    
	}
}
