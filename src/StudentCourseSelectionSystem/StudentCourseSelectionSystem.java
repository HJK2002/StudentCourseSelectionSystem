package StudentCourseSelectionSystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;
import java.util.StringTokenizer;

public class StudentCourseSelectionSystem {
	
	public static final String NEW_LINE=System.getProperty("line.separator");
	public static final String ISEND="0";
	public static final String ISENTITY="Entity";
	public static final String ISONLINE="Online";
	public static final String ISSEPERATE="_";
	
	private static CourseCatalog allCourseCatalog;
	private static StudentDataBase onlyStudentDataBase;
	private static Records onlyRecord;
	private static String managePassword;
	private Student nowStudent;
	private RecordFormatter currentRecordFormatter;
	
	Scanner reader=new Scanner(System.in);
	StudentCourseSelectionSystem(){
		
		allCourseCatalog=new CourseCatalog();
		onlyStudentDataBase=StudentDataBase.getOnlyStudentDataBase();
		onlyRecord=Records.getRecord();
		nowStudent=null;
		
	}
	
	public static void main(String[] args) throws IOException{
		if(args.length!=4) {
			System.err.println("The File is not enough.");
			System.exit(1);
		}else {
		
			StudentCourseSelectionSystem CourseSelectionSystem=new StudentCourseSelectionSystem();
		
			try {
			
				loadCourseCatalog(args[0]);
				loadStudentDataBase(args[1]);
				getManagementPassword(args[2]);
				loadStudentRecord(args[3]);
				CourseSelectionSystem.running(args[3]);
				writeCourseCatalog(args[0]);
				writeStudentDataBase(args[1]);
				writeManagementPassword(args[2]);
				
			}catch(FileNotFoundException e) {
			
				System.err.println("The file is not find.");
				System.exit(1);
			
			}catch(DataFormatException e) {
			
				System.err.println("The file contains malformed data:"+e.getMessage());
				System.exit(1);
			
			}
		}
		
	}
	
