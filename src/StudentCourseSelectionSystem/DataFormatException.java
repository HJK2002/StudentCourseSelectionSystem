package StudentCourseSelectionSystem;

public class DataFormatException extends Exception{
	
	private static final long serialVersionUID = 1L;
	public DataFormatException() {
		
	}
	public DataFormatException(String line) {
		super(line);
	}
}
