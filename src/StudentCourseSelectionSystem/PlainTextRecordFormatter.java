package StudentCourseSelectionSystem;

public class PlainTextRecordFormatter implements RecordFormatter{
	public final static String NEW_LINE=System.getProperty("line.separator");
	private static PlainTextRecordFormatter onlyPlainTextRecordsFormatter=new PlainTextRecordFormatter();
	private PlainTextRecordFormatter(){
		
	}
	public static PlainTextRecordFormatter getPlainTextSRecordsFormatter() {
		return onlyPlainTextRecordsFormatter;
	}

	public String formatRecord(Records record) {
		String outString=new String();
		int i=1;
		for(RecordItem currentItem: record) {
			outString+="----------------------"+NEW_LINE+
					   "Record "+i+NEW_LINE+
					    currentItem.toString()+NEW_LINE;
			i++;
		}
		return outString;
	}
}
