package StudentListCreator;


public interface StudentDao {
    void showStudentList();
    void addNewStudent(Student newStudent);
    void editStudentById(int id);
    void deleteStudentById(int id);
    Student findStudentById(int id);
    Student findByFirstName(String first);
    Student findByLastName(String last);





}
