package StudentListCreator;


import java.util.Comparator;

public class Student implements Comparator<Student> {
    //step 3
    int id;
    String firstName;
    String lastName;
    String accessId;
    double gpa;
    int enterYear;

    public Student(int id, String firstName, String lastName, String accessId, double gpa, int enterYear) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accessId = accessId;
        this.gpa = gpa;
        this.enterYear = enterYear;
    }

    //step 4a
    @Override
    public String toString() {
        return id +","+
                firstName+","+
                lastName+","+
                accessId+","+
                gpa+","+
                enterYear;

    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAccessId() {
        return accessId;
    }

    public void setAccessId(String accessId) {
        this.accessId = accessId;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public int getEnterYear() {
        return enterYear;
    }

    public void setEnterYear(int enterYear) {
        this.enterYear = enterYear;
    }


    @Override
    public int compare(Student o1, Student o2) {
        return 0;
    }



}
