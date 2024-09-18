import java.util.*;
import java.io.IOException;
/**
 * @author Cheng Erxi, Jiayi Wang
 * The Controller class for a Student Management System.
 * This class handles the main logic for user interaction and management of students, professors, admins, and courses.
 */
public class Controller {
    // Static lists to store different users and courses in the system

    private static List<Student> students = new ArrayList<>();
    private static List<Professor> professors = new ArrayList<>();
    static Map<String, Course> courses = new HashMap<>();
    private static List<Admin> admins = new ArrayList<>();
    private static Map<String, List<Student>> courseStudentList = new HashMap<>();
    /**
     * The main method that serves as the entry point of the application.
     * It initializes the system, reads user and course information from files, and starts the main application loop.
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        // Initialization code here
        // Create an instance of FileInfoReader and read the files
        // Then use the loaded data to log in users and perform operations
        Scanner scanner = new Scanner(System.in);
        try {
            // Read information from files and populate the lists

            students = FileInfoReader.readStudentInfo("studentInfo.txt");
            professors = FileInfoReader.readProfInfo("profInfo.txt");
            admins = FileInfoReader.readAdminInfo("adminInfo.txt");
            FileInfoReader fileReader = new FileInfoReader();
            courses = fileReader.readCourseInfo("courseInfo.txt");
            // Additional logic for handling users and the menu system
            // Main application loop
            // Populate the courseStudentList map
            populateCourseStudentList();
            // Main application loop for user interaction
            while (true) {
                // User menu display and input handling
                System.out.println("Welcome to the Student Management System");
                System.out.println("1 -- Login as a student");
                System.out.println("2 -- Login as a professor");
                System.out.println("3 -- Login as an admin");
                System.out.println("4 -- Quit the system");
                System.out.print("Please enter your option, e.g., '1': ");

                String input = scanner.nextLine();

                switch (input) {
                    case "1":
                        handleStudentLogin(scanner);    //if 1, turn to the student login
                        break;
                    case "2":
                        handleProfessorLogin(scanner);//if 2, turn to the professor login
                        break;
                    case "3":
                        handleAdminLogin(scanner);//if 1, turn to the admin login
                        break;
                    case "4":
                        System.out.println("Exiting the system. Goodbye!");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Handles the logic for student login.
     * Prompts the user for username and password and authenticates them against the student list.
     * @param scanner The scanner instance for reading user input.
     * @throws IOException If an I/O error occurs.
     */
    public static void handleStudentLogin(Scanner scanner) throws IOException {
        // Implement student login logic

        boolean temp = true;
        Student student = null;
        while (temp) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            student = (Student) authenticateUser(username, password, students);
            if (student != null) {
                break;
            }
            System.out.println("Authentication failed. Please try again.");
        }



        System.out.println("Welcome, " + student.getUsername());

