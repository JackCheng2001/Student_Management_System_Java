/** Class representing a course in an educational context.
    @author Cheng Erxi, Jiayi Wang
 */
public class Course {
    private String courseId;
    private String courseName;
    private String lecturer;
    private String days;
    private String startTime;
    private String endTime;
    private int capacity;
    /**
     * Constructor for creating a new Course object.
     *
     * @param courseId   Unique identifier for the course.
     * @param courseName Name of the course.
     * @param lecturer   Name of the lecturer teaching the course.
     * @param days       Days of the week when the course is held.
     * @param startTime  Start time of the course.
     * @param endTime    End time of the course.
     * @param capacity   Maximum number of students that can enroll in the course.
     */
    public Course(String courseId, String courseName, String lecturer, String days, String startTime, String endTime, int capacity) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.lecturer = lecturer;
        this.days = days;
        this.startTime = startTime;
        this.endTime = endTime;
        this.capacity = capacity;
    }

    // getters and setters for the fields
    @Override
    public String toString() {
        return String.format("%s | %s | %s %s-%s",
                courseId, courseName, days, startTime, endTime);
    }

    public String getCourseId() {
        return courseId;
    }

    // Getter for courseName
    public String getCourseName() {
        return courseName;
    }
    public String getLecturer() {
        return lecturer;
    }
    public void setLecturer(String lecturer_1) {
        this.lecturer=lecturer_1;

    }




}