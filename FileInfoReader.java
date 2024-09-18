import java.io.*;
import java.util.*;

public class FileInfoReader {
    /**
     * Reads course information from a file and maps it into Course objects.
     * @author Cheng Erxi,Jiayi Wang
     * @param filename The name of the file containing course information.
     * @return A map of course ID to Course object.
     * @throws IOException If an error occurs during file reading.
     */
    public Map<String, Course> readCourseInfo(String filename) throws IOException {
        Map<String, Course> courses = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");

                String courseId = parts[0].trim();
                String courseName = parts[1].trim();
                String professorName = parts[2].trim();
                Course course = new Course(parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim(), parts[4].trim(), parts[5].trim(), Integer.parseInt(parts[6].trim()));
                courses.put(courseId, course);
            }
        }
        return courses;
    }

    // Methods for reading admin, student, and professor information follow a similar pattern
    // Each method reads lines from the file, splits them into parts, and constructs corresponding objects
    // ...

    /**
     * Parses a string containing course grades into a map.
     *
     *
     * @return A map of course IDs to grades.
     */
    public static List<Admin> readAdminInfo(String filename) throws IOException {
        List<Admin> admins = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");

                if (parts.length == 4) {
                    Admin admin = new Admin(parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim());
                    admins.add(admin);
                }
            }
        }
        return admins;
    }

    /**
     * Reads student information from a specified file and creates a list of Student objects.
     *
     * @param filename The name of the file containing student information.
     * @return A list of Student objects created from the file data.
     * @throws IOException If an error occurs during file reading.
     */
    public static List<Student> readStudentInfo(String filename) throws IOException {
        List<Student> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");

                if (parts.length >= 5) {
                    Map<String, String> coursesGrades = parseCourses(parts[4]);
                    Student student = new Student(parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim(), coursesGrades);

                    students.add(student);
                }
            }
        }
        return students;
    }
    /**
     * Reads professor information from a specified file and creates a list of Professor objects.
     *
     * @param filename The name of the file containing professor information.
     * @return A list of Professor objects created from the file data.
     * @throws IOException If an error occurs during file reading.
     */
    public static List<Professor> readProfInfo(String filename) throws IOException {
        List<Professor> professors = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");

                if (parts.length == 4) {
                    Professor professor = new Professor(parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim());
                    professors.add(professor);
                }
            }
        }
        return professors;
    }
    /**
     * Reads professor information from a file and creates a list of Professor objects.
     * Each line in the file represents a professor, with their details separated by semicolons.
     *
     *
     * @return A list of Professor objects.
     * @throws IOException If an error occurs during file reading.
     */
    // Methods to read adminInfo.txt, studentInfo.txt, and profInfo.txt
    // Similar to readCourseInfo but adapted for each file format
    private static Map<String, String> parseCourses(String coursesPart) {
        Map<String, String> coursesGrades = new HashMap<>();
        // Split the courses part into individual course-grade pairs
        String[] courseEntries = coursesPart.split(",");
        for (String courseEntry : courseEntries) {
            String[] courseGradePair = courseEntry.trim().split(":");
            if (courseGradePair.length == 2) {
                String course = courseGradePair[0].trim();
                String grade = courseGradePair[1].trim();
                coursesGrades.put(course, grade);
            }
        }
        return coursesGrades;
    }
}