        showStudentMenu(scanner, student);

    }
    /**
     * Handles the logic for professor login.
     * Prompts the user for username and password and authenticates them against the professor list.
     * @param scanner The scanner instance for reading user input.
     * @throws IOException If an I/O error occurs.
     */
    public static void handleProfessorLogin(Scanner scanner) throws IOException {
        // Implement professor login logic

        boolean temp = true;
        Professor professor = null;
        while (temp) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            professor = (Professor) authenticateUser(username, password, professors);
            if (professor != null) {
                break;
            }
            System.out.println("Authentication failed. Please try again.");
        }
        System.out.println("Welcome, " + professor.getUsername());
        showProfessorMenu(scanner, professor);
    }
    /**
     * Handles the logic for admin login.
     * Prompts the user for username and password and authenticates them against the admin list.
     * @param scanner The scanner instance for reading user input.
     * @throws IOException If an I/O error occurs.
     */
    public static void handleAdminLogin(Scanner scanner) throws IOException {
        // Implement admin login logic

        boolean temp = true;
        Admin admin = null;
        while (temp) {
            // Prompting for admin's username
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            // Prompting for admin's password
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            // Attempting to authenticate the admin
            // The authenticateUser method checks if the provided username and password match any admin in the admins list
            admin = (Admin) authenticateUser(username, password, admins);
            // If a matching admin is found, break out of the loop
            if (admin != null) {
                break;
            }
            // If authentication fails, inform the user and allow them to try again
            System.out.println("Authentication failed. Please try again.");
        }
        System.out.println("Welcome, " + admin.getUsername());// If login is successful, greet the admin
        showAdminMenu(scanner, admin);// Show the admin menu for further actions

    }
    /**
     * Displays the menu for student users and handles their input for various operations.
     * @param scanner The scanner instance for reading user input.
     * @param student The student instance currently logged in.
     * @throws IOException If an I/O error occurs.
     */
    public static void showStudentMenu(Scanner scanner, Student student) throws IOException {
        //System.out.println("Welcome, " + student);
        // Displaying the menu options for a student
        System.out.println("1 -- View all courses");
        System.out.println("2 -- Add courses to your list");
        System.out.println("3 -- View enrolled courses");
        System.out.println("4 -- Drop courses in your list");
        System.out.println("5 -- View grades");
        System.out.println("6 -- Return to previous menu");
// Prompting the student to make a choice from the menu
        System.out.println("Please enter your option, eg '1'");
        String input = scanner.nextLine();

        switch (input) {
            case "1":
                viewAllCourses();        // If the student selects option 1, display all available courses
                break;
            case "2":
                addCourseToList(student, scanner);        // If option 2 is chosen, allow the student to add courses to their list
                // Updating the course-student list after adding a new course
                populateCourseStudentList();
                break;
            case "3":
                viewEnrolledCourses(student);        // If option 3 is selected, show the student their currently enrolled courses
                break;
            case "4":
                dropCourseFromList(student, scanner);        // If option 4 is chosen, provide an option to drop a course
                populateCourseStudentList();        // Updating the course-student list after dropping a course
                break;
            case "5":
                viewGrades(student);        // If option 5 is selected, display the student's grades
                break;
            case "6":        // Option 6 allows the student to exit the menu system
                System.out.println("Exiting the system. Goodbye!");
                return;
            default:
                System.out.println("Invalid option. Please try again.");
        }

    }
    /**
     * Displays the menu for professor users and handles their input for various operations.
     * @param scanner The scanner instance for reading user input.
     * @param professor The professor instance currently logged in.
     * @throws IOException If an I/O error occurs.
     */
    public static void showProfessorMenu(Scanner scanner, Professor professor) throws IOException {
        // Professor menu logic
        //System.out.println("Welcome, " + professor);
        // Displaying the menu options for a professor
        System.out.println("1 -- View given courses");
        System.out.println("2 -- View student list of the given course");
        System.out.println("3 -- Return to previous menu");
// Prompting the professor to select an option
        System.out.println("Please enter your option, eg '1'");
        String input = scanner.nextLine();
// Switch statement to handle the professor's menu selection
        switch (input) {
            case "1":
                viewGivenCourses(professor);        // If the professor selects 1, display the list of courses they are teaching
                break;
            case "2":
                populateCourseStudentList();        // If the professor selects 2, first update the course-student list
                viewStudentListOfGivenCourse(scanner, professor);        // Then display the list of students for a specific course they are teaching
                break;
            case "3":
                System.out.println("Exiting the system. Goodbye!");        // If the professor selects 3, exit the professor menu
                return;
            default:
                System.out.println("Invalid option. Please try again.");        // If the professor enters an invalid option, prompt them to try again
        }
    }
    /**
     * Displays the menu for admin users and handles their input for various operations.
     * @param scanner The scanner instance for reading user input.
     * @param admin The admin instance currently logged in.
     * @throws IOException If an I/O error occurs.
     */
    public static void showAdminMenu(Scanner scanner, Admin admin) throws IOException {
        //System.out.println("Welcome, " + admin);
        // Displaying the menu options for an admin

        System.out.println("1 -- View all courses");
        System.out.println("2 -- Add new courses");
        System.out.println("3 -- Delete courses");
        System.out.println("4 -- Add new professor");
        System.out.println("5 -- Delete professor");
        System.out.println("6 -- Add new student");
        System.out.println("7 -- Delete student");
        System.out.println("8 -- Return to previous menu");
// Prompt the admin to select an option
        System.out.println("Please enter your option, eg '1'");
        String input = scanner.nextLine();
// Switch statement to handle the admin's menu selection

        switch (input) {
            case "1":        // If the admin selects option 1, display all available courses
                viewAllCourses();
                break;
            case "2":        // If option 2 is chosen, allow the admin to add a new course
                addNewCourse(scanner);
                break;
            case "3":        // If option 3 is selected, provide the ability to delete a course
                deleteCourse(scanner);
                break;
            case "4":        // If option 4 is chosen, allow the admin to add a new professor
                addNewProfessor(scanner);
                break;
            case "5":        // If option 5 is selected, provide the ability to delete a professor
                deleteProfessor(scanner);
                break;
            case "6":        // If option 6 is chosen, allow the admin to add a new student
                addNewStudent(scanner);
                break;
            case "7":        // If option 7 is selected, provide the ability to delete a student
                deleteStudent(scanner);
                break;
            case "8":        // Option 8 allows the admin to exit the menu system
                System.out.println("Exiting the system. Goodbye!");

                return;
            default:        // Handling invalid inputs with a prompt to try again
                System.out.println("Invalid option. Please try again.");
        }
    }
    /**
     * Authenticates a user based on the provided username and password.
     * This method iterates through a list of users and checks if the username and password match any user in the list.
     *
     * @param username The username input for authentication.
     * @param password The password input for authentication.
     * @param users    The list of users (which can include any user types extending the User class) against which to authenticate.
     * @return The authenticated User object if the credentials match, or null if no match is found.
     */
    public static User authenticateUser(String username, String password, List<? extends User> users) {
        // Iterating through each user in the provided list
        for (User user : users) {
            // Checking if the current user's username and password match the provided credentials
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                // Returning the user object if a match is found
                return user;
            }
        }
        // Returning null if no matching user is found
        return null;
    }
    /**
     * Displays all the courses currently available in the system.
     * If there are no courses, it prints a message indicating that no courses are available.
     * Otherwise, it prints a list of all courses along with their details.
     */
    public static void viewAllCourses() {
        // Check if the courses map is empty
        if (courses.isEmpty()) {
            // Print a message if there are no courses available
            System.out.println("No courses available.");
        } else {
            // Print the header for the course list
            System.out.println("-------------------------------------");
            System.out.println("Course ID | Course Name | Schedule");
            System.out.println("-------------------------------------");
            // Iterate through each course in the courses map
            for (Course course : courses.values()) {
                // Print details of each course
                System.out.println(course);
            }
            // Print a footer to end the list
            System.out.println("-------------------------------------");
        }
    }

    /**
     * Adds a course to a student's list of enrolled courses.
     * The method prompts the student to enter the ID of the course they wish to enroll in.
     * It checks if the course exists and whether the student is already enrolled in the course.
     * If the course is available and the student is not already enrolled, it enrolls the student in the course.
     *
     * @param student The student who is enrolling in the course.
     * @param scanner The scanner instance for reading user input.
     */
    public static void addCourseToList(Student student, Scanner scanner) {
        // Prompting the student to enter the course ID they wish to enroll in
        System.out.println("Enter the course ID you want to add, or 'q' to return to the previous menu:");
        String courseId = scanner.nextLine().trim();

        // Attempting to retrieve the course from the courses map using the provided ID
        Course courseToAdd = courses.get(courseId);
        // Check if the course exists
        if (courseToAdd == null) {
            System.out.println("Course not found.");
            return;
        }

        // Check if the student is already enrolled in the course
        if (student.getEnrolledCourses().containsKey(courseToAdd.getCourseId())) {
            System.out.println("You are already enrolled in this course.");
            return;
        }

        // Enrolling the student in the course
        student.enrollInCourse(courseToAdd);
        System.out.println("You have successfully enrolled in: " + courseToAdd.getCourseName());
    }

    /**
     * Displays the list of courses that a student is currently enrolled in.
     * If the student is not enrolled in any courses, a message is displayed to indicate this.
     * Otherwise, the details of each enrolled course are displayed.
     *
     * @param student The student whose enrolled courses are to be displayed.
     */
    public static void viewEnrolledCourses(Student student) {
        Map<String, Course> enrolledCourses = student.getEnrolledCourses();
        // Retrieving the map of courses that the student is currently enrolled in
        // Check if the student is enrolled in any courses
        if (enrolledCourses.isEmpty()) {
            // Informing the student that they are not enrolled in any courses
            System.out.println("You are not enrolled in any courses.");
        } else {
            // Informing the student of the courses they are enrolled in
            System.out.println("You are enrolled in the following courses:");
            // Iterating over the enrolled courses and displaying each one
            for (Course course : enrolledCourses.values()) {
                System.out.println(course);// The Course class's toString method is implicitly called here
            }
        }
    }
    /**
     * Allows a student to drop a course from their list of enrolled courses.
     * The method prompts the student to enter the ID of the course they wish to drop.
     * It checks if the student is currently enrolled in the course and, if so, removes it from their list.
     *
     * @param student The student who is dropping the course.
     * @param scanner The scanner instance for reading user input.
     */
    public static void dropCourseFromList(Student student, Scanner scanner) {
        // Prompting the student to enter the course ID they wish to drop
        System.out.println("Enter the course ID you want to drop, or 'q' to return to the previous menu:");
        String courseId = scanner.nextLine().trim();// Reading and trimming the input

        // Checking if the user wants to return to the previous menu
        if ("q".equalsIgnoreCase(courseId)) {
            return; // Return to the previous menu
        }

        // Checking if the student is enrolled in the course
        if (student.getEnrolledCourses().containsKey(courseId)) {
            // Dropping the course from the student's enrolled list
            student.dropCourse(courseId);
            // Confirming to the student that the course has been dropped
            System.out.println("Course dropped successfully.");
        } else {
            // Informing the student if they are not enrolled in the course
            System.out.println("You are not enrolled in this course.");
        }
    }
    /**
     * Displays the grades of a student for all the courses they are enrolled in.
     * If the student has no grades available (either not enrolled in any courses or grades not yet assigned),
     * a message is displayed indicating no grades are available.
     * Otherwise, it lists the grades for each course.
     *
     * @param student The student whose grades are to be displayed.
     */
    public static void viewGrades(Student student) {
        Map<String, String> grades = student.getGrades();

        if (grades.isEmpty()) {
            System.out.println("No grades available.");
        } else {
            System.out.println("Your grades:");
            for (Map.Entry<String, String> entry : grades.entrySet()) {
                String courseName = courses.get(entry.getKey()).getCourseName(); // Get the course name using the course ID
                System.out.println("Course: " + courseName + ", Grade: " + entry.getValue());
            }
        }
    }
    /**
     * Displays the list of courses that a professor is currently teaching.
     * It iterates through all the courses in the system and checks if the professor is the lecturer for each course.
     * Only the courses taught by the specified professor are displayed.
     *
     * @param professor The professor whose courses are to be displayed.
     */
    public static void viewGivenCourses(Professor professor) {
        System.out.println("Courses you are teaching:");
        for (Course course : courses.values()) {
            if (course.getLecturer().equals(professor.getName())) {
                System.out.println(course);
            }

        }
    }
    /**
     * Displays a list of students enrolled in a specific course taught by the given professor.
     * The professor inputs a course ID, and the method checks if the professor is teaching that course.
     * If so, it displays a list of all students enrolled in the course.
     * If the course ID is invalid or the professor is not teaching that course, it displays an appropriate message.
     *
     * @param scanner   The scanner instance for reading user input.
     * @param professor The professor whose course's student list is to be viewed.
     */
    public static void viewStudentListOfGivenCourse(Scanner scanner, Professor professor) {
        // Prompting the professor to enter the course ID
        System.out.println("Enter the course ID to view the student list:");
        String courseId = scanner.nextLine().trim();

        // Check if the professor is teaching the course
        Course course = courses.get(courseId);
        // Checking if the course exists and the professor is teaching it
        if (course != null && course.getLecturer().equals(professor.getName())) {
            List<Student> studentsEnrolled = courseStudentList.get(courseId);
            // Checking if there are any students enrolled in the course
            if (studentsEnrolled != null && !studentsEnrolled.isEmpty()) {
                System.out.println("Students enrolled in " + course.getCourseName() + ":");
                for (Student student : studentsEnrolled) {
                    System.out.println(student.getName() + " (" + student.getId() + ")");
                }
            } else {
                System.out.println("No students are enrolled in this course.");
            }
        } else {
            System.out.println("Invalid course ID or you do not teach this course.");
        }
    }
    /**
     * Populates the map 'courseStudentList' with a list of students enrolled in each course.
     * This method iterates through all students and their enrolled courses,
     * updating the 'courseStudentList' to reflect the current enrollment status.
     */
    public static void populateCourseStudentList() {
        // Update the course-student list
        // Populates the 'courseStudentList' with students enrolled in each course
        for (Student student : students) {    // Iterating over each student in the 'students' list

            Map<String, Course> enrolledCourses = student.getEnrolledCourses();
            for (Map.Entry<String, Course> entry : enrolledCourses.entrySet()) {
                String courseId = entry.getKey();
                // If 'courseStudentList' does not already contain an entry for this course ID,
                // create a new list for this course ID
                courseStudentList.putIfAbsent(courseId, new ArrayList<>());
                courseStudentList.get(courseId).add(student);
            }
        }
    }
    /**
     * Adds a new course to the system by collecting various details about the course from the user.
     * The user can enter 'q' at any point to exit the process.
     *
     * @param scanner The scanner instance for reading user input.
     */
    public static void addNewCourse(Scanner scanner) {
        // Collecting the course ID from the user
        System.out.print("Please enter the course ID, or type 'q' to end: ");
        String courseId = scanner.nextLine();
        if ("q".equals(courseId)) return;

        System.out.print("Please enter the course name, or type 'q' to end: ");
        String courseName = scanner.nextLine();
        if ("q".equals(courseName)) return;
        // Similar pattern for collecting other course details: name, lecturer, days, start time, end time, and capacity
        // ... (repeated for each course attribute)

        System.out.print("Please enter the lecturer name, or type 'q' to end: ");
        String lecturer = scanner.nextLine();
        if ("q".equals(lecturer)) return;

        System.out.print("Please enter the course days, or type 'q' to end: ");
        String days = scanner.nextLine();
        if ("q".equals(days)) return;

        System.out.print("Please enter the course startTime, or type 'q' to end: ");
        String startTime = scanner.nextLine();
        if ("q".equals(startTime)) return;

        System.out.print("Please enter the course endTime, or type 'q' to end: ");
        String endTime = scanner.nextLine();
        if ("q".equals(endTime)) return;

        System.out.print("Please enter the course capacity, or type 0 to end: ");
        int capacity = scanner.nextInt();
        if (capacity == 0) return;
        // Create a new Course object after collecting all course details
        Course newCourse = new Course(courseId, courseName, lecturer, days, startTime, endTime, capacity);

        // Add the new course to the courses map
        courses.put(courseId, newCourse);
        System.out.println("Successfully added the course: " + newCourse);
    }
    /**
     * Deletes a course from the system based on the course ID provided by the user.
     * Also removes the course from the enrolled courses and grades of all students.
     * The user can enter 'q' to exit the process.
     *
     * @param scanner The scanner instance for reading user input.
     */
    public static void deleteCourse(Scanner scanner) {
        System.out.print("Please enter the course ID you want to delete, or 'q' to end: ");
        String courseId = scanner.nextLine();
        if ("q".equalsIgnoreCase(courseId)) return;

        Course courseToRemove = courses.get(courseId);
        if (courseToRemove != null) {
            courses.remove(courseId);
            System.out.println("Course " + courseId + " has been successfully deleted.");

            removeCourseFromStudents(courseId);
        } else {
            System.out.println("Course not found.");
        }
    }
    /**
     * Removes the specified course from the enrolled courses and grades of all students.
     * This is called after a course is deleted from the system.
     *
     * @param courseId The ID of the course to be removed from students' records.
     */
    public static void removeCourseFromStudents(String courseId) {
        for (Student student : students) {
            student.getEnrolledCourses().remove(courseId);

            student.getGrades().remove(courseId);
        }
    }
    /**
     * Adds a new professor to the system by collecting their details.
     * Checks for duplicate ID and exits if a professor with the same ID already exists.
     * The user can enter 'q' to exit the process.
     *
     * @param scanner The scanner instance for reading user input.
     */
    public static void addNewProfessor(Scanner scanner) {
        System.out.println("Adding a new professor.");

        System.out.print("Please enter the professor's ID, or type 'q' to end: ");
        String id = scanner.nextLine().trim();
        if ("q".equalsIgnoreCase(id)) return;

        // Check if the professor ID already exists
        for (Professor prof : professors) {
            if (prof.getId().equals(id)) {
                System.out.println("A professor with this ID already exists.");
                return; // ID already exists,  exit the method
            }
        }

        System.out.print("Please enter the professor's name, or type 'q' to end: ");
        String name = scanner.nextLine().trim();
        if ("q".equalsIgnoreCase(name)) return;

        System.out.print("Please enter the professor's username, or type 'q' to end: ");
        String username = scanner.nextLine().trim();
        if ("q".equalsIgnoreCase(username)) return;

        System.out.print("Please enter the professor's password, or type 'q' to end: ");
        String password = scanner.nextLine().trim();
        if ("q".equalsIgnoreCase(password)) return;

        // Create the new professor object
        Professor newProfessor = new Professor(name, id, username, password);
        professors.add(newProfessor);
        System.out.println("Successfully added new professor: " + name);
    }
    /**
     * Deletes a professor from the system based on the provided ID.
     * The user can enter the professor's ID or 'q' to exit the process.
     * If the professor is found, they are removed from the system and any courses they were teaching.
     *
     * @param scanner The scanner instance for reading user input.
     */
    public static void deleteProfessor(Scanner scanner) {
        System.out.print("Please enter the professor's ID you want to delete, or type 'q' to end: ");
        String id = scanner.nextLine().trim();
        if ("q".equalsIgnoreCase(id)) return;

        // Find the professor with the given ID
        Professor professorToRemove = null;
        for (Professor prof : professors) {
            if (prof.getId().equals(id)) {
                professorToRemove = prof;

                break;
            }
        }
        // Remove professor with the given ID is found
        if (professorToRemove != null) {
            professors.remove(professorToRemove);
            System.out.println("Professor with ID " + id + " has been successfully deleted.");
            // Also remove this professor from any courses they are teaching
            removeProfessorFromCourses(id);
        } else {
            System.out.println("No professor found with ID: " + id);
        }
    }
    /**
     * Removes a professor from all courses they are currently teaching.
     * Sets the lecturer of any courses taught by this professor to null.
     *
     * @param professorName The name of the professor to be removed from courses.
     */
    public static void removeProfessorFromCourses(String professorName) {
        for (Course course : courses.values()) {
            if (course.getLecturer().equals(professorName)) {
                course.setLecturer(null);
            }
        }

    }
    /**
     * Adds a new student to the system by collecting their details.
     * Checks for duplicate ID and exits if a student with the same ID already exists.
     * The user can enter 'q' at any point to exit the process.
     *
     * @param scanner The scanner instance for reading user input.
     */
    public static void addNewStudent(Scanner scanner) {
        System.out.println("Adding a new student.");

        System.out.print("Please enter the student's ID, or type 'q' to end: ");
        String id = scanner.nextLine().trim();
        if ("q".equalsIgnoreCase(id)) return;

        // Check if the student ID already exists
        for (Student student : students) {
            if (student.getId().equals(id)) {
                System.out.println("A student with this ID already exists.");
                return; // ID already exists, exit the method
            }
        }

        System.out.print("Please enter the student's name, or type 'q' to end: ");
        String name = scanner.nextLine().trim();
        if ("q".equalsIgnoreCase(name)) return;

        System.out.print("Please enter the student's username, or type 'q' to end: ");
        String username = scanner.nextLine().trim();
        if ("q".equalsIgnoreCase(username)) return;

        System.out.print("Please enter the student's password, or type 'q' to end: ");
        String password = scanner.nextLine().trim();
        if ("q".equalsIgnoreCase(password)) return;


        Map<String, String> coursesGrades = new HashMap<>();


        // Create the new student object
        Student newStudent = new Student(id, name, username, password, coursesGrades);
        students.add(newStudent); // Add the new student to the list
        System.out.println("Successfully added new student: " + name);
    }
    /**
     * Deletes a student from the system based on the provided ID.
     * The user is prompted to enter the student's ID or type 'q' to exit the process.
     * If a student with the given ID is found, they are removed from the system.
     *
     * @param scanner The scanner instance for reading user input.
     */
    public static void deleteStudent(Scanner scanner) {
        System.out.print("Please enter the student's ID you want to delete, or type 'q' to end: ");
        String id = scanner.nextLine().trim();
        if ("q".equalsIgnoreCase(id)) return;

        // Find the student with the given ID
        Student studentToRemove = null;
        for (Student student : students) {
            if (student.getId().equals(id)) {
                studentToRemove = student;
                break;
            }
        }

        // If a student with the given ID is found, remove them
        if (studentToRemove != null) {
            students.remove(studentToRemove);
            System.out.println("Student with ID " + id + " has been successfully deleted.");
            // Also remove this student from any enrolled courses
        } else {
            System.out.println("No student found with ID: " + id);
        }


    }


}