	private static void loadStudentDataBase(String filename) throws FileNotFoundException,IOException, DataFormatException {
		@SuppressWarnings("resource")
		BufferedReader studentReader=new BufferedReader(
									 new FileReader(filename));
		String line=studentReader.readLine();
		while(line!=null) {
			
			StringTokenizer arrayStudent=new StringTokenizer(line,ISSEPERATE);
			if(arrayStudent.countTokens()!=6) {
				
				throw new DataFormatException(line);
				
			}else {
				
				try {
					
					Student newStudent=new Student(arrayStudent.nextToken(),
												   arrayStudent.nextToken(), 
												   arrayStudent.nextToken(),
												   arrayStudent.nextToken(), 
												   arrayStudent.nextToken(), 
												   arrayStudent.nextToken());
					onlyStudentDataBase.addStudent(newStudent);
				
				}catch(NumberFormatException e) {
					
					throw new DataFormatException(line);
					
				}
				
			}
			line=studentReader.readLine();
		}
		studentReader.close();
	}
	//���ⲿ�ļ���ȡ���пγ�args[1]
	private static void loadCourseCatalog(String filename) throws FileNotFoundException,IOException,DataFormatException{
			@SuppressWarnings("resource")
			BufferedReader courseReader=new BufferedReader(
										new FileReader(filename));
			String line=courseReader.readLine();
			while(line!=null) {
				
				
				if(line.startsWith(ISENTITY)) {
					
					allCourseCatalog.addCourse(getEntityCourse(line));
					
				}else if(line.startsWith(ISONLINE)){
					
					allCourseCatalog.addCourse(getOnlineCourse(line));
					
				}else {
					
					throw new DataFormatException(line);
					
				}
				
				line=courseReader.readLine();
				
			}
			
			courseReader.close();
			
	}
	//���ⲿ�ļ���ȡ����Ա����args[2]
	private static void getManagementPassword(String passwordFile) throws IOException,FileNotFoundException{
		BufferedReader outPassword=new BufferedReader(
								   new FileReader(passwordFile));
		managePassword=outPassword.readLine();
		outPassword.close();
	
	}
	//���ⲿ�ļ���ȡѧ��ѡ����Ϣ
	private static void loadStudentRecord(String chooseRecord) throws FileNotFoundException,IOException,DataFormatException{
			@SuppressWarnings("resource")
			BufferedReader studentRecord=new BufferedReader(
			      					     new FileReader(chooseRecord));
			String line=studentRecord.readLine();
			while(line!=null) {
				StringTokenizer arrayRecord=new StringTokenizer(line,ISSEPERATE);
				if(arrayRecord.countTokens()!=2) {
					
					throw new DataFormatException(line);
					
				}else {
					
				    Student newStudent=onlyStudentDataBase.getStudent(arrayRecord.nextToken());
					Course newCourse=allCourseCatalog.getCourse(arrayRecord.nextToken());
					newStudent.getStudentCatalog().addCourse(newCourse);
				}
				line=studentRecord.readLine();
			}
			studentRecord.close();
	}
	//���н�����дCourse��Ϣ
	private static void writeCourseCatalog(String catalogFile) throws IOException {
		
		BufferedWriter writeCatalog=new BufferedWriter(
									new FileWriter(catalogFile));
		String fileString=new String();
		for(Course oneCourse:allCourseCatalog) {
			
			if(oneCourse instanceof EntityCourse) {
				
				EntityCourse entityCourse=(EntityCourse)oneCourse;
				fileString+=ISENTITY+ISSEPERATE+entityCourse.getCode()+
							ISSEPERATE+entityCourse.getName()+
							ISSEPERATE+entityCourse.getKind()+
							ISSEPERATE+entityCourse.getCredit()+
							ISSEPERATE+entityCourse.getTerm()+
							ISSEPERATE+entityCourse.getStudyHour()+NEW_LINE;
				
			}else if(oneCourse instanceof OnlineCourse) {
				
				OnlineCourse onlineCourse=(OnlineCourse)oneCourse;
				fileString+=ISENTITY+ISSEPERATE+onlineCourse.getCode()+
						ISSEPERATE+onlineCourse.getName()+
						ISSEPERATE+onlineCourse.getKind()+
						ISSEPERATE+onlineCourse.getCredit()+
						ISSEPERATE+onlineCourse.getPlatForm()+
						ISSEPERATE+onlineCourse.getNumberOfVideo()+NEW_LINE;
				
			}
		}
		writeCatalog.write(fileString);
		writeCatalog.close();
	}
	//���н�����дStudentDataBase;
	private static void writeStudentDataBase(String studentBaseFile) throws IOException {
		
		BufferedWriter writeDataBase=new BufferedWriter(
									 new FileWriter(studentBaseFile));
		String fileString=new String();
		for(Student oneStudent:onlyStudentDataBase) {
			
			fileString+=oneStudent.getID()+
						ISSEPERATE+oneStudent.getPassword()+
						ISSEPERATE+oneStudent.getName()+
						ISSEPERATE+oneStudent.getMajor()+
						ISSEPERATE+oneStudent.getClasses()+
						ISSEPERATE+oneStudent.getGrade()+NEW_LINE;
			
		}
		writeDataBase.write(fileString);
		writeDataBase.close();
	}
	//��д������дManage����
	private static void writeManagementPassword(String passwordFile) throws IOException {
		BufferedWriter writeManagePassword=new BufferedWriter(
										   new FileWriter(passwordFile));
		writeManagePassword.write(managePassword);
		writeManagePassword.close();
	}
	
