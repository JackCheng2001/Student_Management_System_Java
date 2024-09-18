import java.util.HashMap;
import java.util.Map;

public class Student extends User {

    // A map to hold courses in which the student is enrolled, keyed by course ID
    private final Map<String, Course> enrolledCourses;
    // A map to hold grades for courses, keyed by course ID
    private Map<String, String> course_grades;
    /**
     * Constructor for creating a new Student object.
     * Initializes the student with the provided details and an empty list of enrolled courses.
     * @author Cheng Erxi,Jiayi Wang
     * @param id       The unique identifier for the student.
     * @param name     The name of the student.
     * @param username The username for the student's login.
     * @param password The password for the student's account.
     * @param grades   A map of the student's grades.
     */
    public Student(String id, String name, String username, String password, Map<String, String> grades) {
        super(id, name, username, password);
        this.enrolledCourses = new HashMap<>();
        this.course_grades = grades;
    }

    // Method to enroll in a course
    /**
     * Enrolls the student in a specified course.
     *
     * @param course The course to enroll in.
     */
    public void enrollInCourse(Course course) {
        enrolledCourses.put(course.getCourseId(), course);
    }

    // Getter for enrolled courses
    /**
     * Gets the courses in which the student is currently enrolled.
     *
     * @return A map of enrolled courses.
     */
    public Map<String, Course> getEnrolledCourses() {
        return enrolledCourses;
    }
    /**
     * Drops a specified course from the student's list of enrolled courses.
     *
     * @param courseId The ID of the course to be dropped.
     */
    public void dropCourse(String courseId) {
        enrolledCourses.remove(courseId);
    }
    /**
     * Gets a copy of the student's grades.
     *
     * @return A map of the student's grades.
     */
    public Map<String, String> getGrades() {
        return new HashMap<>(course_grades); // Return a copy to prevent external modification
    }
    /**
     * Sets the grade for a specified course.
     * The course must be in the student's list of enrolled courses.
     *
     * @param courseId The ID of the course for which to set the grade.
     * @param grade    The grade to be set for the course.
     */
    public void setGradeForCourse(String courseId, String grade) {
        if (enrolledCourses.containsKey(courseId)) {
            course_grades.put(courseId, grade);
        }
    }

}