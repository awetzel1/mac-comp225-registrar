package registrar;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by bjackson on 2/21/2016.
 * Refactored by Amber Wetzel
 */
public class Student {

    public String name;
    public Set<Course> enrolledIn;

    public Student(){
        enrolledIn = new HashSet<>();
    }

    public void setName(String name){
        this.name = name;
    }

    public Set<Course> getCourses(){
        return enrolledIn;
    }

    /*
    Enrolls the student in the passed in course
     */
    public boolean enrollIn(Course course){
        if(course.enrollIn(this)) {
            enrolledIn.add(course);
            return true;
        }
        else {
            return false;
        }
    }

    /*
    Drops the student from the passed in course
     */
    public void drop(Course course){
        if (enrolledIn.contains(course)) {
            enrolledIn.remove(course);
        }
        course.dropStudent(this);
    }
}
