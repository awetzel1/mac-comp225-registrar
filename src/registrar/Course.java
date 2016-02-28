package registrar;

import java.util.*;

/**
 * Created by bjackson on 2/21/2016.
 * Refactored by Amber Wetzel
 */
public class Course {

    private Set<Student> roster = new HashSet<>();
    private List<Student> waitlist = new ArrayList<>();
    private String catalogNumber;
    private String title;
    private int limit = 99999;


    public String getCatalogNumber() {
        return catalogNumber;
    }

    public void setCatalogNumber(String number){
        this.catalogNumber = number;
    }

    public void setTitle(String title){
        if(title == null) {
            throw new IllegalArgumentException("course title cannot be null");
        }
        this.title = title;
    }

    public int getEnrollmentLimit(){
        return limit;
    }

    public boolean setEnrollmentLimit(int limit){
        if (limit < 0) {
            throw new IllegalArgumentException("course cannot have negative enrollment limit: " + limit);
        }

        if (limit >= roster.size()){ //covers both == 0 and updated limits
            this.limit = limit;
            return true;
        }
        else { //when limit is less than # of students enrolled but not negative
            throw new IllegalArgumentException("cannot set the enrollment limit less than the amount currently enrolled: " + limit);
        }
    }

    public void removeEnrollmentLimit(){
        this.limit = 99999; //TODO: Fix me

        while(!waitlist.isEmpty()){enrollFromWaitlist();}
    }

    public Set<Student> getStudents(){
        return Collections.unmodifiableSet(roster);
    }

    public List<Student> getWaitList(){

        return Collections.unmodifiableList(waitlist);
    }


    /*
    Enrolls the passed in student to the courseenrolledIn.add(student);
        return true;
    */
    public boolean enroll(Student student){
        if (roster.contains(student)){ //step 1: check if student is already enrolled
            return true;
        }
        if (roster.size() >= limit){ //step 2: if the class is full, waitlist the student
            return joinWaitlist(student);
        }

        else { //step 3: if the student is not in the class, and the class is not full, enroll them
            roster.add(student);
            return true;
        }

    }

    /*
    Enrolls the passed in student to the course's waitlist;
        return false;
    NOTE: does not check if the course is already full
    (NOTE: Can be made smaller by becoming a void method using if(!waitlist.contains(s)))
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
        if (roster.contains(student)) {
            roster.remove(student);
            enrollFromWaitlist();
        }
        else {dropFromWaitlist(student);}
    }

    /*
    Enrolls the first student from the waitlist into the course, and adds the course to that student's courses
     */
    private void enrollFromWaitlist(){
        if (!waitlist.isEmpty()) {
            Student toEnroll = waitlist.remove(0);
            roster.add(toEnroll);
            toEnroll.courses.add(this);
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
