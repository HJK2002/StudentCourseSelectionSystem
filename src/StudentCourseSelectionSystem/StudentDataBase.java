package StudentCourseSelectionSystem;

import java.util.ArrayList;
import java.util.Iterator;

public class StudentDataBase implements Iterable<Student>{
	private ArrayList<Student> students;
	private static StudentDataBase onlyStudentDataBase=new StudentDataBase();
	private StudentDataBase() {
		students=new ArrayList<Student>();
	}
	public static StudentDataBase getOnlyStudentDataBase() {
		return onlyStudentDataBase;
	}
	public void addStudent(Student newStudent) {
		int n=0;
		for(Student currentStudent:students) {
			if(currentStudent.getID().equals(newStudent.getID())) {
				System.out.println("The code has already exist.");
				break;
			}else {
				n++;
			}
		}
		if(n==students.size()) 
			students.add(newStudent);
	}
	public void removeStudent(String ID) {
		Student oneAdopter=getStudent(ID);
		if(oneAdopter==null) {
			System.out.println("The code does not correspod to one student.");
		}else {
			students.remove(oneAdopter);
			System.out.println("The student is already deleted.");
		}
	}
	public Student getStudent(String ID) {
		for(Student currentStudent:students) {
			if(currentStudent.getID().equals(ID))
				return currentStudent;
		}
		return null;
	}
	public int getNumberOfAllStudent() {
		return students.size();
	}
	public Iterator<Student> iterator(){
		return this.students.iterator();
	}
}
