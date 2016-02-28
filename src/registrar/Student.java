package registrar;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by bjackson on 2/21/2016.
 * Refactored by Amber Wetzel
 */
public class Student {

    private String name;
    public Set<Course> courses;

    public Student(){
        courses = new HashSet<>();
    }

    public void setName(String name){
        //if (name == null) {
        //    throw new IllegalAccessException("Names cannot be null");
        //}
        this.name = name;
    }

    public String getName(){return name;}

    public Set<Course> getCourses(){
        return courses;
    }

    /*
    Enrolls the student in the passed in course
     */
    public boolean enrollIn(Course course){ //this method is not named enroll() like it is described on git.
        if(course.enroll(this)) {
            courses.add(course);
            return true;
        }
        else {
            return false; //is this an error message or a sign they've been waitlisted?
        }
    }

    /*
    Drops the student from the passed in course
     */
    public void drop(Course course){
        if (courses.contains(course)) {
            courses.remove(course);
        }
        course.dropStudent(this);
    }

    @Override
    public String toString() {
        return getName();
    }
}
