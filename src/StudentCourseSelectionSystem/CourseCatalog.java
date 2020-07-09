package StudentCourseSelectionSystem;

import java.util.ArrayList;
import java.util.Iterator;

public class CourseCatalog implements Iterable<Course>{
	private ArrayList<Course> allCourses;
	public CourseCatalog() {
		
		allCourses=new ArrayList<Course>();
		
	}
	public void addCourse(Course course) {
		
		int n=0;
		for(Course currentCourse:allCourses) {
			if(currentCourse.getCode().equals(course.getCode())) {
				//System.out.println("The code has already exist.");
				break;
			}else {
				n++;
			}
		}
		if(n==allCourses.size()) {
			allCourses.add(course);
		}
		
	}
	public void removeCourse(String code) {
		
		Course oneCourse=getCourse(code);
		if(oneCourse==null) {
			System.out.println("The code does not correspod to one course.");
		}else {
			allCourses.remove(oneCourse);
			System.out.println("The course is already deleted.");
		}
		
	}
	public Course getCourse(String code) {
		
		for(Course currentCourse:allCourses) {
			if(currentCourse.getCode().equals(code))
				return currentCourse;
		}
		return null;
		
	}
	public int getNumberOfAllCourses() {
		
		return allCourses.size();
		
	}
	public Iterator<Course> iterator(){
		
		return this.allCourses.iterator();
		
	}
}
