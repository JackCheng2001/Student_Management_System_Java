import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
    @author Cheng Erxi, Jiayi Wang
 */
import static org.junit.jupiter.api.Assertions.*;


public class ProfessorTest {
    private Professor professor;
    private Course course1;
    private Course course2;
    // Setup method to initialize common objects before each test
    @BeforeEach
    public void setUp(){
        //initializing a professor instance and sample courses
        professor = new Professor("Dr.Who","666","whoisthis","12345");
        course1 = new Course("CIS590","Java","Dr.Who", "Monday","9:00 AM","11:00 AM",25);
        course2 = new Course("ENM503","Statistics","Dr.Who","Friday","2:30 PM", "4:30 PM", 50);
    }
    // Test for adding courses to a professor's list
    @Test
    public void testAddCourse(){
        //1. add a normal course
        professor.addCourse(course1);
        professor.addCourse(course2);
        // Assertions to check if the courses are added correctly
        assertEquals(2, professor.getCoursesTaught().size());
        assertEquals(true,professor.getCoursesTaught().contains(course1));
        assertEquals(true,professor.getCoursesTaught().contains(course2));


    }
    @Test
    // Test for retrieving the list of courses taught by the professor
    public void testGetCoursesTaught(){
        // Adding courses to the professor's list

        professor.addCourse(course1);
        professor.addCourse(course2);

        List<Course> courses = professor.getCoursesTaught();
        // Assertions to check if the courses are added correctly
        assertEquals(2,courses.size());
        assertTrue(courses.contains(course1));
        assertTrue(courses.contains(course2));

    }
    // Test for retrieving the list of courses taught by the professor
    @Test
    public void testGetCoursesTaughtEmptyList(){
        //2. obtain the list of courses taught when the list is empty
        List<Course> courses = professor.getCoursesTaught();

        assertNotNull(courses);
        assertTrue(courses.isEmpty());
    }
}
