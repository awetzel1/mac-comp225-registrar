package registrar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by bjackson on 2/21/2016.
 * Refactored by Amber Wetzel
 */
public class Course {

    private Set<Student> enrolledIn;
    private List<Student> waitlist;
    private String number;
    private String name;
    private int limit;

    public Course(){
        enrolledIn = new HashSet<>();
        waitlist = new ArrayList<>();
        limit = 16;
    }

    public void setCatalogNumber(String number){
        this.number = number;
    }

    public void setTitle(String title){
        this.name = title;
    }

    public int getEnrollmentLimit(){
        return limit;
    }

    public boolean setEnrollmentLimit(int limit){
        //If students are enrolled you can't change the limit
        if (enrolledIn.size() == 0){
            this.limit = limit;
            return true;
        }
        return false;
    }

    public Set<Student> getStudents(){
        return enrolledIn;
    }

    public List<Student> getWaitList(){
        return waitlist;
    }


    /*
    Enrolls the passed in student to the courseenrolledIn.add(student);
        return true;
    */
    public boolean enrollIn(Student student){
        if (enrolledIn.contains(student)){ //step 1: check if student is already enrolled
            return true;
        }
        if (enrolledIn.size() >= limit){ //step 2: if the class is full, waitlist the student
            return joinWaitlist(student);
        }

        else { //step 3: if the student is not in the class, and the class is not full, enroll them
            enrolledIn.add(student);
            return true;
        }

    }

    /*
    Enrolls the passed in student to the course's waitlist;
        return false;
    NOTE: Assumes enrolledIn.size() >= limit
    */
    public boolean joinWaitlist(Student student){
        if (waitlist.contains(student)){ //check if student is already waitlisted
            return false;
        }
        waitlist.add(student); //If student is not waitlisted, waitlist them
        return false;
        }

    /*
    Drops the passed in student from the course
    */
    public void dropStudent(Student student){
        if (enrolledIn.contains(student)) {
            enrolledIn.remove(student);
            enrollFromWaitlist();
        }
        else {dropFromWaitlist(student);}
    }

    /*
    Enrolls the first student from the waitlist into the course, and adds the course to that student's courses
     */
    public void enrollFromWaitlist(){
        if (waitlist.size() > 0) {
            Student toEnroll = waitlist.remove(0);
            enrolledIn.add(toEnroll);
            toEnroll.enrolledIn.add(this);
        }
    }

    /*
    Drops the passed in student from the waitlist
     */
    public void dropFromWaitlist(Student student){
        if (waitlist.contains(student)){
            waitlist.remove(student);
        }
    }

}
