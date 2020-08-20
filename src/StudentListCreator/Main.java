package StudentListCreator;

/*
This creates a list of students that can be read and written to
The purpose of this was to understand java classes, interfaces, error handling,
some collecting, file manipulation and more.

Created by Ryan Kaszubski


For file StudentList.txt to work (how I got it to work)
in intellij, run -> edit configurations
then set working directory to this package
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        //main menu
        //step 1-2
            boolean menu;
            while (menu = true) {
                System.out.println("Student Information");
                System.out.println("1. Show Student List");
                System.out.println("2. Add a New Student");
                System.out.println("3. Edit a Student by ID");
                System.out.println("4. Delete a Student");
                System.out.println("5. Find a Student by ID");
                System.out.println("6. Find Students(s) by First Name");
                System.out.println("7. Find Students(s) by Last Name");

                StudentDaoImpl studentDao = new StudentDaoImpl();


                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                    String input = null;
                    System.out.println("Select 1-7 or press q to exit:");
                    input = bufferedReader.readLine();
                    System.out.println(input);
                    if (input.equals("1")) {
                        //show list
                        studentDao.showStudentList();
                        System.out.println();

                    } else if (input.equals("2")) {
                        //add new student
                        //read what the user wants to add:
                        try {
                            BufferedReader oBufferedReader = new BufferedReader(new InputStreamReader(System.in));
                            String addNew = null;
                            System.out.println("Add a new student (john,smith,fn2187,4.0,2010)");
                            System.out.println("Id, firstname, lastname, accessId, gpa, year entered:");
                            //read what's inputted
                            addNew = oBufferedReader.readLine();
                            //print to verify
                            System.out.println("Input Statement: " + addNew);
                            //separate by comma
                            String[] userInput = addNew.split(",");
                            //get id
                            int newId = Integer.parseInt(userInput[0]);
                            //get first name and last name and access id
                            String fName = userInput[1];
                            String lName = userInput[2];
                            String access = userInput[3];
                            //get gpa
                            double newGpa = Double.parseDouble(userInput[4]);
                            //get year entered
                            int newYear = Integer.parseInt(userInput[5]);
                            //create new student
                            Student newStudent = new Student(newId, fName, lName, access, newGpa, newYear);
                            //send to addNewStudent
                            studentDao.addNewStudent(newStudent);


                        } catch (Exception oException) {
                            System.out.println("error, separate by comma?");
                        }

                    } else if (input.equals("3")) {
                        //edit student by id
                        System.out.println("Enter an id: ");
                        BufferedReader editInput = new BufferedReader(new InputStreamReader(System.in));
                        int editId = Integer.parseInt(editInput.readLine());
                        studentDao.editStudentById(editId);

                    } else if (input.equals("4")) {
                        //delete student by id
                        System.out.println("Enter an id: ");
                        BufferedReader deleteInput = new BufferedReader(new InputStreamReader(System.in));
                        //added the comma so the function searches for the id followed by comma
                        int deleteId = Integer.parseInt(deleteInput.readLine());
                        studentDao.deleteStudentById(deleteId);

                    } else if (input.equals("5")) {
                        //find a student by id
                        System.out.println("Enter an id: ");
                        BufferedReader idInput = new BufferedReader(new InputStreamReader(System.in));
                        int searchId;
                        searchId = Integer.parseInt(idInput.readLine());
                        studentDao.findStudentById(searchId);
                        System.out.println();


                    } else if (input.equals("6")) {
                        //find by first name
                        System.out.println("Enter first name: ");
                        BufferedReader fNameInput = new BufferedReader(new InputStreamReader(System.in));
                        String fName;
                        fName = fNameInput.readLine();
                        studentDao.findByFirstName(fName);

                    } else if (input.equals("7")) {
                        //find by last name
                        System.out.println("Enter Last name: ");
                        BufferedReader fNameInput = new BufferedReader(new InputStreamReader(System.in));
                        String fName;
                        fName = fNameInput.readLine();
                        studentDao.findByLastName(fName);

                    } else if (input.equals("q")) {
                        //quit program
                        System.out.println("Ending Program...");
                        break;
                    } else {
                        System.out.println("Error, select 1-7 or press q to exit");
                    }

                } catch (IOException ioException) {
                    ioException.getCause().getMessage();
                }

            }
        }
}
