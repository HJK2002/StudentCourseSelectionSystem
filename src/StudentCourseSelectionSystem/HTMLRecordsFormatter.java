package StudentCourseSelectionSystem;

public class HTMLRecordsFormatter implements RecordFormatter{
	public final static String NEW_LINE=System.getProperty("line.separator");
	private static HTMLRecordsFormatter onlyHTMLRecordsFormatter=new HTMLRecordsFormatter();
	private HTMLRecordsFormatter() {
		
	}
	public static HTMLRecordsFormatter getHTMLSalesFormatter() {
		return onlyHTMLRecordsFormatter;
	}
	public String formatRecord(Records record) {
		int i=1;
		String outString=new String();
		outString+="<html>"+NEW_LINE+
				   "  <body>"+NEW_LINE+
				   "    <center><h2>Records</h2></center>"+NEW_LINE;
		for(RecordItem recordItem:record) {
			outString+=
				   "    <hr>"+NEW_LINE+
				   "    <h4>RecordItem "+i+"</h4>"+NEW_LINE+
				   "    <p>"+NEW_LINE+
				   "      <b>courseCode:</b> "+recordItem.getOutCourse().getCode()+"<br>"+NEW_LINE+
				   "      <b>courseName:</b> "+recordItem.getOutCourse().getName()+"<br>"+NEW_LINE+
			       "      <b>studentID: </b> "+recordItem.getOutStudent().getID()+"<br>"+NEW_LINE+
			       "      <b>studentName: </b> "+recordItem.getOutStudent().getName()+"<br>"+NEW_LINE+
				   "      <b>price:</b> "+recordItem.getSelectTime()+ NEW_LINE+
				   "   </p>"+NEW_LINE;
			i++;
		}
		outString+="  </body>"+ NEW_LINE+
				   "</html>"+NEW_LINE;
		return outString;
	}
}