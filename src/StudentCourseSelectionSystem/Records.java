package StudentCourseSelectionSystem;

import java.util.ArrayList;
import java.util.Iterator;

public class Records implements Iterable<RecordItem>{
	public static final String NEW_LINE=System.getProperty("line.separator");
	private ArrayList<RecordItem> record;
	private static Records onlyRecord=new Records();
	private Records() {
		record=new ArrayList<RecordItem>();
	}
	public static Records getRecord() {
		return onlyRecord;
	}
	public void addRecordItem(RecordItem recordItem) {
		record.add(recordItem);
	}
	public int getNumberOfAllRecordItem() {
		return record.size();
	}
	public String toString() {
		String outString=null;
		int i=1;
		for(RecordItem oneRecordItem:record) {
			outString+="record "+i+":"+NEW_LINE+
					oneRecordItem.toString()+NEW_LINE;
			i++;
		}
		return outString;
	}
	public Iterator<RecordItem> iterator(){
		return this.record.iterator();
	}
}