	//�Ӽ��̻��ѡ��ֵ
	private int getChoice() {
			
			try {
				
				int a=Integer.parseInt(reader.nextLine());
				return a;
			}catch(NumberFormatException e) {
			}
			//�����ʽ�쳣������-1
			return -1;
			
	}
	//���˵�������Ϣ
	private void printer1() {
		
			System.out.println(" [0] Exit"+NEW_LINE+
						   	   " [1] Course SelectionSystem Logon"+NEW_LINE+
						   	   " [2] Manage(Administrators use)"+NEW_LINE+
				         	   " Choice-> ");
			
	}
	//ִ��ϵͳ
	private void running(String chooseRecord) {
		
		System.out.println("Weclome to use our system.");
		while(true) {
			
			printer1();
			int mainMenuChoice=getChoice();
			if(mainMenuChoice==0) {
				
				System.out.println("Thank you for your using.");
				reader.close();
				break;
				
			}else if(mainMenuChoice==-1) {
				
				System.out.println("The choice have format error.");
				
			}else if(mainMenuChoice==1) {
				
				courseSelectionSystemLogon(chooseRecord);
				
			}else if(mainMenuChoice==2){
				
				partOfManagement();
				
			}else {
				
				System.out.println("Data out of range");
				
			}
		}
		
	}
	//�ж��Ƿ��¼�ɹ����ɹ������ѡ�β���
	private void courseSelectionSystemLogon(String chooseRecord){
		
		if(logon()) {
			System.out.println("Welcome to the part of the course selection.");
			while(true) {
				
				printer2();
				int selectChoice=getChoice();
				if(selectChoice==0) {
					
					System.out.println("Thank you for your using.");
					break;
					
				}else if(selectChoice==-1) {
					
					System.out.println("The choice have format error.");
					
				}else if(selectChoice==1) {
					
					displayAllCourse();
					
				}else if(selectChoice==2) {
					
					displayDetailCourse();
					
				}else if(selectChoice==3) {
					
					displayChosenCourse();
					
				}else if(selectChoice==4) {
					
					courseSelection();
					
				}else if(selectChoice==5){
					
					courseDrop();
					
				}else if(selectChoice==6){
					changeStudentPassword();
				}else {
					
					System.out.println("Data out of range");
					
				}
				
			}
			  //���ѧ����¼
			for(Course oneCourse:nowStudent.getStudentCatalog()) {
				try {
					BufferedWriter writeChooseRecord=new BufferedWriter(
							                           new FileWriter(chooseRecord,true));
					String oneRecord=nowStudent.getID()+ISSEPERATE+oneCourse.getCode()+NEW_LINE;
					writeChooseRecord.write(oneRecord);
					writeChooseRecord.close();
				}catch(IOException e) {
					System.out.println("The file of the student record is lose.");
				}
			}
		}
		              
	}
	//��ӡѧ��ѡ�ν���˵�
	private void printer2() {
		
		System.out.println(" [0] Exit"+NEW_LINE+
						   " [1] Display all course"+NEW_LINE+
						   " [2] Display one detailed information"+NEW_LINE+
						   " [3] Display the class chosen by student"+NEW_LINE+
						   " [4] CourseSelection"+NEW_LINE+//ѡ��
						   " [5] CourseDrop"+NEW_LINE+	   //�˿�
						   " [6] Change PassWord"+NEW_LINE+
						   " Choice-> ");
		
	}
	//��¼
	private boolean logon() {
		
		System.out.println("Please enter your ID.");
		String outID=reader.nextLine();
		System.out.println("Please enter your password.");
		String outPassword=reader.nextLine();
		Student currentStudent=onlyStudentDataBase.getStudent(outID);
		if(currentStudent==null) {
			
			System.out.println("The ID is not efficent.");
			return false;
			
		}else {
			
			if(currentStudent.getPassword().equals(outPassword)) {
				System.out.println("Logon success");
				nowStudent=currentStudent;
				return true;
				
			}else {
				
				System.out.println("The password is not right.");
				return false;
				
			}
		}
		
	}
	//StudentSelect[1]չʾ���пγ̵�code,name,kind,credit
	private void displayAllCourse() {
		
		String allCourseString=new String();
		int i=1;
		for(Course currentCourse:allCourseCatalog) {
			
			allCourseString+="Course "+i+NEW_LINE+"   "+currentCourse.getCode()+"  "+currentCourse.getName()+"  "+
					         currentCourse.getKind()+"  "+currentCourse.getCredit()+"  "+NEW_LINE;
			i++;
			
		}
		System.out.println(allCourseString);
		
	}
	//StudentSelect[2]չʾ�����γ̵���ϸ��Ϣ�������ον�ʦ��ѧʱ������ƽ̨��
	private void displayDetailCourse() {
		
		System.out.println("Please input the code");
		String outCode=reader.nextLine();
		Course newCourse=allCourseCatalog.getCourse(outCode);
		if(newCourse==null) {
			
			System.out.println("The code does not correspod to one course");
			
		}else if(newCourse instanceof EntityCourse) {
			
			EntityCourse newEntity=(EntityCourse)newCourse;
			System.out.println(newEntity);
			
		}else if(newCourse instanceof OnlineCourse) {
			
			OnlineCourse newOnline=(OnlineCourse)newCourse;
			System.out.println(newOnline);
			
		}
		
	}
	//StudentSelect[3]չʾѧ���Ѿ�ѡ��Ŀγ��б�
	private void displayChosenCourse() {
		if(nowStudent.getStudentCatalog().getNumberOfAllCourses()==0) {
			System.out.println("The student have not chosen class.");
		}else {
			int i=1;
			String allChosenString=new String();
			for(Course currentCourse:nowStudent.getStudentCatalog()) {
			
				allChosenString+="Course "+i+NEW_LINE+currentCourse.getCode()+"  "+currentCourse.getName()+"  "+
								 currentCourse.getKind()+"  "+currentCourse.getCredit()+"  "+NEW_LINE;
				i++;
			
			}
			System.out.println(allChosenString);
		}
	}
	//StudentSelect[4]ѡ��
	private void courseSelection() {
		displayAllCourse();
		while(true) {
			System.out.println("Input the course code to select,input 0 to end.");
			String selectCode=reader.nextLine();
			if(selectCode.equals(ISEND)) {
				
				System.out.println("Select end");
				break;
				
			}
			Course outCourse=allCourseCatalog.getCourse(selectCode);
			if(outCourse==null) {
				
				System.out.println("Invaliud code");
				
			}else {
				if(nowStudent.getStudentCatalog().getCourse(selectCode)!=null) {
					System.out.println("The student already have this course.");
				}else {
					nowStudent.getStudentCatalog().addCourse(outCourse);
					makeRecordItem(outCourse);
					System.out.println("Add success");
				}
			}
			
		}
		
	}
	//StudentSelect[4]�к���.����ʷ��¼
	private void makeRecordItem(Course newCourse ) {
		
		Date nowDate=new Date();
		String nowTime=nowDate.toString();
		RecordItem newRecordItem=new RecordItem(newCourse,nowStudent,nowTime);
		onlyRecord.addRecordItem(newRecordItem);
		
	}
	//StudentSelect[5]�˿�
	private void courseDrop() {
		
		displayChosenCourse();
		System.out.println("Please input the course ID");
		String dropCode=reader.nextLine();
		nowStudent.getStudentCatalog().removeCourse(dropCode);
		
	}
	//StudentSelect[6]��������
	private void changeStudentPassword() {
		
		System.out.println("Please enter the new Password");
		nowStudent.setPassword(reader.nextLine());
		System.out.println("Change sucess");
		
	}
	//����ϵͳ���(ֻ�й���Ա����)
	private void partOfManagement() {
		
		System.out.println("Please enter the password:");
		String outPassword=reader.nextLine();
		if(outPassword.equals(managePassword)) {
			
			System.out.println("Welcome to the part of the management.");
			
			while(true) {
				
				printer3();
				int manageChoice=getChoice();
				if(manageChoice==0) {
					
					System.out.println("Thank you for using the part of management.");
					
					break;
				}else if(manageChoice==-1) {
					
					System.out.println("The choice have format error.");
					
				}else if(manageChoice==1) {
					
					addCourse();
					
				}else if(manageChoice==2) {
					
					removeCourse();
					
				}else if(manageChoice==3) {
					
					displayAllCourse();
					
				}else if(manageChoice==4) {
					
					removeStudent();
					
				}else if(manageChoice==5) {
					
					displayAllStudent();
					
				}else if(manageChoice==6){
					
					formatRecordPrint();
					
				}else if(manageChoice==7){
					
					changeManagePassword();
					
				}else {
					
					System.out.println("Data out of range");
					
				}
				
			}
		}else {
			
			System.out.println("The password is not correct.");
			
		}
		
	}
	//��ӡ����ϵͳ�˵�
	private void printer3() {
		
		System.out.println(" [0] Exit"+NEW_LINE+
						   " [1] Add course "+NEW_LINE+
						   " [2] Remove course "+NEW_LINE+
						   " [3] Display all course"+NEW_LINE+
						   " [4] Remove student"+NEW_LINE+
						   " [5] Display all student message"+NEW_LINE+
						   " [6] Write file of select record"+NEW_LINE+
						   " [7] Change password"+NEW_LINE+
						   " choice-> ");
		
	}
	//manage[1] �ֶ���ӿγ�(��ȡ�ļ����ٽ��е�����)
	private void addCourse() {
		
		System.out.println("Please input data accroding to the followings."+NEW_LINE+
						   "Entitycourse: Entity_code_name_kind_credit_term_studyhour"+NEW_LINE+
						   "Onlinecourse: Online_code_name_kind_credit_platForm_numberOfVideo"+NEW_LINE);
		String outCourseString=reader.nextLine();
		try {
			
			if(outCourseString.startsWith(ISENTITY)) {
				
				allCourseCatalog.addCourse(getEntityCourse(outCourseString));
				
			}else if(outCourseString.startsWith(ISONLINE)){
				
				allCourseCatalog.addCourse(getOnlineCourse(outCourseString));
				
			}else {
				
				System.out.println("The format is not right.");
				
			}
		}catch(DataFormatException e) {
			
			System.out.println("The format is not right.");
			
		}
	}
	//manage[1] 1.��ȡ���¿�
	private static Course getEntityCourse(String message) throws DataFormatException{
		
		StringTokenizer arrayEntity=new StringTokenizer(message,ISSEPERATE);
		if(arrayEntity.countTokens()!=7) {
			
			throw new DataFormatException(message);
			
		}else {
			
			try {
				
				arrayEntity.nextToken();
				EntityCourse newEntityCourse=new EntityCourse(arrayEntity.nextToken(),
															  arrayEntity.nextToken(),
															  arrayEntity.nextToken(),
															  Double.parseDouble(arrayEntity.nextToken()),
															  arrayEntity.nextToken(),
															  Integer.parseInt(arrayEntity.nextToken()));
				
				return newEntityCourse;
				
			}catch(NumberFormatException e) {
				
				throw new DataFormatException(message);
				
			}
		}
	}
	//manage[1] 2.��ȡ����
	private static Course getOnlineCourse(String message) throws DataFormatException{
		
		StringTokenizer arrayOnline=new StringTokenizer(message,ISSEPERATE);
		if(arrayOnline.countTokens()!=7) {
			
			throw new DataFormatException(message);
			
		}else {
			
			try {
				
				arrayOnline.nextToken();
				OnlineCourse newEntityCourse=new OnlineCourse(arrayOnline.nextToken(),
															  arrayOnline.nextToken(),
														  	  arrayOnline.nextToken(),
														  	  Double.parseDouble(arrayOnline.nextToken()),
														  	  arrayOnline.nextToken(),
														  	  Integer.parseInt(arrayOnline.nextToken()));
				 
				return newEntityCourse;
				
			}catch(NumberFormatException e) {
				
				throw new DataFormatException(message);
				
			}	 	
		}
	}
	//[2]�Ƴ��γ�
	private void removeCourse() {
		
		System.out.println("Please input the course code.");
		String code=reader.nextLine();
		allCourseCatalog.removeCourse(code);
		
	}
	//[4]�Ƴ�ѧ��
	private void removeStudent() {
		
		System.out.println("Please input the student ID.");
		String ID=reader.nextLine();
		onlyStudentDataBase.removeStudent(ID);
		
	}
	//[5]չʾ����ѧ����Ϣ(����������ѡ�Ŀγ�)
	private void displayAllStudent() {
		
		int i=1;
		String allStudentString=new String();
		for(Student currentStudent:onlyStudentDataBase) {
			
			allStudentString+="Student "+i+NEW_LINE+
						currentStudent.toString()+NEW_LINE+
						"Chosen class:"+NEW_LINE;
			if(currentStudent.getStudentCatalog().getNumberOfAllCourses()==0) {
				allStudentString=allStudentString+"null"+NEW_LINE;
			}else {
				for(Course currentCourse:currentStudent.getStudentCatalog()) {
					allStudentString+=currentCourse.getCode()+"  "+currentCourse.getName()+NEW_LINE;
				}
				i++;
			}
		}
		System.out.println(allStudentString);
		
	}
	//[6]�� plainText��HTML��ʽ��ӡѡ�μ�¼��Ϣ���ļ���
	private void formatRecordPrint() {
		
			System.out.println(" [1] PlainText format"+NEW_LINE+
							   " [2] HTML format"+NEW_LINE+
							   "choice-> ");
			int outFormatChoice=getChoice();
			 if(outFormatChoice==-1) {
				
				System.out.println("The choice have format error.");
				
			}else if(outFormatChoice==1) {
				
				currentRecordFormatter=PlainTextRecordFormatter.getPlainTextSRecordsFormatter();
				writeOutFile();
				
			}else if(outFormatChoice==2) {
				
				currentRecordFormatter=HTMLRecordsFormatter.getHTMLSalesFormatter();
				writeOutFile();
				
			}else {
				
				System.out.println("Data out rannge");
				
			}
		
	}
	//д���ļ�
	private void writeOutFile() {
		
		System.out.println("Please enter the file name.");
		String filename=reader.nextLine();
		try {
			
			BufferedWriter bufferWrite=new BufferedWriter(
								new FileWriter(filename));
			bufferWrite.write(currentRecordFormatter.formatRecord(onlyRecord));
			bufferWrite.close();
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
				
	}
	private void changeManagePassword(){
		
		System.out.println("Please input new password");
		managePassword=reader.nextLine();
		System.out.println("Change success.");
		
	}
	
}