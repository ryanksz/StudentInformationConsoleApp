package StudentListCreator;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

public class StudentDaoImpl implements StudentDao {

    @Override
    public void showStudentList() {

        /*
        For this to work in intellij, run -> edit configurations
        then set working directory to this package
        This loops through the file and reads the students info
         */
        //from lecture 18
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("StudentList.txt"))) {
            int intVal;
            while ((intVal = bufferedReader.read()) >= 0) {
                char charVal = (char) intVal;
                System.out.print(charVal);
                //bufferedReader.close();
            }

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }

    @Override
    public void addNewStudent(Student newStudent) {
        /*
        This adds the Student object to the text file
        Simply writes it to the StudentList.txt
         */
        try {
            String fileName = "StudentList.txt";
            FileWriter fileWriter = new FileWriter(fileName, true);
            fileWriter.write("\n" + newStudent.toString());
            fileWriter.close();

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @Override
    public void editStudentById(int id) {
        Student editStudent = findStudentById(id);
        /*
        This lets the edit the student by deleting the original data from the file,
        then letting the user enter the updated info for the student
         */
        try {
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            System.out.println(editStudent.toString());
            //delete old one
            deleteStudentById(id);
            System.out.println("Enter new info (john,smith,fn2187,4.0,2010)");
            //read what's inputted
            String addNew = null;
            addNew = userInput.readLine();
            //print to verify
            System.out.println("Input Statement: " + addNew);
            //separate by comma
            String[] editInput = addNew.split(",");
            //get id
            int newId = Integer.parseInt(editInput[0]);
            //get first name and last name and access id
            String fName = editInput[1];
            String lName = editInput[2];
            String access = editInput[3];
            //get gpa
            double newGpa = Double.parseDouble(editInput[4]);
            //get year entered
            int newYear = Integer.parseInt(editInput[5]);
            //create new student
            Student newStudent = new Student(newId, fName, lName, access, newGpa, newYear);
            //send to addNewStudent
            addNewStudent(newStudent);
            System.out.println("Added: " + newStudent.toString());
        } catch (Exception e) {
            System.out.println("error not found: " + e);
        }
    }

    @Override
    public void deleteStudentById(int id) {
        /*
        This gets the id from user then
        goes through the file and uses .filter to take out the student to be deleted
        then stores everyone else then writes everyone else back to the file at the end
         */
        try {

            Student toDelete = findStudentById(id);
            System.out.println(toDelete.toString());
            String stringToSearch = toDelete.toString();
            File studentFile = new File("StudentList.txt");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(studentFile));
            String file;
            while ((file = bufferedReader.readLine()) != null) {
                List<String> out = Files.lines(studentFile.toPath()).filter(lines ->
                        !lines.contains(stringToSearch)).collect(Collectors.toList());
                Files.write(studentFile.toPath(), out, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);

            }

        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (NullPointerException nullPointerException) {
            nullPointerException.getCause().getMessage();
        }
    }

    @Override
    public Student findStudentById(int id) {
        /*
        This searches for the student by id by taking the text
        and creates each line into a Student object.
        After that it compares all the ids with the id inputted and returns a matching student
         */
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("StudentList.txt"))) {
            String file;
            while ((file = bufferedReader.readLine()) != null) {
                String[] separate = file.split("\n");
                for (int i = 0; i < separate.length; i++) {
                    if(separate[i].equals("")) {
                        //if there is a blank line in the file,this ignores it
                        System.out.println("blank line");
                        break;
                    }
                    String[] findStudent = separate[i].split(",");
                    //get id
                    int findId = Integer.parseInt(findStudent[0]);
                    //first,last,access id
                    String findFName = findStudent[1];
                    String findLName = findStudent[2];
                    String findAccessId = findStudent[3];
                    //get gpa
                    double findGpa = Double.parseDouble(findStudent[4]);
                    //get year entered
                    int findYear = Integer.parseInt(findStudent[5]);
                    //create student
                    Student studentToFind = new Student(findId,findFName,findLName,findAccessId,findGpa,findYear);
                    if(id == studentToFind.getId()) {
                        System.out.print("Found: "+studentToFind.toString());
                        return studentToFind;
                    }else {
                        System.out.println("Not Found");
                    }

                }
            }
        } catch (NullPointerException | IOException nullPointerException) {
            nullPointerException.getCause().getMessage();
            System.out.println("Not Found");
        }
        return null;
    }

    @Override
    public Student findByFirstName(String first) {
      /*
        Same as find student but looks for first name
         */
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("StudentList.txt"))) {
            String file;
            while ((file = bufferedReader.readLine()) != null) {
                String[] separate = file.split("\n");
                for (int i = 0; i < separate.length; i++) {
                    if(separate[i].equals("")) {
                        //if there is a blank line in the file,this ignores it
                        System.out.println("blank line");
                        break;
                    }
                    String[] findStudent = separate[i].split(",");
                    //get id
                    int findId = Integer.parseInt(findStudent[0]);
                    //first,last,access id
                    String findFName = findStudent[1];
                    String findLName = findStudent[2];
                    String findAccessId = findStudent[3];
                    //get gpa
                    double findGpa = Double.parseDouble(findStudent[4]);
                    //get year entered
                    int findYear = Integer.parseInt(findStudent[5]);
                    //create student
                    Student studentToFind = new Student(findId,findFName,findLName,findAccessId,findGpa,findYear);
                    if(first.equals(studentToFind.getFirstName())) {
                        System.out.print("Found: "+studentToFind.toString());
                        return studentToFind;
                    }else {
                        System.out.println("Not Found");
                    }

                }
            }
        } catch (NullPointerException | IOException nullPointerException) {
            nullPointerException.getCause().getMessage();
            System.out.println("Not Found");
        }

        return null;
    }

    @Override
    public Student findByLastName(String last) {
        /*
        Same as find student, but looks for last name
         */
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("StudentList.txt"))) {
            String file;
            while ((file = bufferedReader.readLine()) != null) {
                String[] separate = file.split("\n");
                for (int i = 0; i < separate.length; i++) {
                    if(separate[i].equals("")) {
                        //if there is a blank line in the file,this ignores it
                        System.out.println("blank line");
                        break;
                    }
                    String[] findStudent = separate[i].split(",");
                    //get id
                    int findId = Integer.parseInt(findStudent[0]);
                    //first,last,access id
                    String findFName = findStudent[1];
                    String findLName = findStudent[2];
                    String findAccessId = findStudent[3];
                    //get gpa
                    double findGpa = Double.parseDouble(findStudent[4]);
                    //get year entered
                    int findYear = Integer.parseInt(findStudent[5]);
                    //create student
                    Student studentToFind = new Student(findId,findFName,findLName,findAccessId,findGpa,findYear);
                    if(last.equals(studentToFind.getLastName())) {
                        System.out.print("Found: "+studentToFind.toString());
                        return studentToFind;
                    }else {
                        System.out.println("Not Found");
                    }

                }
            }
        } catch (NullPointerException | IOException nullPointerException) {
            nullPointerException.getCause().getMessage();
            System.out.println("Not Found");
        }
        return null;
    }

}
