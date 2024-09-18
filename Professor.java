import java.util.ArrayList;
import java.util.List;
/**
 * @author Cheng Erxi,Jiayi Wang
 * Constructor for the Professor class.
 */
 public class Professor extends User {
    private final String name;

    private ArrayList<Course> coursesTaught;
    /**
     * Constructor for the Professor class.
     *
     * @param name     The name of the professor.
     * @param id       The unique identifier for the professor.
     * @param username The username for the professor's account.
     * @param password The password for the professor's account.
     */
    public Professor(String name,String id , String username, String password) {
        super(name, id, username, password);
        this.name=name;
        this.id=id;
        this.username=username;
        this.password=password;
        this.coursesTaught = new ArrayList<Course>();
    }
    /**
     * Adds a course to the list of courses taught by the professor.
     *
     * @param course The course to be added.
     */
    public void addCourse(Course course) {
        this.coursesTaught.add(course);
    }

    public String getName() {
        return name;
    }

    public List<Course> getCoursesTaught() {
        return coursesTaught;
    }
}